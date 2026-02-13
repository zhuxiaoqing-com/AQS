package com.mdb.util;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock {
    private String name;
    private Object key;

    private long timeStamp;

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    public Lock(String name, Object key) {
        this.name = name;
        this.key = key;
        this.timeStamp = TimeUtils.getTime();
    }

    public String getName() {
        return name;
    }

    public Object getKey() {
        return key;
    }

    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    public ReentrantReadWriteLock.ReadLock getReadLock() {
        return readLock;
    }

    public ReentrantReadWriteLock.WriteLock getWriteLock() {
        return writeLock;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
