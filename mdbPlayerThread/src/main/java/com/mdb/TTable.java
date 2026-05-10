package com.mdb;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mdb.entity.ThreeTuple;
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
import java.util.stream.Stream;

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



    public void checkpoint(boolean sync) {
        logger.info("checkpoint table Start table {} ", getName());
        MdbInterface mdbInterface = getMdb().getMdbInterface();
        if(sync) {
            checkpointLogicThread(sync);
        } else {
            mdbInterface.allExecute(() -> checkpointLogicThread(sync));
        }
    }

    public void checkpointLogicThread(boolean sync){
        MdbInterface mdbInterface = getMdb().getMdbInterface();
        // 线程不符
        if (!sync) {
            if (!mdbInterface.checkThreadName(getSerializeLogicThreadName())) {
                return;
            }
        }
        logger.info("checkpoint checkpointLogicThread table Start table {} ", getName());

        //检测那些key可以在该线程
        HashMap<String, String> addCache = new HashMap<>();
        HashMap<String, String> modifyCache = new HashMap<>();
        HashMap<String, String> removeCache = new HashMap<>();

        List<TRecord> addTRecordCache = new ArrayList<>();
        List<TRecord> modifyTRecordCache = new ArrayList<>();
        List<TRecord> removeTRecordCache = new ArrayList<>();

        for (TRecord<K,V> kvtRecord : getCacheList()) {
            K key = kvtRecord.getKey();
            if (!sync) {
                if (!mdbInterface.checkKeyBelongCurrThread(key)) {
                    continue;
                }
            }

            String valueJson = kvtRecord.isModified(getMdb(), false);
            if (valueJson == null) {
                continue;
            }
            int state = kvtRecord.getState();
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

            // 将add状态刷新成get状态
            kvtRecord.checkpointRefreshState(state);
        }

        if (addCache.isEmpty() &&
                modifyCache.isEmpty() &&
                removeCache.isEmpty()) {
            return;
        }

        if(sync) {
            checkpointMdbSaveDB(addCache,
                    modifyCache,
                    removeCache,
                    addTRecordCache,
                    modifyTRecordCache,
                    removeTRecordCache,sync);
        } else {
            // 序列化好了 抛回去
            getMdb().putDBExecutor(a -> checkpointMdbSaveDB(addCache,
                    modifyCache,
                    removeCache,
                    addTRecordCache,
                    modifyTRecordCache,
                    removeTRecordCache, sync));
        }
    }

    public void checkpointMdbSaveDB(HashMap<String, String> addCache,
                                      HashMap<String, String> modifyCache,
                                      HashMap<String, String> removeCache,
                                      List<TRecord> addTRecordCache,
                                      List<TRecord> modifyTRecordCache,
                                      List<TRecord> removeTRecordCache,
                                    boolean sync
    ){
        logger.info("checkpoint checkpointMdbSaveDB table Start table {} addSize {} modifySize {} removeSize {}  ",
                getName(), addCache.size(), modifyCache.size(), removeCache.size());
        boolean addSuccess = true;
        boolean modifySuccess = true;
        boolean removeSuccess = true;

        try {
            getTables().getStorage().replaceBatch(getName(), addCache);
        } catch (Exception e) {
            logger.error("checkpoint addCache fail table {} ", this.getName(), e);
            addSuccess = false;
        }

        try {
            getTables().getStorage().replaceBatch(getName(), modifyCache);
        } catch (Exception e) {
            logger.error("checkpoint modifyCache fail table {} ", this.getName(), e);
            modifySuccess = false;
        }

        try {
            getTables().getStorage().removeBatch(getName(), removeCache.keySet());
        } catch (Exception e) {
            logger.error("checkpoint removeCache fail table {} ", this.getName(), e);
            removeSuccess = false;
        }

        // 全部成功了
        if (addSuccess && modifySuccess && removeSuccess) {
            return;
        }



        TRecord any = Stream.of(addTRecordCache, modifyTRecordCache, removeTRecordCache)
                .flatMap(List::stream)
                .findAny().orElse(null);

        // 抛会逻辑线程恢复数据
        boolean finalAddSuccess = addSuccess;
        boolean finalmodifySuccess = modifySuccess;
        boolean finalremoveSuccess = removeSuccess;
        Runnable runnable = () -> {
            // 这里就算logic线程的cache里已经没有数据了也没事,反正只是改了这个类,其他都没改
            if (!finalAddSuccess) {
                // 执行失败
                for (TRecord tRecord : addTRecordCache) {
                    // 将其重新设置为修改状态
                    tRecord.checkPointFail();
                }
            }
            if (!finalmodifySuccess) {
                // 执行失败
                for (TRecord tRecord : modifyTRecordCache) {
                    // 将其重新设置为修改状态
                    tRecord.checkPointFail();
                }
            }
            if (!finalremoveSuccess) {
                // 执行失败
                for (TRecord tRecord : removeTRecordCache) {
                    // 将其重新设置为修改状态
                    tRecord.checkPointFail();
                }
            }
        };

        if (sync) {
            logger.error("数据库数据同步失败！！！！具体日志 table {} ", this.getName());
            runnable.run();
        } else {
            getMdb().getMdbInterface().execute(getSerializeLogicThreadName(), any.getKey(), runnable);
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
        getMdb().getMdbInterface().allExecute(()->tickClearCacheLogicThread());
    }

    public void tickClearCacheLogicThread() {
        long curNano = System.nanoTime();
        long expairNano = TimeCostPrint.millsNano * TimeCostPrint.minuteMills * 30;

        MdbInterface mdbInterface = getMdb().getMdbInterface();
        // 线程不符
        if (!mdbInterface.checkThreadName(getSerializeLogicThreadName())) {
            return;
        }

        logger.info("tickClearCache tickClearCacheLogicThread start tableName {}  ", this.getName());

        List<ThreeTuple<TRecord<K, V>, String, String>> expireList = new ArrayList<>();
        for (TRecord<K, V> kvtRecord : cache.values()) {
            // 30分钟没访问删除
            if (curNano - kvtRecord.getLastAccessTime() < expairNano) {
                continue;
            }
            if (!mdbInterface.checkKeyBelongCurrThread(kvtRecord.getKey())) {
                continue;
            }
            String valueJson = kvtRecord.isModified(getMdb(), true);
            if (valueJson == null) {
                // 没有变化过 直接删除;
                cache.remove(kvtRecord.getKey());
                continue;
            }
            // 这里先将其设置为没修改过，然后等存好数据库数据以后,再返回，如果还是没修改过，那就进行删除cache，如果修改过了，就不动;
            kvtRecord.checkpointRefreshState(kvtRecord.getState());
            expireList.add(new ThreeTuple<>(kvtRecord, kvtRecord.marshalKey(), kvtRecord.marshalValue()));
        }

        if(expireList.isEmpty()) {
            return;
        }

        // 抛到DB线程进行存储
        getMdb().putDBExecutor(a -> tickClearCacheMdbSaveDB(expireList));
    }




    public void tickClearCacheMdbSaveDB(List<ThreeTuple<TRecord<K, V>, String, String>> expireList){
        logger.info("tickClearCache tickClearCacheMdbSaveDB start tableName {} expireListSize {} ", this.getName(), expireList.size());

        List<TRecord<K, V>> successRecord = new ArrayList<>();
        for (ThreeTuple<TRecord<K, V>, String, String> tuple : expireList) {
            TRecord<K, V> one = tuple.getOne();
            boolean flush = one.flush("tickClearCache", tuple.getTwo(), tuple.getThree());
            if(flush){
                successRecord.add(one);
            }
        }

        logger.info("tickClearCache tickClearCacheMdbSaveDB summary tableName {}   expireListSize {}  successRecordSize {}",
                this.getName(), expireList.size(), successRecord.size());

        if(successRecord.isEmpty()) {
            return;
        }

        // 抛会玩家线程进行处理
        TRecord<K, V> any = expireList.get(0).getOne();

        // 抛会逻辑线程删除cache数据
        getMdb().getMdbInterface().execute(getSerializeLogicThreadName(), any.getKey(), ()->{
            // 这里先将其设置为没修改过，然后等存好数据库数据以后,再返回，如果还是没修改过，那就进行删除cache，如果修改过了，就不动;
            for (TRecord<K, V> savedRecord : successRecord) {
                TRecord<K, V> currRecord = cache.get(savedRecord.getKey());
                if(currRecord != savedRecord) {
                    //logger.error("tickClearCacheMdbSaveDB currRecord!= savedRecord 请查找问题！！！ table {} currRecord {} savedRecord {} ", getName(), currRecord, savedRecord);
                    continue;
                }

                // 修改过
                if(savedRecord.isModified(getMdb(), false) != null){
                    continue;
                }
                // 删除数据
                cache.remove(savedRecord.getKey());
            }
        });
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

    public String getSerializeLogicThreadName() {
        if(meta == null) {
            return "";
        }
        return meta.getSerializeLogicThreadName();
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

    public void deleteCache(K key) {
        findFailCache.invalidate(key);
        cache.remove(key);
    }


    /**
     * 仅限于内部使用
     */
    public TRecord<K, V> getTRecordByCache(K key) {
        return cache.get(key);
    }

    protected abstract V newValue();

    protected abstract String marshalKey(K key);

    protected abstract String marshalValue(V value);

    protected abstract V deepCopy(V value);

    protected abstract K unmarshalKey(String os) throws Exception;

    protected abstract V unmarshalValue(String os) throws Exception;

}
