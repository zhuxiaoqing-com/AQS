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
    ScheduledThreadPoolExecutor accessDBScheduler;

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
                accessDBScheduler = ConcurrentEnvironment.getInstance().newScheduledThreadPool(EXECUTOR_NAME, EXECUTOR_THREAD_COUNT);
                // 每次初始化一个线程
                if (playerExecutor.isEmpty()) {
                    for (int i = 0; i < EXECUTOR_PLAYER_THREAD_COUNT; i++) {
                        playerExecutor.add(ConcurrentEnvironment.getInstance().newFixedThreadPool("mdb.player.executor." + i, 1));
                    }
                }
                // 关闭后，不再执行“延迟任务”（delay/一次性定时）
                accessDBScheduler.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
                // 关闭后，不再继续执行“周期任务”（scheduleAtFixedRate / scheduleWithFixedDelay）
                accessDBScheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
                // 任务 cancel 后，立即从队列移除
                accessDBScheduler.setRemoveOnCancelPolicy(true);
                checkpoint = new Checkpoint();
                accessDBScheduler.scheduleWithFixedDelay(checkpoint, 0, mdbMetadata.getCheckPointPeriod(), TimeUnit.MILLISECONDS);
                accessDBScheduler.scheduleWithFixedDelay(this::tick, 1, 1, TimeUnit.SECONDS);
                accessDBScheduler.scheduleWithFixedDelay(this::clearCacheRound,
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
            logger.info("玩家mdb加载请求 playerId {} 是否已有状态 {} 参数 {} state {}",
                    playerId, mdbPlayerInfo != null, param, mdbPlayerInfo != null ? mdbPlayerInfo.getState() : 0);
            if (mdbPlayerInfo == null) {
                mdbPlayerInfo = new MdbPlayerInfo(key, param, MdbPlayerInfo.load_ing);
                mdbPlayerInfo.setMdbLoadCallback(callback);
                mdbPlayerMap.put(key, mdbPlayerInfo);
                logger.info("玩家mdb信息创建 playerId {} 状态 {} 参数 {}", playerId, MdbPlayerInfo.stateName(mdbPlayerInfo.getState()), param);
                // 没有数据：设置为wait_load状态，异步进行load;异步Load开始设置load_ing状态;load_finish以后回调load_finish接口;
                logger.info("玩家mdb提交异步加载 playerId {}", playerId);
                accessDBScheduler.execute(()->_loadPlayerAllTableToMemory(key, loadFinishCallBack()));
                return;
            }
            mdbPlayerInfo.setMdbLoadCallback(callback);
            switch (mdbPlayerInfo.getState()) {
                case MdbPlayerInfo.load_ing:
                    // 不处理;直接return;
                    logger.info("玩家mdb忽略加载请求 playerId {} 状态 {}", playerId, MdbPlayerInfo.stateName(mdbPlayerInfo.getState()));
                    break;
                case MdbPlayerInfo.load_finish:
                    // 再调用一次吧;业务线程会进行处理重复的,防止bug导致玩家永远也上不了线
                    logger.info("玩家mdb再次触发加载回调 playerId {} 状态 {}", playerId, MdbPlayerInfo.stateName(mdbPlayerInfo.getState()));
                    loadFinishCallBack().callback(playerId,  true, null);
                    break;
                case MdbPlayerInfo.flush_wait:
                    // 直接设置为load_finish;
                    changePlayerState(mdbPlayerInfo, MdbPlayerInfo.load_finish, "load_cancel_flush_wait");
                    // 清除离线时间状态;
                    long oldOfflineTime = mdbPlayerInfo.getOfflineTime();
                    mdbPlayerInfo.setOfflineTime(0);
                    logger.info("玩家mdb离线时间变化 playerId {} {} -> {} 原因 {}",
                            mdbPlayerInfo.getPlayerId(), oldOfflineTime, (long) 0, "load_cancel_flush_wait");
                    if (callback != null) {
                        logger.info("玩家mdb加载回调 playerId {} 成功 true 原因 load_cancel_flush_wait", playerId);
                        callback.callback(mdbPlayerInfo, true, null);
                    }
                    break;
                case MdbPlayerInfo.flush_ing:
                    // 错误码;进行像客户端提示说正在加载数据中请稍后重试;
                    if (callback != null) {
                        logger.info("玩家mdb加载回调 playerId {} 成功 false 原因 flushing", playerId);
                        callback.callback(mdbPlayerInfo, false, new IllegalStateException("player is flushing"));
                    }
                    break;
                case MdbPlayerInfo.flush_finish:
                    // 不会有这个状态;flush_ing结束后直接就删数据了;
                    logger.info("玩家mdb忽略加载请求 playerId {} 状态 {}", playerId, MdbPlayerInfo.stateName(mdbPlayerInfo.getState()));
                    break;
            }
        });
    }



    private void _loadPlayerAllTableToMemory(Long key, MdbPlayerFinishCallback callback) {
        logger.info("loadPlayerAllTableToMemory key {}", key);
        try {
            TimeCostPrint timeCostPrint = new TimeCostPrint(logger, 20, "loadPlayerAllTableToMemory");
            for (Tables allTable : allTables) {
                for (TTable table : allTable.getTables()) {
                    if (!table.isSupportFlush()) {
                        continue;
                    }
                    logger.info("玩家mdb加载表数据 playerId {} table {}", key, table.getName());
                    table.loadToCache(key);
                }
            }
            timeCostPrint.print();
            logger.info("玩家mdb异步加载完成 playerId {} 成功 true", key);
            putPlayerThread(key, a -> callback.callback(key, true, null));
        } catch (Exception e) {
            logger.error("Mdb loadPlayerAllTableToMemory error key {} ", key, e);

            // 异步加载报错;将其加载出来的数据全部删除;
            for (Tables allTable : allTables) {
                for (TTable table : allTable.getTables()) {
                    if (!table.isSupportFlush()) {
                        continue;
                    }
                    table.deleteCache(key);
                }
            }

            putPlayerThread(key, a -> callback.callback(key, false, e));
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
            logger.info("玩家mdb刷库请求 playerId {} 是否已有状态 {} 离线时间 {} state {} ",
                    playerId, mdbPlayerInfo != null, offlineTime, mdbPlayerInfo != null ? mdbPlayerInfo.getState() : 0);
            if (mdbPlayerInfo == null) {
                logger.error("mdb flush的时候没有MdbPlayerInfo数据");
                if (callback != null) {
                    callback.onFlushOver(null, false, null);
                }
                return;
            }
            mdbPlayerInfo.setMdbFlushCallback(callback);
            long oldOfflineTime = mdbPlayerInfo.getOfflineTime();
            mdbPlayerInfo.setOfflineTime(offlineTime);
            logger.info("玩家mdb离线时间变化 playerId {} {} -> {} 原因 {}",
                    mdbPlayerInfo.getPlayerId(), oldOfflineTime, offlineTime, "flush_request");

            switch (mdbPlayerInfo.getState()) {
                case MdbPlayerInfo.load_wait:
                    // 这里设置flush_ing，flushFinishCallBack().callback() 里会将其设置为flush_finish状态
                    changePlayerState(mdbPlayerInfo, MdbPlayerInfo.flush_ing, "flush_when_load_wait");
                    // 回调flush_finish
                    flushFinishCallBack().callback(key, true, null);
                    break;
                case MdbPlayerInfo.load_ing:
                    // 预约flush，在玩家数据里记录一个离线时间;load结束的时候如果有离线时间,就应该主动调用flush;
                    // 不处理 离线时间已经设置了;等load结束的时候检测离线时间就行了
                    logger.info("玩家mdb预约刷库 playerId {} 状态 {} 离线时间 {}", playerId, MdbPlayerInfo.stateName(mdbPlayerInfo.getState()), offlineTime);
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
                    changePlayerState(mdbPlayerInfo, MdbPlayerInfo.flush_wait, "flush_request");
                    setFlushStartTime(mdbPlayerInfo, currMs + playerFlushDelayMill, "flush_request");
                    break;
                case MdbPlayerInfo.flush_wait:
                    //直接不处理;修改离线时间;
                    logger.info("玩家mdb更新等待刷库 playerId {} 离线时间 {}", playerId, offlineTime);
                    break;
                case MdbPlayerInfo.flush_ing:
                    //直接不处理;修改离线时间;
                    logger.info("玩家mdb忽略刷库请求 playerId {} 状态 {} 离线时间 {}", playerId, MdbPlayerInfo.stateName(mdbPlayerInfo.getState()), offlineTime);
                    break;
                case MdbPlayerInfo.flush_finish:
                    //不处理;
                    logger.info("玩家mdb忽略刷库请求 playerId {} 状态 {}", playerId, MdbPlayerInfo.stateName(mdbPlayerInfo.getState()));
                    break;
            }
        });
    }


    private boolean _flush(Long key, MdbPlayerFinishCallback callback) {
        boolean allSuccess = true;
        try {
            logger.info("玩家mdb异步刷库开始 playerId {}", key);
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
                        logger.info("玩家mdb刷库表开始 playerId {} table {}", key, tt.getName());
                        // 名义上增加只读锁，其实是flush操作，目的是为了和checkpoint的write锁互斥，同时又保证多玩家在没有checkpoint时flush自己数据能并发执行
                        tt.readlock.lock();
                        try {
                            if (!tt.flush(key)) {
                                allSuccess = false;
                            }
                        } finally {
                            tt.readlock.unlock();
                        }
                        logger.info("玩家mdb刷库表完成 playerId {} table {} 当前是否全部成功 {}", key, tt.getName(), allSuccess);
                    }
                }
            }
            timeCostPrint.print();
            boolean finalAllSuccess = allSuccess;
            logger.info("玩家mdb异步刷库完成 playerId {} 成功 {}", key, finalAllSuccess);
            putPlayerThread(Collections.singletonList(key), a->callback.callback(key, finalAllSuccess, null));
        } catch (Exception e) {
            // 如果失败抛出异常，业务层应该下次还让玩家再次进入这个进程，不然会有脏数据的问题; 业务层也可以尝试进行重试
            logger.info("玩家mdb异步刷库完成 playerId {} 成功 false 异常 {}", key, e.toString());
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

        shutdownExecutor(EXECUTOR_NAME, accessDBScheduler);
        accessDBScheduler = null;
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

    public boolean canSupportFlushOperate(Object key, String tableName, String operation) {
        if (!(key instanceof Long)) {
            return true;
        }
        Long playerId = (Long) key;
        MdbPlayerInfo mdbPlayerInfo = mdbPlayerMap.get(playerId);
        if (mdbPlayerInfo != null && mdbPlayerInfo.getState() == MdbPlayerInfo.load_finish) {
            return true;
        }
        logger.error("玩家mdb操作不允许 playerId {} table {} operation {} state {}",
                playerId,
                tableName,
                operation,
                mdbPlayerInfo == null ? "null" : MdbPlayerInfo.stateName(mdbPlayerInfo.getState()));
        return false;
    }

    public long getPlayerFlushDelayMill() {
        return playerFlushDelayMill;
    }

    public void setPlayerFlushDelayMill(long playerFlushDelayMill) {
        this.playerFlushDelayMill = playerFlushDelayMill;
    }

    private void changePlayerState(MdbPlayerInfo mdbPlayerInfo, int newState, String reason) {
        int oldState = mdbPlayerInfo.getState();
        mdbPlayerInfo.setState(newState);
        logger.info("玩家mdb状态变化 playerId {} {} -> {} 原因 {}",
                mdbPlayerInfo.getPlayerId(),
                MdbPlayerInfo.stateName(oldState),
                MdbPlayerInfo.stateName(newState),
                reason);
    }

    private void setFlushStartTime(MdbPlayerInfo mdbPlayerInfo, long flushStartTime, String reason) {
        long oldFlushStartTime = mdbPlayerInfo.getFlushStartTime();
        mdbPlayerInfo.setFlushStartTime(flushStartTime);
        logger.info("玩家mdb刷库开始时间变化 playerId {} {} -> {} 原因 {}",
                mdbPlayerInfo.getPlayerId(), oldFlushStartTime, flushStartTime, reason);
    }

    private void putPlayerThread(Long playerId, Consumer<Collection> consumer) {
        putPlayerThread(Collections.singletonList(playerId), consumer);
    }
    private void putPlayerThread(Collection playerIds, Consumer<Collection> consumer) {
        if (playerIds.size() <= 0) {
            return;
        }
        if (playerExecutor.isEmpty()) {
            logger.warn("mdb player executor is empty, ignore player task {}", playerIds);
            return;
        }

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
            logger.info("玩家mdb定时检测到期刷库 playerId {} 当前时间 {} 刷库开始时间 {}", l, currMill, mdbPlayerInfo.getFlushStartTime());
            changePlayerState(mdbPlayerInfo, MdbPlayerInfo.flush_ing, "tick_flush_due");
            // 进行flush
            logger.info("玩家mdb提交异步刷库 playerId {}", l);
            accessDBScheduler.execute(()->_flush(l, flushFinishCallBack()));
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
                    logger.warn("mdb flush finish callback miss player info key {}", key);
                    return;
                }

                if (allSuccess) {
                    changePlayerState(mdbPlayerInfo, MdbPlayerInfo.flush_finish, "flush_finish_success");
                    mdbPlayerMap.remove(playerId);
                    logger.info("玩家mdb移除玩家信息 playerId {} 原因 flush_finish_success", playerId);
                } else {
                    changePlayerState(mdbPlayerInfo, MdbPlayerInfo.flush_wait, "flush_finish_fail");
                    // 延迟1.5倍，继续flush
                    setFlushStartTime(mdbPlayerInfo, currMill + (long) (playerFlushDelayMill * 1.5d), "flush_finish_fail");
                }
                if (mdbPlayerInfo.getMdbFlushCallback() != null) {
                    logger.info("玩家mdb刷库回调 playerId {} 成功 {}", playerId, allSuccess);
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
                    logger.warn("mdb load finish callback miss player info key {}", key);
                    return;
                }

                if (allSuccess) {
                    // 成功进入load_finish;检查是否有离线时间;有的话直接进入flush_wait;
                    changePlayerState(mdbPlayerInfo, MdbPlayerInfo.load_finish, "load_finish_success");
                    if (mdbPlayerInfo.getOfflineTime() > 0) {
                        changePlayerState(mdbPlayerInfo, MdbPlayerInfo.flush_wait, "load_finish_with_offline");
                        setFlushStartTime(mdbPlayerInfo, currMill + playerFlushDelayMill, "load_finish_with_offline");
                    }
                } else {
                    // 失败-进行flush操作;因为有可能加载了一半;
                    /*changePlayerState(mdbPlayerInfo, MdbPlayerInfo.flush_wait, "load_finish_fail");
                    setFlushStartTime(mdbPlayerInfo, currMill + playerFlushDelayMill, "load_finish_fail");*/
                    // 失败应该直接将数据都删掉吧，不需要flush了;这里只有没有过mdb对象的时候才有可能调用;也就是说如果原本里面有数据的话 不会进行load的
                    // 再load失败的时候就已经删了;这里只需要吧mdbPlayerInfo删了就行;
                    mdbPlayerMap.remove(playerId);
                    logger.info("玩家mdb加载回调 load失败删除 mdbPlayerInfo player {}", playerId);
                }
                if (mdbPlayerInfo.getMdbLoadCallback() != null) {
                    logger.info("玩家mdb加载回调 playerId {} 成功 {}", playerId, allSuccess);
                    mdbPlayerInfo.getMdbLoadCallback().callback(mdbPlayerInfo, allSuccess, exception);
                }
            }
        };
    }
}
