package com.mdb;

import com.mdb.entity.FourTuple;
import com.mdb.util.ConcurrentEnvironment;
import com.mdb.util.TimeCostPrint;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Mdb {
    public static Logger logger = LoggerFactory.getLogger(Mdb.class.getName());
    private static final String EXECUTOR_NAME = "mdb.scheduler";
    private static final long CLEAR_CACHE_DELAY_SECONDS = 10 * TimeCostPrint.minuteMills / 1000;
    private static final int VISIT_DB_EXECUTOR_THREAD_COUNT = Integer.getInteger("mdb.visit.db.executor", 4);

    public Mdb() {
    }

    private volatile CopyOnWriteArrayList<Tables> allTables = new CopyOnWriteArrayList<>(); // 为了支持在一个进程中开启多个mdb
    private final ConcurrentHashMap<Class<?>, TTable<?, ?>> class2TableMap = new ConcurrentHashMap<>();
    private final List<ThreadPoolExecutor> visitDBExecutor = new ArrayList<>();

    // xBean 是否开启全量检测
    private boolean xBeanDetailCheck = false;
    // xBean 是否开启严格检测
    private boolean strictCheckOpen = true;
    // checkPoint前+1
    private volatile long checkPointCount = 0;

    public boolean running = false;
    private volatile boolean closing = false;

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
        closing = false;

        try {
            Class.forName(XBean.class.getName());
            Class<?> tableClass = Class.forName("table." + mdbMetadata.getName() + "._Tables_");
            Constructor<?> constructor = tableClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object tableOwner = constructor.newInstance();
            Tables tables = new Tables();
            allTables.add(tables);
            Class<?>[] classes = tableClass.getDeclaredClasses();
            for (Class<?> aClass : classes) {
                if (!TTable.class.isAssignableFrom(aClass)) {
                    continue;
                }
                TTable<?, ?> tTable = createTableInstance(tableOwner, tableClass, aClass);
                tables.add(tTable);
                TTable<?, ?> old = class2TableMap.putIfAbsent(aClass, tTable);
                if (old != null) {
                    logger.error("aClass 重复！！！！ {} ", aClass);
                }
            }
            tables.open(this, mdbMetadata);
            logger.warn("{} has {} tables ......", mdbMetadata.getName(), tables.tables.size());
            // checkpoint
            if (checkpoint == null) {
                scheduler = ConcurrentEnvironment.getInstance().newScheduledThreadPool(EXECUTOR_NAME, 1);
                if (visitDBExecutor.isEmpty()) {
                    for (int i = 0; i < VISIT_DB_EXECUTOR_THREAD_COUNT; i++) {
                        visitDBExecutor.add(ConcurrentEnvironment.getInstance().newFixedThreadPool("mdb.visit.db.executor." + i, 1));
                    }
                }
                // 关闭后，不再执行“延迟任务”（delay/一次性定时）
                scheduler.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
                // 关闭后，不再继续执行“周期任务”（scheduleAtFixedRate / scheduleWithFixedDelay）
                scheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
                // 任务 cancel 后，立即从队列移除
                scheduler.setRemoveOnCancelPolicy(true);
                checkpoint = new Checkpoint(this);
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

    @SuppressWarnings("unchecked")
    private TTable<?, ?> createTableInstance(Object tableOwner, Class<?> tableClass, Class<?> nestedTableClass) throws Exception {
        Constructor<?> tableConstructor;
        if (Modifier.isStatic(nestedTableClass.getModifiers())) {
            tableConstructor = nestedTableClass.getDeclaredConstructor();
            tableConstructor.setAccessible(true);
            return (TTable<?, ?>) tableConstructor.newInstance();
        }
        tableConstructor = nestedTableClass.getDeclaredConstructor(tableClass);
        tableConstructor.setAccessible(true);
        return (TTable<?, ?>) tableConstructor.newInstance(tableOwner);
    }


    /**
     * 直接玩家线程加载,然后抛到db线程get,然后再回调回来;不管失败;当然如果报错了 就算失败不让进;
     * 不 这个的直接加载,不然就要吧get方法拆开了;这里要么就直接加载吧;get拆开其cache就有问题;
     * 感觉不要这个方法算了;
     */
   /* public void loadPlayerAllTableToMemory(Object key) {
        logger.info("loadPlayerAllTableToMemory key {}", key);
        try {
            for (Tables allTable : allTables) {
                for (TTable table : allTable.getTables()) {
                    if (!table.isSupportFlush()) {
                        continue;
                    }
                    table.get(key);
                }
            }
        } catch (Exception e) {
            logger.error("Mdb loadPlayerAllTableToMemory error key {} ", key, e);
            throw new MdbError(e);
        }
    }*/

    /**
     * flush 失败以后 需要业务层找机会再次调用;
     * 这里的flush按理来说应该是要一步的
     *
     * 业务层必须有重试功能;
     */
    public void flush(Object key, MdbFlushCallback callback) {
        if (isClosing()) {
            if (callback != null) {
                callback.onFlushOver(key, false, new IllegalStateException("mdb is closing"));
            }
            return;
        }
        flushLogicThread(key, callback);
    }

    /**
     *  直接本地进行序列化，然后抛到db线程进行存储，然后再抛回来进行删除数据;
     *  如果在这个期间玩家重新登录的话，那是不是就不应该删除呢?所以来一个function判断是否删除该数据就行了;这个先不弄吧;
     *  这里flush失败就失败了，clearCache里会保底，这里的clearCache里也会清理可以flush的数据，如果不能清理就麻烦了，比如flush失败以后预约下次flush;
     *  当然也可以业务层再次预约，看实现;底层只需要保证完善就行;
     */
    private void flushLogicThread(Object key, MdbFlushCallback callback) {
            logger.info("flush flushLogicThread start key {}", key);
            boolean allSuccess = true;
        try {
            List<FourTuple<TRecord, Integer, String, String>> jsonList = new ArrayList<>();
            for (Tables allTable : allTables) {
                boolean detect = allTable.getStorage().detect();
                if (!detect) {
                    logger.error("flush 和数据库的连接已经的断开！！！ tablesName {} ", allTable.getName());
                    allSuccess = false;
                    continue;
                }
                for (TTable table : allTable.tables.values()) {
                    TTable tt = table;
                    if (!tt.isSupportFlush()) {
                        continue;
                    }

                    TRecord tRecord = tt.getTRecordByCache(key);
                    if (tRecord == null) {
                        continue;
                    }

                    // 没有修改过
                    if (!tRecord.isModified(this, false)) {
                        tt.deleteCache(key);
                        continue;
                    }
                    // 修改过了
                    jsonList.add(new FourTuple<>(tRecord, tRecord.getState(), tRecord.marshalKey(), tRecord.marshalValue()));
                }
            }

            if (jsonList.isEmpty()) {
                if (callback != null) {
                    callback.onFlushOver(key, allSuccess, null);
                }
                return;
            }
            // 抛到DB线程进行存储
            if (!putDBExecutor(a -> flushMdbSaveDB(jsonList, key, callback))) {
                if (callback != null) {
                    callback.onFlushOver(key, false, new IllegalStateException("mdb is closing"));
                }
            }
        } catch (Exception e) {
            if (callback != null) {
                callback.onFlushOver(key, false, e);
            }
        }
    }

    private void flushMdbSaveDB(List<FourTuple<TRecord, Integer, String, String>> jsonList, Object key, MdbFlushCallback mdbFlushCallback) {
        logger.info("flush flushMdbSaveDB start key {} jsonListSize {} ", key, jsonList.size());

        boolean allSuccess = true;
        try {
            ArrayList<TRecord> successTRecord = new ArrayList<>();
            for (FourTuple<TRecord, Integer, String, String> entry : jsonList) {
                TRecord one = entry.getOne();
                int flushState = entry.getTwo();
                String jsonKey = entry.getThree();
                String jsonValue = entry.getFour();

                boolean flush = one.flush("flush", flushState, jsonKey, jsonValue);
                if (!flush) {
                    allSuccess = false;
                    continue;
                }
                successTRecord.add(one);
            }

            logger.info("flush flushMdbSaveDB summary key {} jsonListSize {} successTRecordSize {} ", key, jsonList.size(), successTRecord.size());

            TRecord tRecordByThreaName = jsonList.get(0).getOne();
            boolean finalAllSuccess = allSuccess;
            getMdbInterface().execute(tRecordByThreaName.getTable().getSerializeLogicThreadName(), key, () -> {
                boolean success = finalAllSuccess;
                // 将保存到mysql成功的进行删除cache;
                for (TRecord record : successTRecord) {
                    TRecord currTRecord = record.getTable().getTRecordByCache(record.getKey());
                    // cache里的不是当前的record了;
                    if (currTRecord != record) {
                        success = false;
                        continue;
                    }
                    // 修改过了
                    if (record.isModified(this, false)) {
                        success = false;
                        continue;
                    }

                    record.getTable().deleteCache(record.getKey());
                }

                if(success) {
                    // 这里再检查一遍;
                    for (Tables allTable : allTables) {
                        for (TTable table : allTable.tables.values()) {
                            TTable tt = table;
                            if (!tt.isSupportFlush()) {
                                continue;
                            }

                            TRecord t = tt.getTRecordByCache(key);
                            if (t == null) {
                                continue;
                            }
                            // 还有没删除的！！！
                            success = false;
                            break;
                        }
                    }
                }
                // 这里的success  true;就代表数据库里已经没有改key的数据了;可以更换服务器了;
                if (mdbFlushCallback != null) {
                    mdbFlushCallback.onFlushOver(key, success, null);
                }
            });

        } catch (Exception e) {
            if (mdbFlushCallback != null) {
                mdbFlushCallback.onFlushOver(key, false, e);
            }
        }
    }



    public void tick() {
        long currTime = System.currentTimeMillis();
        for (Tables allTable : allTables) {
            for (TTable table : allTable.getTables()) {
                table.tick(currTime);
            }
        }

    }

    private void clearCacheRound() {
        for (Tables allTable : allTables) {
            for (TTable table : allTable.getTables()) {
                // 支持flush 说明是玩家个人数据; 不清理让玩家手动调用flush清理
                if (!table.isSupportFlush()) {
                    table.tickClearCache();
                }
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
        closing = true;
        logger.info("@@@@@@@@@@@@@@@@  mdb stop begin   @@@@@@@@@@@@@@@@");
        shutdownExecutorNow(EXECUTOR_NAME, scheduler);
        scheduler = null;
        shutdownVisitDBExecutors();
        if (checkpoint != null && runCheckpoint) {
            try {
                logger.info("checkpoint on close begin");
                checkpoint.checkpoint(System.currentTimeMillis(), true);
                logger.info("checkpoint on close end");
            } catch (Throwable e) {
                logger.error("checkpoint on close error", e);
            }
        }
        checkpoint = null;

        if (allTables.size() > 0) {
            for (Tables tables : allTables) {
                tables.close();
            }
            allTables.clear();
        }
        class2TableMap.clear();

        running = false;
        logger.info("@@@@@@@@@@@@@@@@  mdb stop end   @@@@@@@@@@@@@@@@");
    }

    private void shutdownExecutorNow(String executorName, ExecutorService executor) {
        if (executor == null) {
            return;
        }
        logger.info("shutdownNow executor begin name {}", executorName);
        try {
            ConcurrentEnvironment.getInstance().shutdown(executorName);
        } catch (Throwable e) {
            logger.error("shutdown executor error name {}", executorName, e);
        }
        try {
            executor.shutdownNow();
        } catch (Throwable e) {
            logger.error("shutdownNow executor error name {}", executorName, e);
        }
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                logger.error("awaitTermination timeout name {} seconds {}", executorName, 60);
            }
        } catch (InterruptedException e) {
            logger.error("awaitTermination interrupted name {} seconds {}", executorName, 60, e);
        }
        logger.info("shutdownNow executor end name {}", executorName);
    }

    private synchronized void shutdownVisitDBExecutors() {
        for (int i = 0; i < visitDBExecutor.size(); i++) {
            ThreadPoolExecutor executor = visitDBExecutor.get(i);
            if (executor == null) {
                continue;
            }
            shutdownExecutorNow("mdb.visit.db.executor." + i, executor);
        }
        visitDBExecutor.clear();
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

    @SuppressWarnings("unchecked")
    public <K, V> TTable<K, V> getTTable(Class<? extends TTable<K, V>> clazz) {
        return (TTable<K, V>) class2TableMap.get(clazz);
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


    public boolean putDBExecutor(Consumer<Collection<?>> consumer) {
        // 这里暂时这么写,保证每个逻辑线程的数据保证被单个db线程处理;
        return putDBExecutor(Thread.currentThread().getId(), consumer);
    }

    private boolean putDBExecutor(Object key, Consumer<Collection<?>> consumer) {
        return putDBExecutor(Collections.singletonList(key), consumer);
    }

    /**
     * mdb模块是通过table的读写锁来保证互斥的;防止出现同一个数据库类，我再存你也在存，防止出现老的数据库覆盖新的数据库类的情况;
     * flush和checkpoint和clearcache全部互斥;
     * flush加的是读锁,因为flush一次性有多个人可能会刷新; clearcache加的是写锁,因为读锁已经被flush用了没办法了;checkpoint就不用想了加的肯定是写锁;
     * 这里不会有老数据库覆盖新数据库的问题;因为flush和clearcache只要运行了就会把cache里的数据删除; checkpoint不删,但是这里只是凑巧而已
     *
     * 这里为了解决上面说的问题，将其修改成了将key每次%,保证同key的顺序执行，不会有 脏数据的问题
     */
    private boolean putDBExecutor(Collection<?> keys, Consumer<Collection<?>> consumer) {
        if (keys.size() <= 0) {
            return true;
        }
        if (closing) {
            logger.warn("mdb is closing, ignore db task {}", keys);
            return false;
        }
        if (visitDBExecutor.isEmpty()) {
            logger.warn("mdb visit db executor is empty, ignore task {}", keys);
            return false;
        }

        int size = visitDBExecutor.size();
        Map<Integer, ? extends List<?>> collect = keys.stream().collect(Collectors.groupingBy(Mdb::hash));
        for (Map.Entry<Integer, ? extends List<?>> entry : collect.entrySet()) {
            ThreadPoolExecutor threadPoolExecutor = visitDBExecutor.get(Math.floorMod(entry.getKey(), size));
            threadPoolExecutor.execute(() -> consumer.accept(entry.getValue()));
        }
        return true;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 业务线程序列化
     */
    private MdbInterface mdbInterface;

    public void registerLogicThreadSerialize(MdbInterface logicThreadSerialize) {
        mdbInterface = logicThreadSerialize;
    }

    public MdbInterface getMdbInterface() {
        return mdbInterface;
    }

    public boolean isClosing() {
        return closing;
    }

}
