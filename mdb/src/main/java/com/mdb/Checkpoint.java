package com.mdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Checkpoint implements Runnable {
    public static Logger logger = LoggerFactory.getLogger(Checkpoint.class);
    private ReentrantLock checkpointMutex = new ReentrantLock();


    @Override
    public void run() {

    }


    HashMap<String, String> addCache = new HashMap<>();
    HashMap<String, String> modifyCache = new HashMap<>();
    HashMap<String, String> removeCache = new HashMap<>();
    List<TRecord> addTRecordCache = new ArrayList<>();
    List<TRecord> modifyTRecordCache = new ArrayList<>();
    List<TRecord> removeTRecordCache = new ArrayList<>();


    /**
     * 是否是关闭服务器调用过来的
     */
    public void checkpoint(long now, boolean close) {
        //checkpoint可以统一共用每次开始的时候将 版本号+1;
        //然后sleep(1000) 将等待异步全部修改完成

        // 防止同时运行多个checkpoint
        checkpointMutex.lock();
        try {
            long checkPointCount = Mdb.getInstance().getCheckPointCount();
            Mdb.getInstance().incrCheckPointCount();
            if (!close) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }

            for (Tables allTable : Mdb.getInstance().getAllTables()) {
                for (TTable value : allTable.tables.values()) {
                    addCache.clear();
                    modifyCache.clear();
                    removeCache.clear();
                    addTRecordCache.clear();
                    modifyTRecordCache.clear();
                    removeTRecordCache.clear();
                    value.checkpoint(checkPointCount, addCache, modifyCache, removeCache);
                }
            }


        } finally {
            checkpointMutex.unlock();
        }
    }
}
