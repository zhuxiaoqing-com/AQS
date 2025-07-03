package com.mdb.utils;

public class LockKey {
    private String tableName;
    private Object key;
    public LockKey(String tableName, Object key) {
        this.tableName = tableName;
        this.key = key;
    }

    public String getTableName() {
        return tableName;
    }

    public Object getKey() {
        return key;
    }
}
