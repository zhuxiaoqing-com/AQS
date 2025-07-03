package com.mdb;

import java.util.List;
import java.util.Map;

public interface StorageEngine {

    String find(String key);

    boolean exist(String key);

    boolean insert(String key, String value);

    void replace(String key, String value);

    default void replaceBatch(Map<String, String> kvs) {
        for (Map.Entry<String, String> entry : kvs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            replace(key, value);
        }
    }

    void remove(String key);

    default void removeBatch(List<String> keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    void detect();
}
