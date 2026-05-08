package com.mdb;

import com.mdb.util.TimeCostPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Checkpoint implements Runnable {
    public static Logger logger = LoggerFactory.getLogger(Checkpoint.class);

    Mdb mdb;

    private ReentrantLock checkpointMutex = new ReentrantLock();

    public Checkpoint(Mdb mdb) {
        this.mdb = mdb;
    }

    @Override
    public void run() {
        checkpoint(System.currentTimeMillis(), false);
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

        // 关闭的时候进行全量检测;可以顺便检测我这个 version 透传问题大不大
        if(close) {
            mdb.setxBeanDetailCheck(true);
        }
        // 防止同时运行多个checkpoint
        checkpointMutex.lock();
        try {
            long checkPointCount = mdb.getCheckPointCount();
            mdb.incrCheckPointCount();
            logger.info("checkpoint_all Start count: {}", mdb.getCheckPointCount());
            if (!close) {
                // 开启了才进行暂停;不然不需要暂停
                if (mdb.isxBeanDetailCheck()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error("", e);
                    }
                }
            }
            TimeCostPrint timeCostPrint = new TimeCostPrint(logger, 5 * 60, "checkpoint_all");
            int addCount = 0;
            int modifyCount = 0;
            int removeCount = 0;
            for (Tables allTable : mdb.getAllTables()) {
                boolean detect = allTable.getStorage().detect();
                if (!detect) {
                    logger.error("checkpoint 和数据库的连接已经的断开！！！ tablesName {} ", allTable.getName());
                    continue;
                }

                for (TTable value : allTable.tables.values()) {
                    addCache.clear();
                    modifyCache.clear();
                    removeCache.clear();
                    addTRecordCache.clear();
                    modifyTRecordCache.clear();
                    removeTRecordCache.clear();
                    value.checkpoint(checkPointCount, addCache, modifyCache, removeCache, addTRecordCache, modifyTRecordCache, removeTRecordCache, close);
                    addCount = addCount + addCache.size();
                    modifyCount = modifyCount + modifyCache.size();
                    removeCount = removeCount + removeCache.size();
                }
            }
            timeCostPrint.print();
            logger.info("checkpoint_all End checkPointCount {}  addCount {} modifyCount {} removeCount {} ", checkPointCount, addCount, modifyCount, removeCount);

        } finally {
            checkpointMutex.unlock();
        }
    }
}
