package com.mdb;

import java.util.concurrent.locks.ReentrantLock;

public class Checkpoint implements Runnable {
    Tables tables;

    private ReentrantLock checkpointMutex = new ReentrantLock();

    @Override
    public void run() {

    }


    /**
     * 是否是关闭服务器调用过来的
     */
    public void checkpoint(long now, boolean close) {
        // 1.将所有remove add 和 modify 的放到一个新的集合里面; 然后集合统一同步到数据库里面;

        checkpointMutex.lock();

        for (TTable value : tables.tables.values()) {

        }
    }
}
