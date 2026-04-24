package com.mdb;

import com.mdb.entity.MdbPlayerInfo;
import com.mdb.util.ConcurrentEnvironment;
import com.mdb.util.TimeCostPrint;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Mdb {
    public static Logger logger = LoggerFactory.getLogger(Mdb.class.getName());
    private static Mdb instance = new Mdb();
    private static final String EXECUTOR_NAME = "mdb.scheduler";
    // 统一后的执行器同时承担定时任务和异步 DB 任务，线程数默认沿用原 dbExecutor 配置。
    private static final int EXECUTOR_THREAD_COUNT =
            Integer.getInteger("mdb.scheduler.threads", Integer.getInteger("mdb.db.threads", 6));
    private static final long CLEAR_CACHE_DELAY_SECONDS = 10 * TimeCostPrint.minuteMills / 1000;

    private static final int EXECUTOR_PLAYER_THREAD_COUNT = Integer.getInteger("mdb.player.executor", 2);
    private long playerFlushDelayMill =TimeCostPrint.minuteMills*20;
    private static final List<ThreadPoolExecutor> playerExecutor = new ArrayList<>();
    private static final Map<Long, MdbPlayerInfo> mdbPlayerMap = new ConcurrentHashMap<>();


    public static Mdb getInstance() {
        return instance;
    }

    private Mdb() {
    }

    private volatile CopyOnWriteArrayList<Tables> allTables = new CopyOnWriteArrayList<>(); // 为了支持在一个进程中开启多个mdb

    // xBean 是否开启全量检测
    private boolean xBeanDetailCheck = false;
    // xBean 是否开启严格检测
    private boolean strictCheckOpen = true;
    // checkPoint前+1
    private volatile long checkPointCount = 0;

    public boolean running = false;

    public Checkpoint checkpoint = null;
    ScheduledThreadPoolExecutor scheduler;

    public static void configLog() {
        LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
        File conFile = new File("mdb\\src\\main\\log4j2.xml");
        logContext.setConfigLocation(conFile.toURI());
        logContext.reconfigure();
    }

    public synchronized void start(MdbMetadata mdbMetadata) throws Exception {
        logger.warn("@@@@@@@@@@@@@@@@ mdb start begin @@@@@@@@@@@@@@@@ mdb metadata = " + mdbMetadata.getName());

        try {
            Class.forName(XBean.class.getName());
            Class<?> tableClass = Class.forName("table." + mdbMetadata.getName() + "._Tables_");
            Constructor<?> constructor = tableClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object table = constructor.newInstance();
            Tables tables = new Tables();
            allTables.add(tables);
            Field[] fields = tableClass.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                Object fieldValue = field.get(table);
                if (fieldValue instanceof TTable)
                    tables.add((TTable) fieldValue);
            }
            tables.open(mdbMetadata);
            logger.warn("{} has {} tables ......", mdbMetadata.getName(), tables.tables.size());
            // checkpoint
            if (checkpoint == null) {
                scheduler = ConcurrentEnvironment.getInstance().newScheduledThreadPool(EXECUTOR_NAME, EXECUTOR_THREAD_COUNT);
                // 每次初始化一个线程
                initPlayerExecutor();
                // 关闭后，不再执行“延迟任务”（delay/一次性定时）
                scheduler.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
                // 关闭后，不再继续执行“周期任务”（scheduleAtFixedRate / scheduleWithFixedDelay）
                scheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
                // 任务 cancel 后，立即从队列移除
                scheduler.setRemoveOnCancelPolicy(true);
                checkpoint = new Checkpoint();
                scheduler.scheduleWithFixedDelay(checkpoint, 0, mdbMetadata.getCheckPointPeriod(), TimeUnit.MILLISECONDS);
                scheduler.scheduleWithFixedDelay(this::tick, 1, 1, TimeUnit.SECONDS);
                scheduler.scheduleWithFixedDelay(this::clearCacheRound,
                        CLEAR_CACHE_DELAY_SECONDS,
                        CLEAR_CACHE_DELAY_SECONDS,
                        TimeUnit.SECONDS);
            }

            logger.warn("@@@@@@@@@@@@@@@@  mdb start end  @@@@@@@@@@@@@@@@");
            running = true;
        } catch (Throwable e) {
            logger.error("Mdb start error", e);
            closeInternal(true, false);
            throw e;
        }

    }

    /**
     *
     * 没有数据：设置为wait_load状态，异步进行load;异步Load开始设置load_ing状态;load_finish以后回调load_finish接口;
     *  wait_load;不处理;直接return;
     *  load_ing;不处理;直接return;
     *  load_finish;不处理;因为已经上线了;
     *  wait_flush;直接设置为load_finish;
     *  flush_ing;错误码;进行像客户端提示说正在加载数据中请稍后重试;
     *  flush_finish;不会有这个状态;flush_ing结束后直接就删数据了;
     */
    public void loadPlayerAllTableToMemory(Long key, Object param, MdbLoadCallback callback) {
        putPlayerThread(key, a->{
            long currMs = System.currentTimeMillis();
            Long playerId = (Long) a.stream().findAny().get();
            MdbPlayerInfo mdbPlayerInfo = mdbPlayerMap.get(playerId);
            if (mdbPlayerInfo == null) {
                mdbPlayerInfo = new MdbPlayerInfo(key, param, MdbPlayerInfo.load_ing);
                mdbPlayerInfo.setMdbLoadCallback(callback);
                mdbPlayerMap.put(key, mdbPlayerInfo);
                // 没有数据：设置为wait_load状态，异步进行load;异步Load开始设置load_ing状态;load_finish以后回调load_finish接口;
                executeAsync(()->_loadPlayerAllTableToMemory(key, loadFinishCallBack()));
                return;
            }
            mdbPlayerInfo.setMdbLoadCallback(callback);

            switch (mdbPlayerInfo.getState()) {
                case MdbPlayerInfo.load_wait:
                    // ;不处理;直接return;
                    mdbPlayerInfo.setState(MdbPlayerInfo.flush_ing);
                    break;
                case MdbPlayerInfo.load_ing:
                    // 不处理;直接return;
                    break;
                case MdbPlayerInfo.load_finish:
                    // 再调用一次吧;业务线程会进行处理重复的,防止bug导致玩家永远也上不了线
                    loadFinishCallBack().callback(playerId,  true, null);
                    break;
                case MdbPlayerInfo.flush_wait:
                    // 直接设置为load_finish;
                    mdbPlayerInfo.setState(MdbPlayerInfo.load_finish);
                    mdbPlayerInfo.setOfflineTime(0);// 清除离线时间状态;
                    callback.callback(mdbPlayerInfo, false, null);
                    break;
                case MdbPlayerInfo.flush_ing:
                    // 错误码;进行像客户端提示说正在加载数据中请稍后重试;
                    callback.callback(mdbPlayerInfo, false, null);
                    break;
                case MdbPlayerInfo.flush_finish:
                    // 不会有这个状态;flush_ing结束后直接就删数据了;
                    break;
            }
        });
    }



    private void _loadPlayerAllTableToMemory(Object key, MdbPlayerFinishCallback callback) {
        logger.info("loadPlayerAllTableToMemory key {}", key);
        try {
            TimeCostPrint timeCostPrint = new TimeCostPrint(logger, 20, "loadPlayerAllTableToMemory");
            for (Tables allTable : allTables) {
                for (TTable table : allTable.getTables()) {
                    if (!table.isSupportFlush()) {
                        continue;
                    }
                    table.get(key);
                }
            }
            timeCostPrint.print();
            putPlayerThread(Collections.singletonList(key), a -> callback.callback(key, true, null));
        } catch (Exception e) {
            logger.error("Mdb loadPlayerAllTableToMemory error key {} ", key, e);
            putPlayerThread(Collections.singletonList(key), a -> callback.callback(key, false, e));
        }
    }

    /**
     *     没有数据;不处理;warn警告
     *     wait_load;删除map数据;回调flush_finish事件
     *     load_ing;预约flush，在玩家数据里记录一个离线时间;load结束的时候如果有离线时间,就应该主动调用flush;
     *     load_finish;异步进行flush，将状态修改为wait_flush
     *     wait_flush;直接不处理;修改离线时间;
     *     flush_ing;直接不处理;修改离线时间;
     *     flush_finish;不处理;
     */
    public void flush(Long key, MdbFlushCallback callback, long offlineTime) {

        putPlayerThread(key, a->{
            long currMs = System.currentTimeMillis();
            Long playerId = (Long) a.stream().findAny().get();
            MdbPlayerInfo mdbPlayerInfo = mdbPlayerMap.get(playerId);
            if(mdbPlayerInfo == null) {
                logger.error("mdb flush的时候没有MdbPlayerInfo数据");
                if (callback != null) {
                    callback.onFlushOver(null, false, null);
                }
                return;
            }
            mdbPlayerInfo.setMdbFlushCallback(callback);
            mdbPlayerInfo.setOfflineTime(offlineTime);

            switch (mdbPlayerInfo.getState()) {
                case MdbPlayerInfo.load_wait:
                    mdbPlayerInfo.setState(MdbPlayerInfo.flush_ing);
                    // 回调flush_finish
                    flushFinishCallBack().callback(key, true, null);
                    break;
                case MdbPlayerInfo.load_ing:
                    // 预约flush，在玩家数据里记录一个离线时间;load结束的时候如果有离线时间,就应该主动调用flush;
                    // 不处理 离线时间已经设置了;等load结束的时候检测离线时间就行了
                    /*
                     直接抛到队列里;排队预约flush; 先抛到scheduler队列;等待load_ing结束;然后再抛到player队列进行flush预约;
                     上面的有点问题，scheduler并没有按线程id来进行处理;
                     这个感觉还是有点复杂,还是根据离线时间来判断吧;
                     */
                    /*scheduler.execute(()->{
                       putPlayerThread(playerId,aa->{
                           MdbPlayerInfo pInfo = mdbPlayerMap.get(playerId);
                           // 排队预约的时候玩家已经离线了
                           if(pInfo == null) {
                               logger.error();
                               return;
                           }
                       });
                    });*/
                    break;
                case MdbPlayerInfo.load_finish:
                    // 异步进行flush，将状态修改为wait_flush
                    mdbPlayerInfo.setState(MdbPlayerInfo.flush_wait);
                    mdbPlayerInfo.setFlushStartTime(currMs+ playerFlushDelayMill);
                    break;
                case MdbPlayerInfo.flush_wait:
                    //直接不处理;修改离线时间;
                    break;
                case MdbPlayerInfo.flush_ing:
                    //直接不处理;修改离线时间;
                    break;
                case MdbPlayerInfo.flush_finish:
                    //不处理;
                    break;
            }
        });
    }


    private boolean _flush(Long key, MdbPlayerFinishCallback callback) {
        boolean allSuccess = true;
        try {
            logger.info("flush start key {}", key);
            TimeCostPrint timeCostPrint = new TimeCostPrint(logger, 20, "flush");
            for (Tables allTable : allTables) {
                boolean detect = allTable.getStorage().detect();
                if (!detect) {
                    logger.error("flush 和数据库的连接已经的断开！！！ tablesName {} ", allTable.getName());
                    allSuccess = false;
                    continue;
                }
                for (TTable table : allTable.tables.values()) {
                    TTable tt = table;
                    if (tt.isSupportFlush()) {
                        // 名义上增加只读锁，其实是flush操作，目的是为了和checkpoint的write锁互斥，同时又保证多玩家在没有checkpoint时flush自己数据能并发执行
                        tt.readlock.lock();
                        try {
                            if (!tt.flush(key)) {
                                allSuccess = false;
                            }
                        } finally {
                            tt.readlock.unlock();
                        }
                    }
                }
            }
            timeCostPrint.print();
            boolean finalAllSuccess = allSuccess;
            putPlayerThread(Collections.singletonList(key), a->callback.callback(key, finalAllSuccess, null));
        } catch (Exception e) {
            // 如果失败抛出异常，业务层应该下次还让玩家再次进入这个进程，不然会有脏数据的问题; 业务层也可以尝试进行重试
            putPlayerThread(Collections.singletonList(key), a->callback.callback(key, false, e));
        }
        return allSuccess;
    }

    public void tick() {
        tick(System.currentTimeMillis());
    }

    void tick(long currTime) {
        for (Tables allTable : allTables) {
            for (TTable table : allTable.getTables()) {
                table.tick(currTime);
            }
        }

        // 每秒检测
        putPlayerThread(mdbPlayerMap.keySet(), this::mdbPlayerTick);

    }

    private void clearCacheRound() {
        for (Tables allTable : allTables) {
            for (TTable table : allTable.getTables()) {
                // 支持flush 说明是玩家个人数据; 不清理让玩家手动调用flush清理
                // 这里之前有这个逻辑是因为玩家数据可能玩家还在线就会自动给回收了，其实感觉回收了也没事,这里可以先不管;
                // 还有一个问题，flush的数据应该必须得玩家还在线的时候才能访问;不然会出现脏数据的问题;
                // 为了解决脏数据，感觉需要玩家在线才能加载，然后flush以后九不准再次加载了;这样的话必须保证load和flush顺序;
                //
                // 这里给注释掉，防止玩家flush
                if (table.isSupportFlush()) {
                    continue;
                }
                table.tickClearCache();
            }
        }
    }

    public synchronized void close() {
        closeInternal(false, true);
    }

    private void closeInternal(boolean force, boolean runCheckpoint) {
        if (!force && !running) {
            return;
        }
        logger.info("@@@@@@@@@@@@@@@@  mdb stop begin   @@@@@@@@@@@@@@@@");
        if (checkpoint != null && runCheckpoint) {
            try {
                checkpoint.checkpoint(System.currentTimeMillis(), true);
            } catch (Throwable e) {
                logger.error("checkpoint on close error", e);
            }
        }
        checkpoint = null;

        shutdownExecutor(EXECUTOR_NAME, scheduler);
        scheduler = null;
        shutdownPlayerExecutors();
        mdbPlayerMap.clear();

        if (allTables.size() > 0) {
            for (Tables tables : allTables) {
                tables.close();
            }
            allTables.clear();
        }

        running = false;
        logger.info("@@@@@@@@@@@@@@@@  mdb stop end   @@@@@@@@@@@@@@@@");
    }

    private void shutdownExecutor(String executorName, ExecutorService executor) {
        if (executor == null) {
            return;
        }
        try {
            ConcurrentEnvironment.getInstance().shutdown(executorName);
        } catch (Throwable e) {
            logger.error("shutdown executor error name {}", executorName, e);
            try {
                executor.shutdown();
            } catch (Throwable ex) {
                logger.error("fallback shutdown executor error name {}", executorName, ex);
            }
        }
    }

    private static synchronized void initPlayerExecutor() {
        if (!playerExecutor.isEmpty()) {
            return;
        }
        for (int i = 0; i < EXECUTOR_PLAYER_THREAD_COUNT; i++) {
            playerExecutor.add(ConcurrentEnvironment.getInstance().newFixedThreadPool("mdb.player.executor." + i, 1));
        }
    }

    private static synchronized void shutdownPlayerExecutors() {
        for (int i = 0; i < playerExecutor.size(); i++) {
            ThreadPoolExecutor executor = playerExecutor.get(i);
            if (executor == null) {
                continue;
            }
            try {
                ConcurrentEnvironment.getInstance().shutdown("mdb.player.executor." + i);
            } catch (Throwable e) {
                logger.error("shutdown player executor error index {}", i, e);
                executor.shutdown();
            }
        }
        playerExecutor.clear();
    }

    private void executeAsync(Runnable runnable) {
        if (scheduler == null) {
            runnable.run();
            return;
        }
        scheduler.execute(runnable);
    }



    public boolean isxBeanDetailCheck() {
        return xBeanDetailCheck;
    }

    public void setxBeanDetailCheck(boolean xBeanDetailCheck) {
        this.xBeanDetailCheck = xBeanDetailCheck;
    }

    public long getCheckPointCount() {
        return checkPointCount;
    }

    public boolean isStrictCheckOpen() {
        return strictCheckOpen;
    }

    public void setStrictCheckOpen(boolean strictCheckOpen) {
        this.strictCheckOpen = strictCheckOpen;
    }

    @Deprecated
    public boolean isModifyTrue() {
        if ((checkPointCount & 1) == 0) {
            return true;
        }
        return false;
    }

    public void incrCheckPointCount() {
        this.checkPointCount = checkPointCount + 1;
    }

    public List<Tables> getAllTables() {
        return allTables;
    }


    // 自定义注入校验器
    private OperationChecker checker;

    public void register(OperationChecker checker) {
        this.checker = checker;
    }

    public boolean canOperate(Object key) {
        if(this.checker != null) {
            return this.checker.canOperate(key);
        }
        return true;
    }

    public long getPlayerFlushDelayMill() {
        return playerFlushDelayMill;
    }

    public void setPlayerFlushDelayMill(long playerFlushDelayMill) {
        this.playerFlushDelayMill = playerFlushDelayMill;
    }

    private void putPlayerThread(Long playerId, Consumer<Collection> consumer) {
        putPlayerThread(Collections.singletonList(playerId), consumer);
    }
    private void putPlayerThread(Collection playerIds, Consumer<Collection> consumer) {
        if (playerIds.size() <= 0) {
            return;
        }
        initPlayerExecutor();
        Object o = playerIds.stream().findAny().get();
        if (!(o instanceof Long)) {
            playerExecutor.get(0).execute(() -> consumer.accept(playerIds));
            return;
        }

        int size = playerExecutor.size();
        Map<Integer, List<Long>> collect = ((Collection<Long>) playerIds).stream().collect(Collectors.groupingBy(Mdb::hash));
        for (Map.Entry<Integer, List<Long>> entry : collect.entrySet()) {
            ThreadPoolExecutor threadPoolExecutor = playerExecutor.get(Math.floorMod(entry.getKey(), size));
            threadPoolExecutor.execute(() -> consumer.accept(entry.getValue()));
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    /**
     * 每秒检测玩家状态
     */
    private void mdbPlayerTick(Collection<Long> playerId) {
        long currMill = System.currentTimeMillis();
        for (Long l : playerId) {
            MdbPlayerInfo mdbPlayerInfo = mdbPlayerMap.get(l);
            if (mdbPlayerInfo == null) {
                continue;
            }
            // 状态不对
            if (mdbPlayerInfo.getState() != MdbPlayerInfo.flush_wait) {
                continue;
            }
            if (currMill < mdbPlayerInfo.getFlushStartTime()) {
                continue;
            }
            mdbPlayerInfo.setState(MdbPlayerInfo.flush_ing);
            // 进行flush
            executeAsync(()->_flush(l, flushFinishCallBack()));
        }
    }

    /**
     * 检测是否成功;成功删除数据;失败重新设置为flush_wait;重新等待时间
     */
    private MdbPlayerFinishCallback flushFinishCallBack() {
        return new MdbPlayerFinishCallback() {
            @Override
            public void callback(Object key, boolean allSuccess, Exception exception) {
                long currMill = System.currentTimeMillis();
                // 这里已经回到了 player线程
                Long playerId = (Long) key;
                MdbPlayerInfo mdbPlayerInfo = mdbPlayerMap.get(playerId);
                if (mdbPlayerInfo == null) {
                    return;
                }

                if (allSuccess) {
                    mdbPlayerInfo.setState(MdbPlayerInfo.flush_finish);
                    mdbPlayerMap.remove(playerId);
                } else {
                    mdbPlayerInfo.setState(MdbPlayerInfo.flush_wait);
                    // 延迟1.5倍，继续flush
                    mdbPlayerInfo.setFlushStartTime(currMill + (long) (playerFlushDelayMill * 1.5d));
                }
                if (mdbPlayerInfo.getMdbFlushCallback() != null) {
                    mdbPlayerInfo.getMdbFlushCallback().onFlushOver(mdbPlayerInfo, allSuccess, exception);
                }
            }
        };
    }

    /**
     * 检测是否成功;成功进入load_finish;检查是否有离线时间;有的话直接进入flush_wait;
     * 失败-进行flush操作;因为有可能加载了一半;
     */
    private MdbPlayerFinishCallback loadFinishCallBack() {
        return new MdbPlayerFinishCallback() {
            @Override
            public void callback(Object key, boolean allSuccess, Exception exception) {
                long currMill = System.currentTimeMillis();
                // 这里已经回到了 player线程
                Long playerId = (Long) key;
                MdbPlayerInfo mdbPlayerInfo = mdbPlayerMap.get(playerId);
                if (mdbPlayerInfo == null) {
                    return;
                }

                if (allSuccess) {
                    // 成功进入load_finish;检查是否有离线时间;有的话直接进入flush_wait;
                    mdbPlayerInfo.setState(MdbPlayerInfo.load_finish);
                    if (mdbPlayerInfo.getOfflineTime() > 0) {
                        mdbPlayerInfo.setState(MdbPlayerInfo.flush_wait);
                        mdbPlayerInfo.setFlushStartTime(currMill + playerFlushDelayMill);
                    }
                } else {
                    // 失败-进行flush操作;因为有可能加载了一半;
                    mdbPlayerInfo.setState(MdbPlayerInfo.flush_wait);
                    mdbPlayerInfo.setFlushStartTime(currMill + playerFlushDelayMill);
                }
                if (mdbPlayerInfo.getMdbLoadCallback() != null) {
                    mdbPlayerInfo.getMdbLoadCallback().callback(mdbPlayerInfo, allSuccess, exception);
                }
            }
        };
    }
}
