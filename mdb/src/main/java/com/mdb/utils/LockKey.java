package com.mdb.utils;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LockKey lockKey = (LockKey) o;
        return Objects.equals(tableName, lockKey.tableName) && Objects.equals(key, lockKey.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, key);
    }
}
