package com.mdb;

import com.mdb.stoarge.StorageEngine;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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


    public synchronized void start(MdbMetadata mdbMetadata) {

    }



    public boolean flush(Object key, MdbFlushCallback mdbFlushCallback) {
        boolean allSuccess = true;
        long startTime = System.currentTimeMillis();
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
        logger.info("Flush all tables in key {} {} ms", key, System.currentTimeMillis() - startTime);
        if (mdbFlushCallback != null) {
            mdbFlushCallback.onFlushOver(key);
        }
        return allSuccess;
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
