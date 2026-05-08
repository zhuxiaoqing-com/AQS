package com.mdb;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mdb.util.Lock;
import com.mdb.util.Lockkeys;
import com.mdb.util.TimeCostPrint;
import com.mdb.xmlgen.xmlparse.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
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


    //private TTableCache<K, V> cache;
    private final Map<K, TRecord<K, V>> cache = new ConcurrentHashMap<>(10000);
    Cache<Object, Object> findFailCache = Caffeine.newBuilder().maximumSize(1000).expireAfterAccess(30,TimeUnit.MINUTES).softValues().build();

    private final AtomicLong countGetMiss = new AtomicLong();
    private long allCountGetMiss = 0;

    public V get(K key) {
        checkSupportFlushOperate(key, "get");
        return getOrLoadToCache(key);
    }

    public V loadToCache(K key) {
        return getOrLoadToCache(key);
    }

    private V getOrLoadToCache(K key) {
        Lock lockey = Lockkeys.getLockey(getName(), key);
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
            if (findFailCache.getIfPresent(key) != null) {
                return null;
            }
            String valueStr = _find(key);
            if (valueStr != null && !valueStr.isEmpty()) {
                tRecord = new TRecord<>(this, key, null, TRecord.GET);
                tRecord.onSelect(valueStr);
                cache.put(key, tRecord);

            } else {
                findFailCache.put(key, defaultValue);
                // getMiss
                countGetMiss.incrementAndGet();
            }

            return tRecord == null ? null : tRecord.getValue();
        } finally {
            lockey.getWriteLock().unlock();
        }
    }

    public boolean add(K key, V value) {
      return  add(key, value, false);
    }

    public boolean add(K key, V value, boolean immediately) {
        if (value == null) {
            throw new NullPointerException("value is null");
        }
        checkSupportFlushOperate(key, immediately ? "add_immediately" : "add");
        Lock lockey = Lockkeys.getLockey(getName(), key);
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
        return true;
    }

    public boolean remove(K key) {
        checkSupportFlushOperate(key, "remove");
        Lock lockey = Lockkeys.getLockey(getName(), key);
        lockey.getWriteLock().lock();
        try {
            TRecord<K, V> tRecord = cache.get(key);
            if (tRecord != null) {
                tRecord.remove();
                return true;
            }
            // 没有找到就直接新建一个
            tRecord = new TRecord<K, V>(this, key, newValue(), TRecord.REMOVE);
            cache.put(key, tRecord);
            /**
             * 未加载 remove 现在能正常留下删除标记了，但这里没有清理 findFailCache。
             * 如果这个 key 之前查库未命中过，之后又经历 remove -> add -> tickClearCache 写回 DB，旧的未命中缓存可能在 cache 被清掉后继续让 get 返回 null。
             * 这个窗口比较绕，但清掉 findFailCache 成本很低。
             */
            findFailCache.invalidate(key);
        } finally {
            lockey.getWriteLock().unlock();
        }
        return true;
    }

    private void checkSupportFlushOperate(K key, String operation) {
        if (!isSupportFlush()) {
            return;
        }
        if (getMdb().canSupportFlushOperate(key, getName(), operation)) {
            return;
        }
        throw new IllegalStateException(
                "player mdb data not loaded, table=" + getName() + ", operation=" + operation + ", key=" + key);
    }


    /**
     * 查找数据库 等地方
     */
    private V _findByObj(K key) {
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
     * 查找数据库 等地方
     */
    private String _find(K key) {
        return tables.getStorage().find(getName(), marshalKey(key));
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

    /**
     *  你把 writelock 去掉后，flush 和 checkpointSaveDB 会并发，典型问题是：
     *   2. flush 写入新值
     *   3. checkpoint 随后把旧值再写回库（覆盖新值）
     *   这里得注意这个问题,flush把checkPoint的覆盖掉了;
     */
    public boolean flush(K key) {
        // 这里防止close以后，线程还在运行，数据库脏数据
        if (getMdb().close) {
            logger.error("flush mdb is close!!! table {} key {}", getName(), key);
            return false;
        }
        // 这里还是把锁加上吧 毕竟从cache里删除;保证顺序
        Lock lockey = Lockkeys.getLockey(this.getName(), key);
        lockey.getWriteLock().lock();
        try {
            findFailCache.invalidate(key);
            TRecord<K, V> kvtRecord = cache.remove(key);
            // !=null 并且修改过
            if (kvtRecord != null) {
                String valueJson = kvtRecord.isModified(getMdb(), true);
                if (valueJson != null) {
                    boolean flush = kvtRecord.flush("flush", valueJson);
                    if (!flush) {
                        logger.error("flush fail table {} key {} ", this.getName(), key);
                        // flush失败重新加入cache等待下一次同步flush
                        cache.put(key, kvtRecord);
                        return false;
                    }
                }
            }
        } finally {
            lockey.getWriteLock().unlock();
        }
        return true;
    }


    public void deleteCache(K key) {
        Lock lockey = Lockkeys.getLockey(this.getName(), key);
        lockey.getWriteLock().lock();
        try {
            findFailCache.invalidate(key);
            cache.remove(key);
        } finally {
            lockey.getWriteLock().unlock();
        }
    }

    public void checkpoint(long checkPointCount,
                           HashMap<String, String> addCache, HashMap<String, String> modifyCache, HashMap<String, String> removeCache,
                           List<TRecord> addTRecordCache, List<TRecord> modifyTRecordCache, List<TRecord> removeTRecordCache,boolean strictCheck
    ) {
        writelock.lock();
        try {
            TimeCostPrint timeCostPrint = new TimeCostPrint(logger, 60, "checkpoint_table" + getName());
            checkpointMarshal(checkPointCount, addCache, modifyCache, removeCache, addTRecordCache, modifyTRecordCache, removeTRecordCache, strictCheck);

            TimeCostPrint saveDBTime = new TimeCostPrint(logger, 30, "checkpoint_table_saveDBTime" + getName());
            checkpointSaveDB(addCache, modifyCache, removeCache, addTRecordCache, modifyTRecordCache, removeTRecordCache);
            saveDBTime.print();

            timeCostPrint.print();
            logger.info("checkpoint_table addCount {} modifyCount {} removeCount {} ", addCache.size(), modifyCache.size(), removeCache.size());
        } finally {
            writelock.unlock();
        }
    }


    private void checkpointMarshal(long checkPointCount,
                                   HashMap<String, String> addCache,
                                   HashMap<String, String> modifyCache,
                                   HashMap<String, String> removeCache,
                                   List<TRecord> addTRecordCache,
                                   List<TRecord> modifyTRecordCache,
                                   List<TRecord> removeTRecordCache
                                   ,boolean strictCheck
    ) {
        try {
            for (TRecord<K, V> kvtRecord : getCacheList()) {
                AtomicLong valueVersion = kvtRecord.getValueVersion();
                /**
                 * 这里的old比如是1; 然后state是REMOVE;
                 * 并发state变为ADD; version还是1;这个时候明明是添加，我却将其变成了remove存入了;
                 * 这里是因为我没有继承version;继承了以后version就会变成2;
                 * 然后我要把state变成GET;但是version被清空了;
                 *
                 * 如果我REMOVE和ADD的时候继承旧的类的version呢？;
                 * 虽然state错了,不过没事version没问题就行了;
                 * 我获取old;然后state变化了;这个时候因为state进行增加删除更新;
                 * 然后old自增了;因为version不一样，所以下一次还是可以继续存储;
                 *
                 * 我再addremove里先设置version, 再设置state;可能state变化了，但是old没变化导致state变化了，
                 * 比如我add设置version;然后checkpoint#getOld(),getState(remove); 然后add里设置state(add);
                 * 这个时候add就不会再更新到数据库了;
                 *
                 * 我再addremove里先设置state, 再设置version;的话就不会有这个问题了;因为version有compareAndSet保证;
                 *
                 *
                 * 下面这里必须先old再state;old保底，这样中途state和json如果变化了;都无所谓，下次再操作就好了,和锁的含义差不多
                 */
                long old = valueVersion.get();
                int state = kvtRecord.getState();
                String valueJson = kvtRecord.isModified(getMdb(), strictCheck);
                if (valueJson == null) {
                    continue;
                }
                switch (state) {
                    case TRecord.ADD:
                        addCache.put(kvtRecord.marshalKey(), valueJson);
                        addTRecordCache.add(kvtRecord);
                        break;
                    case TRecord.GET:
                        modifyCache.put(kvtRecord.marshalKey(), valueJson);
                        modifyTRecordCache.add(kvtRecord);
                        break;
                    case TRecord.REMOVE:
                        removeCache.put(kvtRecord.marshalKey(), "");
                        removeTRecordCache.add(kvtRecord);
                        break;
                }
                valueVersion.compareAndSet(old, 0);// 修改失败就不管了;说明又有修改了
                // 将add状态刷新成get状态
                kvtRecord.checkpointRefreshState(state);
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
                tRecord.checkPointFail();
            }
        }

        try {
            getTables().getStorage().replaceBatch(getName(), modifyCache);
        } catch (Exception e) {
            logger.error("checkpoint modifyCache fail table {} ", this.getName(), e);
            // 执行失败
            for (TRecord tRecord : modifyTRecordCache) {
                // 将其重新设置为修改状态
                tRecord.checkPointFail();

            }
        }
        try {
            getTables().getStorage().removeBatch(getName(), removeCache.keySet());
        } catch (Exception e) {
            logger.error("checkpoint removeCache fail table {} ", this.getName(), e);
            // 执行失败
            for (TRecord tRecord : removeTRecordCache) {
                // 将其重新设置为修改状态
                tRecord.checkPointFail();
            }
        }
    }


    public static final int TICK_INTERVAL = 1000 * 60;
    public long nextTickTime;

    public void tick(long currTime) {
        if (nextTickTime <= 0) {
            nextTickTime = currTime + TICK_INTERVAL;
            return;
        }
        if (currTime < nextTickTime) {
            return;
        }
        nextTickTime = currTime + TICK_INTERVAL;
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
        // 这里防止close以后，线程还在运行，数据库脏数据
        if (getMdb().close) {
            logger.error("tickClearCache mdb is close!!! table {}", getName());
            return;
        }

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
                    String valueJson = kvtRecord.isModified(getMdb(),true);
                    if (valueJson == null) {
                        continue;
                    }
                    // 刷新失败了 基本是数据库出问题了
                    if (!kvtRecord.flush("tickClearCache", valueJson)) {
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
        return meta != null && meta.isSupportFlush();
    }

    void setMeta(Table meta) {
        this.meta = meta;
    }

    Table getMeta() {
        return meta;
    }

    Mdb getMdb() {
        return tables.getMdb();
    }

    public void setTables(Tables tables) {
        this.tables = tables;
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
