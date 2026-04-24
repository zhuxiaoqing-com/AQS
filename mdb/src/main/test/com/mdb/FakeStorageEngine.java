package com.mdb;

import com.mdb.stoarge.StorageEngine;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class FakeStorageEngine implements StorageEngine {
    private final Map<String, Map<String, String>> data = new ConcurrentHashMap<>();

    private final AtomicInteger insertFailures = new AtomicInteger();
    private final AtomicInteger replaceFailures = new AtomicInteger();
    private final AtomicInteger replaceBatchFailures = new AtomicInteger();
    private final AtomicInteger removeFailures = new AtomicInteger();
    private final AtomicInteger removeBatchFailures = new AtomicInteger();

    private volatile boolean detect = true;

    void failNextInsert() {
        insertFailures.incrementAndGet();
    }

    void failNextReplace() {
        replaceFailures.incrementAndGet();
    }

    void failNextReplaceBatch() {
        replaceBatchFailures.incrementAndGet();
    }

    void failNextRemove() {
        removeFailures.incrementAndGet();
    }

    void failNextRemoveBatch() {
        removeBatchFailures.incrementAndGet();
    }

    void setDetect(boolean detect) {
        this.detect = detect;
    }

    String getStored(String table, String key) {
        Map<String, String> tableData = data.get(table);
        return tableData == null ? null : tableData.get(key);
    }

    @Override
    public String find(String table, String key) {
        return getStored(table, key);
    }

    @Override
    public boolean exist(String table, String key) {
        return getStored(table, key) != null;
    }

    @Override
    public boolean insert(String table, String key, String value) {
        if (consume(insertFailures)) {
            return false;
        }
        Map<String, String> tableData = table(table);
        if (tableData.putIfAbsent(key, value) != null) {
            return false;
        }
        return true;
    }

    @Override
    public void replace(String table, String key, String value) {
        if (consume(replaceFailures)) {
            throw new RuntimeException("fake replace failure");
        }
        table(table).put(key, value);
    }

    @Override
    public void replaceBatch(String table, Map<String, String> kvs) {
        if (consume(replaceBatchFailures)) {
            throw new RuntimeException("fake replaceBatch failure");
        }
        StorageEngine.super.replaceBatch(table, kvs);
    }

    @Override
    public void remove(String table, String key) {
        if (consume(removeFailures)) {
            throw new RuntimeException("fake remove failure");
        }
        Map<String, String> tableData = data.get(table);
        if (tableData != null) {
            tableData.remove(key);
        }
    }

    @Override
    public void removeBatch(String table, Collection<String> keys) {
        if (consume(removeBatchFailures)) {
            throw new RuntimeException("fake removeBatch failure");
        }
        StorageEngine.super.removeBatch(table, keys);
    }

    @Override
    public boolean detect() {
        return detect;
    }

    @Override
    public void initTable(String tableName) {
        table(tableName);
    }

    @Override
    public void close() {
        data.clear();
    }

    private Map<String, String> table(String table) {
        return data.computeIfAbsent(table, ignored -> new ConcurrentHashMap<>());
    }

    private boolean consume(AtomicInteger counter) {
        while (true) {
            int current = counter.get();
            if (current <= 0) {
                return false;
            }
            if (counter.compareAndSet(current, current - 1)) {
                return true;
            }
        }
    }
}
