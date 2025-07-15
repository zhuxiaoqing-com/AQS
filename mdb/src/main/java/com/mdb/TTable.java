package com.mdb;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mdb.utils.Lock;
import com.mdb.utils.Lockeys;
import com.mdb.utils.Lockeys11;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class TTable<K, V> {
    public static final Object defaultValue = new Object();


    private boolean supportFlush;



    //private TTableCache<K, V> cache;
    private final Map<K, TRecord<K, V>> cache = new ConcurrentHashMap<>();
    Cache<Object, Object> findFailCache = Caffeine.newBuilder().maximumSize(200).softValues().build();

    private final AtomicLong countGetMiss = new AtomicLong();
    private long allCountGetMiss = 0;

    public V get(K key) {
        Lock lockey = Lockeys11.getLockey(getName(), key);
        lockey.getReadLock().lock();
        try {
            TRecord<K, V> tRecord = cache.get(key);
            if (tRecord != null) {
                if (tRecord.getState() == TRecord.REMOVE) {
                    return null;
                }
                return tRecord.get();
            }
        } finally {
            lockey.getReadLock().unlock();
        }

        // 不存在在数据库找个新的 然后返回
        lockey.getWriteLock().lock();
        try {
            // 找到了 返回
            TRecord<K, V> tRecord = cache.get(key);
            if (tRecord != null) {
                if (tRecord.getState() == TRecord.REMOVE) {
                    return null;
                }
                return tRecord.get();
            }

            // 没找到查数据库
            V value = _find(key);
            if (value != null) {
                cache.put(key, new TRecord<>(this, key, value, TRecord.GET));
            } else {
                findFailCache.put(key, defaultValue);
                // getMiss
                countGetMiss.incrementAndGet();
            }

            return value;
        } finally {
            lockey.getWriteLock().unlock();
        }
    }


    public void add(K key, V value) {
        Lock lockey = Lockeys11.getLockey(getName(), key);
        lockey.getWriteLock().lock();
        try {
            TRecord<K, V> tRecord = cache.get(key);
            if (tRecord != null) {
                tRecord.add(value);
            } else {
                tRecord = new TRecord<>(this, key, value, TRecord.ADD);
                cache.put(key, tRecord);
                findFailCache.invalidate(key);
            }

        } finally {
            lockey.getWriteLock().unlock();
        }
    }

    public void delete(K key) {
        Lock lockey = Lockeys11.getLockey(getName(), key);
        lockey.getWriteLock().lock();
        try {
            TRecord<K, V> tRecord = cache.get(key);
            if(tRecord == null) {
                return;
            }
            tRecord.remove();
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

    /**
     * 保存到数据库
     */
    private void store(TRecord<K, V> tRecord) {

    }

    /**
     * 从数据库删除
     */
    private void remove(TRecord<K, V> tRecord) {

    }

    public String getName() {
        return null;
    }

    public boolean supportFlush() {
        return supportFlush;
    }

    public void setSupportFlush(boolean supportFlush) {
        this.supportFlush = supportFlush;
    }

    public void tick(long time) {
        long count = countGetMiss.getAndSet(0);
        if (count > 0) {
            allCountGetMiss += count;
            //Set<Object> failKeySet = findFailCache.asMap().keySet();
            Mdb.logger.info(" tableName {}  allCountGetMiss {} countGetMiss {} ",
                    getName(), allCountGetMiss, count);
        }
    }


    protected abstract V newValue();

    protected abstract String marshalKey(K key);

    protected abstract String marshalValue(V value);

    protected abstract V deepCopy(V value);

    protected abstract K unmarshalKey(String os) throws Exception;

    protected abstract V unmarshalValue(String os) throws Exception;


    /**
     * 将单个key保存到数据库  玩家离线时调用;
     * 应该还有个判断 该表是否属于玩家 属于玩家才在玩家离线时调用;
     */
    public void flush(K key) {
        TRecord<K, V> kvtRecord = _flush(key);
        if(kvtRecord != null && kvtRecord.isModified()) {

        }
    }

    public TRecord<K, V>  _flush(K key) {
        Lock lockey = Lockeys.getLockey(this.getName(), key);
        lockey.getWriteLock().lock();
        try {
            return cache.remove(key);
        }finally {
            lockey.getWriteLock().unlock();
        }
    }

}
