package com.mdb;

import com.mdb.utils.Lock;
import com.mdb.utils.Lockeys;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TTable<K, V> {
    //private TTableCache<K, V> cache;
    private Map<K, V> cache = new ConcurrentHashMap<K, V>();


    public void add(K key, V value) {
        Lock lockey = Lockeys.getLockey(getName(), key);
        lockey.getWriteLock().lock();
        try {
            cache.put(key, value);
        } finally {
            lockey.getWriteLock().unlock();
        }
    }

    public V get(K key) {
        Lock lockey = Lockeys.getLockey(getName(), key);
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

            // todo 这里应该还有一个查找失败map 如果存在就不查找了
            value = _find(key);
            if (value != null) {
                cache.put(key, value);
            }

        }finally {
            lockey.getWriteLock().unlock();
        }
    }

    /**
     * 查找数据库 等地方
     */
    private V _find(K key) {
        return null;
    }

    public void delete(K key) {

    }

    public String getName() {
        return null;
    }

}
