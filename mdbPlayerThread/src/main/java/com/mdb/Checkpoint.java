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

    public Checkpoint(Mdb mdb) {
        this.mdb = mdb;
    }

    @Override
    public void run() {
        checkpoint(System.currentTimeMillis(), false);
    }

    /**
     * 是否是关闭服务器调用过来的
     */
    public void checkpoint(long now, boolean close) {
        //checkpoint可以统一共用每次开始的时候将 版本号+1;
        //然后sleep(1000) 将等待异步全部修改完成

        // 关闭的时候进行全量检测;可以顺便检测我这个 version 透传问题大不大
        if (close) {
            mdb.setxBeanDetailCheck(true);
        }
        mdb.incrCheckPointCount();
        logger.info("checkpoint_all Start count: {}", mdb.getCheckPointCount());


        for (Tables allTable : mdb.getAllTables()) {
            boolean detect = allTable.getStorage().detect();
            if (!detect) {
                logger.error("checkpoint 和数据库的连接已经的断开！！！ tablesName {} ", allTable.getName());
                continue;
            }

            for (TTable value : allTable.tables.values()) {
                value.checkpoint(close);
            }
        }
    }
}
