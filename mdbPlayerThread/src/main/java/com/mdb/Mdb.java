package com.mdb;

import com.mdb.exception.MdbError;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
     * 加载玩家的所有table到内存
     * 可以在玩家登录的时候异步调用
     */
    public void loadPlayerAllTableToMemory(Object key) {
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
    }

    /**
     * flush 失败以后 需要业务层找机会再次调用;
     * 这里的flush按理来说应该是要一步的
     *
     * 业务层必须有重试功能;
     */
    public void flush(Object key, boolean sync, MdbFlushCallback callback) {
        if (sync) {
            _flush(key, callback);
            return;
        }

        scheduler.execute(() -> _flush(key, callback));
    }

    private boolean _flush(Object key, MdbFlushCallback callback) {
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
            if (callback != null) {
                callback.onFlushOver(key, allSuccess, null);
            }
        } catch (Exception e) {
            // 如果失败抛出异常，业务层应该下次还让玩家再次进入这个进程，不然会有脏数据的问题; 业务层也可以尝试进行重试
            if (callback != null) {
                callback.onFlushOver(key, false, e);
            }
        }
        return allSuccess;
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
        shutdownVisitDBExecutors();

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

    private synchronized void shutdownVisitDBExecutors() {
        for (int i = 0; i < visitDBExecutor.size(); i++) {
            ThreadPoolExecutor executor = visitDBExecutor.get(i);
            if (executor == null) {
                continue;
            }
            try {
                ConcurrentEnvironment.getInstance().shutdown("mdb.visit.db.executor." + i);
            } catch (Throwable e) {
                logger.error("shutdown visit db executor error index {}", i, e);
                executor.shutdown();
            }
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

    public void putDBExecutor(Object key, Consumer<Collection> consumer) {
        putDBExecutor(Collections.singletonList(key), consumer);
    }

    @SuppressWarnings("unchecked")
    public void putDBExecutor(Collection keys, Consumer<Collection> consumer) {
        if (keys.size() <= 0) {
            return;
        }
        if (visitDBExecutor.isEmpty()) {
            logger.warn("mdb visit db executor is empty, ignore task {}", keys);
            return;
        }

        int size = visitDBExecutor.size();
        Map<Object, List<Long>> collect = ( keys).stream().collect(Collectors.groupingBy(Mdb::hash));
        for (Map.Entry<Object, List<Long>> entry : collect.entrySet()) {
            ThreadPoolExecutor threadPoolExecutor = visitDBExecutor.get(Math.floorMod(entry.getKey(), size));
            threadPoolExecutor.execute(() -> consumer.accept(entry.getValue()));
        }
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
}
