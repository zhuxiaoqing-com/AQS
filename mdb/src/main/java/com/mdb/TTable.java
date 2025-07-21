package com.mdb;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mdb.utils.Lock;
import com.mdb.utils.Lockkeys;
import com.mdb.utils.Lockeys11;
import com.mdb.utils.TimeCostPrint;
import com.mdb.xmlgen.xmlparse.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class TTable<K, V> {
    public static final Object defaultValue = new Object();
    private static final Logger logger = LoggerFactory.getLogger(TTable.class);


    private Table meta;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    protected ReentrantReadWriteLock.WriteLock writelock = lock.writeLock();
    protected ReentrantReadWriteLock.ReadLock readlock = lock.readLock();

    private Tables tables;


    public TTable(Tables tables) {
        this.tables = tables;
    }

    //private TTableCache<K, V> cache;
    private final Map<K, TRecord<K, V>> cache = new ConcurrentHashMap<>(10000);
    Cache<Object, Object> findFailCache = Caffeine.newBuilder().maximumSize(200).softValues().build();

    private final AtomicLong countGetMiss = new AtomicLong();
    private long allCountGetMiss = 0;

    public V get(K key) {
        Lock lockey = Lockeys11.getLockey(getName(), key);
        lockey.getReadLock().lock();
        try {
            TRecord<K, V> tRecord = cache.get(key);
            if (tRecord != null) {
                if (tRecord.isRemoveState()) {
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
                if (tRecord.isRemoveState()) {
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
        if (value == null) {
            throw new NullPointerException("value is null");
        }
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
            if (tRecord == null) {
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
        V value = null;
        try {
            String s = tables.getStorage().find(getName(), marshalKey(key));
            if (s != null && !s.isEmpty()) {
                value = unmarshalValue(s);
            }
        } catch (Exception e) {
            logger.error("find error tableName {} key {}", getName(), key, e);
            throw new RuntimeException(e);
        }

        return value;
    }


    /**
     * 将单个key保存到数据库  玩家离线时调用;
     * 应该还有个判断 该表是否属于玩家 属于玩家才在玩家离线时调用;
     */
    /*public void flush(K key) {
        TRecord<K, V> kvtRecord = _flush(key);
        if (kvtRecord != null) {
            kvtRecord.flush();
        }
    }*/
    public boolean flush(K key) {
        // 这里还是把锁加上吧 毕竟从cache里删除;保证顺序
        Lock lockey = Lockkeys.getLockey(this.getName(), key);
        lockey.getWriteLock().lock();
        try {
            TRecord<K, V> kvtRecord = cache.remove(key);
            // !=null 并且修改过
            if (kvtRecord != null && kvtRecord.isModified()) {
                boolean flush = kvtRecord.flush("flush");
                if (!flush) {
                    logger.error("flush fail table {} key {} ", this.getName(), key);
                    // flush失败重新加入cache等待下一次同步flush
                    cache.put(key, kvtRecord);
                    return false;
                }
            }
        } finally {
            lockey.getWriteLock().unlock();
        }
        return true;
    }


    public void checkpoint(long checkPointCount,
                           HashMap<String, String> addCache, HashMap<String, String> modifyCache, HashMap<String, String> removeCache,
                           List<TRecord> addTRecordCache, List<TRecord> modifyTRecordCache, List<TRecord> removeTRecordCache
    ) {
        TimeCostPrint timeCostPrint = new TimeCostPrint(logger, 60, "checkpoint_table" + getName());
        checkpointMarshal(checkPointCount, addCache, modifyCache, removeCache, addTRecordCache, modifyTRecordCache, removeTRecordCache);

        TimeCostPrint saveDBTime = new TimeCostPrint(logger, 30, "checkpoint_table_saveDBTime" + getName());
        checkpointSaveDB(addCache, modifyCache, removeCache, addTRecordCache, modifyTRecordCache, removeTRecordCache);
        saveDBTime.print();

        timeCostPrint.print();
        logger.info("checkpoint_table addCount {} modifyCount {} removeCount {} ", addCache.size(), modifyCache.size(), removeCache.size());
    }


    private void checkpointMarshal(long checkPointCount,
                                   HashMap<String, String> addCache,
                                   HashMap<String, String> modifyCache,
                                   HashMap<String, String> removeCache,
                                   List<TRecord> addTRecordCache,
                                   List<TRecord> modifyTRecordCache,
                                   List<TRecord> removeTRecordCache) {
        try {
            for (TRecord<K, V> kvtRecord : getCacheList()) {
                if (!kvtRecord.isModified(checkPointCount)) {
                    continue;
                }
                AtomicLong valueVersion = kvtRecord.getValueVersion();
                long old = valueVersion.get();
                switch (kvtRecord.getState()) {
                    case TRecord.ADD:
                        addCache.put(kvtRecord.marshalKey(), kvtRecord.marshalValue());
                        addTRecordCache.add(kvtRecord);
                        break;
                    case TRecord.GET:
                        modifyCache.put(kvtRecord.marshalKey(), kvtRecord.marshalValue());
                        modifyTRecordCache.add(kvtRecord);
                        break;
                    case TRecord.REMOVE:
                        removeCache.put(kvtRecord.marshalKey(), "");
                        removeTRecordCache.add(kvtRecord);
                        break;
                }
                valueVersion.compareAndSet(old, 0);// 修改失败就不管了;说明又有修改了
                // 将add状态刷新成get状态
                kvtRecord.checkpointRefreshState();
            }
        } catch (Exception e) {
            logger.error("checkpoint marshal fail table {} ", this.getName(), e);
        }
    }

    private void checkpointSaveDB(HashMap<String, String> addCache,
                                  HashMap<String, String> modifyCache,
                                  HashMap<String, String> removeCache,
                                  List<TRecord> addTRecordCache,
                                  List<TRecord> modifyTRecordCache,
                                  List<TRecord> removeTRecordCache) {
        try {
            getTables().getStorage().replaceBatch(getName(), addCache);
        } catch (Exception e) {
            logger.error("checkpoint addCache fail table {} ", this.getName(), e);
            // 执行失败
            for (TRecord tRecord : addTRecordCache) {
                // 将其重新设置为修改状态
                tRecord.getValueVersion().set(1);
            }
        }

        try {
            getTables().getStorage().replaceBatch(getName(), modifyCache);
        } catch (Exception e) {
            logger.error("checkpoint modifyCache fail table {} ", this.getName(), e);
            // 执行失败
            for (TRecord tRecord : modifyTRecordCache) {
                // 将其重新设置为修改状态
                tRecord.getValueVersion().set(1);
            }
        }
        try {
            getTables().getStorage().removeBatch(getName(), removeCache.keySet());
        } catch (Exception e) {
            logger.error("checkpoint removeCache fail table {} ", this.getName(), e);
            // 执行失败
            for (TRecord tRecord : removeTRecordCache) {
                // 将其重新设置为修改状态
                tRecord.getValueVersion().set(1);
            }
        }
    }


    public static final int TICK_INTERVAL = 1000 * 60;
    public int nextTickTime;

    public void tick(long currTime) {
        if (currTime < nextTickTime) {
            return;
        }
        nextTickTime += TICK_INTERVAL;
        long count = countGetMiss.getAndSet(0);
        if (count > 0) {
            allCountGetMiss += count;
            //Set<Object> failKeySet = findFailCache.asMap().keySet();
            Mdb.logger.info(" tableName {}  allCountGetMiss {} countGetMiss {} TICK_INTERVAL {}",
                    getName(), allCountGetMiss, count, TICK_INTERVAL);
        }
    }

    /**
     * 定时把30分钟还没有访问的数据清理
     */
    public void tickClearCache() {
        long curNano = System.nanoTime();
        long expairNano = TimeCostPrint.millsNano * TimeCostPrint.minuteMills * 30;
        // 支持flush 说明是玩家个人数据; 不清理让玩家手动调用flush清理
     /*   if (isSupportFlush()) {
            return;
        }*/

        logger.info("tickClearCache start tableName {}  ", this.getName());
        boolean detect = tables.getStorage().detect();
        if (!detect) {
            logger.error("tickClearCache 和数据库的连接已经的断开！！！ tablesName {} tableName {} ", tables.getName(), getName());
            return;
        }

        TimeCostPrint timeCostPrint = new TimeCostPrint(logger, 30, "tickClearCache");

        writelock.lock();// 和 checkpoint 互斥
        try {
            ArrayList<TRecord<K, V>> expireList = new ArrayList<>();
            for (TRecord<K, V> value : cache.values()) {
                // 30分钟没访问删除
                if (curNano - value.getLastAccessTime() > expairNano) {
                    expireList.add(value);
                }
            }

            //这里还没法批量执行;不然如果remove的时候我先remove批量执行,然后我先从cache里吧key删了;
            // 这个时候刚好这个Key又get了,那这个Key的数据就是有问题的了;
            new ArrayList<>(expireList);
            for (TRecord<K, V> kvtRecord : expireList) {
                Lock lockey = Lockkeys.getLockey(getName(), kvtRecord.getKey());
                // 需要排他 所以要写锁 和 get add remove 互斥;
                lockey.getWriteLock().lock();
                try {
                    TRecord<K, V> mapTRecord = cache.get(kvtRecord.getKey());
                    // 不是一个对象了
                    if (mapTRecord != kvtRecord) {
                        continue;
                    }
                    // 30分钟没访问删除
                    if (curNano - kvtRecord.getLastAccessTime() < expairNano) {
                        continue;
                    }
                    cache.remove(kvtRecord.getKey());
                    // 没有修改过就不用同步到数据库
                    if (!kvtRecord.isModified()) {
                        continue;
                    }
                    // 刷新失败了 基本是数据库出问题了
                    if (!kvtRecord.flush("tickClearCache")) {
                        //将对象加回去 等待下次同步
                        cache.put(kvtRecord.getKey(), kvtRecord);
                    }
                } finally {
                    lockey.getWriteLock().unlock();
                }
            }
        } finally {
            writelock.unlock();
            timeCostPrint.print();
        }
    }


    public Tables getTables() {
        return tables;
    }

    public void close() {

    }


    public abstract String getName();

    public boolean isSupportFlush() {
        return meta.isSupportFlush();
    }


    public Collection<TRecord<K, V>> getCacheList() {
        return cache.values();
    }

    protected abstract V newValue();

    protected abstract String marshalKey(K key);

    protected abstract String marshalValue(V value);

    protected abstract V deepCopy(V value);

    protected abstract K unmarshalKey(String os) throws Exception;

    protected abstract V unmarshalValue(String os) throws Exception;

}
