package com.mdb.stoarge;

import java.util.List;
import java.util.Map;

/**
 * insert
 * replace
 * replaceBatch
 * remove
 * removeBatch
 * 的时候如果报错了 就将数据记录下来,放到一个线程安全的列表里面,定时检测数据库是否连接正常, 长度大于n以后淘汰;并且输出日志
 */
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
