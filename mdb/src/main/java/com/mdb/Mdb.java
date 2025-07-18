package com.mdb;

import com.mdb.utils.ConcurrentEnvironment;
import com.mdb.utils.TimeCostPrint;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Mdb {
    public static Logger logger = LoggerFactory.getLogger(Mdb.class.getName());
    private static Mdb instance = new Mdb();

    public static Mdb getInstance() {
        return instance;
    }

    private Mdb() {
    }

    private volatile CopyOnWriteArrayList<Tables> allTables = new CopyOnWriteArrayList<>(); // 为了支持在一个进程中开启多个mdb

    // xBean 是否开启全量检测
    private boolean xBeanDetailCheck = false;
    // checkPoint前+1
    private volatile long checkPointCount = 0;

    public boolean running = false;
    public boolean close = false;

    private Checkpoint checkpoint = null;
    ScheduledThreadPoolExecutor scheduled_checkPoint;

    public synchronized void start(MdbMetadata mdbMetadata) throws Exception {
        logger.warn("@@@@@@@@@@@@@@@@ mdb start begin @@@@@@@@@@@@@@@@ mdb metadata = " + mdbMetadata.getName());

        try {
            Class.forName("warrior.mdb.XBean");
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
                scheduled_checkPoint = ConcurrentEnvironment.getInstance().newScheduledThreadPool("mdb.scheduler.checkpoint", 1);
                checkpoint = new Checkpoint(mdbMetadata, scheduled_checkPoint);
            }

            logger.warn("@@@@@@@@@@@@@@@@  mdb start end  @@@@@@@@@@@@@@@@");
            running = true;
        } catch (Throwable e) {
            logger.error("Mdb start error", e);
            close();
            throw e;
        }

    }


    /**
     * flush 失败以后 需要业务层找机会再次调用
     */
    public boolean flush(Object key, MdbFlushCallback mdbFlushCallback) {
        boolean allSuccess = true;
        TimeCostPrint timeCostPrint = new TimeCostPrint(logger, 20, "flush");
        for (Tables allTable : allTables) {
            for (TTable table : allTable.tables.values()) {
                TTable tt = table;
                if (tt.isSupportFlush()) {
                    // 名义上增加只读锁，其实是flush操作，目的是为了和checkpoint的write锁互斥，同时又保证多玩家在没有checkpoint时flush自己数据能并发执行
                    tt.readlock.lock();
                    try {
                        if(!tt.flush(key)) {
                            allSuccess = false;
                        }
                    } finally {
                        tt.readlock.unlock();
                    }
                }
            }
        }
        timeCostPrint.print();
        if (mdbFlushCallback != null) {
            mdbFlushCallback.onFlushOver(key);
        }
        return allSuccess;
    }



    public void tick() {
        for (Tables allTable : allTables) {
            for (TTable table : allTable.getTables()) {
                table.tick();
            }
        }
    }

    public void close() {

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


}
