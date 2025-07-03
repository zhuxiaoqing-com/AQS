package com.mdb;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mdb.utils.Lock;
import com.mdb.utils.LockKey;
import com.mdb.utils.Lockeys11;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TTable<K, V> {
    //private TTableCache<K, V> cache;
    private Map<K, V> cache = new ConcurrentHashMap<K, V>();

    public static final Object defaultValue = new Object();

    Cache<Object, Object> findFailCache = Caffeine.newBuilder().maximumSize(200).softValues().build();


    public void add(K key, V value) {
        Lock lockey = Lockeys11.getLockey(getName(), key);
        lockey.getWriteLock().lock();
        try {
            cache.put(key, value);
            findFailCache.invalidate(key);
        } finally {
            lockey.getWriteLock().unlock();
        }
    }

    public V get(K key) {
        Lock lockey = Lockeys11.getLockey(getName(), key);
        lockey.getReadLock().lock();
        try {
            V value = cache.get(key);
            if (value != null) {
                return value;
            }
        } finally {
            lockey.getReadLock().unlock();
        }

        // 不存在在数据库找个新的 然后返回
        lockey.getWriteLock().lock();
        try {
            // 找到了 返回
            V value = cache.get(key);
            if (value != null) {
                return value;
            }

            // 没找到查数据库
            value = _find(key);
            if (value != null) {
                cache.put(key, value);
            }else {
                findFailCache.put(key,defaultValue);
            }

            return value;
        }finally {
            lockey.getWriteLock().unlock();
        }
    }

    public void delete(K key) {
        Lock lockey = Lockeys11.getLockey(getName(), key);
        lockey.getWriteLock().lock();
        try {
            cache.remove(key);
            findFailCache.invalidate(key);
        } finally {
            lockey.getWriteLock().unlock();
        }
    }

    /**
     * 查找数据库 等地方
     */
    private V _find(K key) {
        return null;
    }

    public String getName() {
        return null;
    }

}
