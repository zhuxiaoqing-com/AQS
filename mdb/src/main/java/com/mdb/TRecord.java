package com.mdb;

import net.jpountz.xxhash.XXHash64;
import net.jpountz.xxhash.XXHashFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TRecord<K, V> {
    public static final int ADD = 1;
    public static final int REMOVE = 2;
    public static final int GET = 3;
    private static final Logger log = LoggerFactory.getLogger(TRecord.class);

    private AtomicInteger state = new AtomicInteger(0);
    private TTable<K, V> table;
    private K key;
    private V value;

    private long[] hashArray = new long[]{0L,0L};

    /**
     * 上一次访问时间
     */
    private volatile long lastAccessTime;


    public TRecord(TTable<K, V> table, K key, V value, int state) {
        this.table = table;
        this.key = key;
        this.value = value;
        this.state.set(state);
        access();
    }


    /**
     * 这里肯定是线性的;因为只有add的地方调用，而且加锁了;
     */
    public void add(V newValue) {
        access();
        state.set(ADD);

        ((XBean) newValue).inheritVersion(getValueVersion());
        value = newValue;
        getValueVersion().incrementAndGet();
    }

    /**
     * 这里肯定是线性的;因为只有remove的地方调用，而且加锁了
     */
    public void remove() {
        access();
        state.set(REMOVE);

        V newValue = table.newValue();

        ((XBean) newValue).inheritVersion(getValueVersion());
        value = newValue;
        getValueVersion().incrementAndGet();
    }

    public V get() {
        access();
        return value;
    }



    public void access() {
        lastAccessTime = System.nanoTime();
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public int getState() {
        return state.get();
    }
    public AtomicInteger getStateAtomic() {
        return state;
    }

    public boolean isRemoveState(){
        return state.get() == REMOVE;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public AtomicLong getValueVersion() {
        return ((XBean) value).findVersionAtomic();
    }

    public String marshalKey() {
        return table.marshalKey(key);
    }
    public String marshalValue() {
        return table.marshalValue(value);
    }

    public String marshalValueOnSave() {
        String s = marshalValue();
        hashArray = hash(s);
        return s;
    }


    public boolean onSelect(String valueStr) {
        try {
            value = table.unmarshalValue(valueStr);
            if (value == null) {
                Mdb.logger.error("find error tableName {} key {} unmarshal value is null", table.getName(), key);
                return false;
            }
            ((XBean)value).findVersionAtomic().set(0);
            hashArray = hash(valueStr);
            return true;
        } catch (Exception e) {
            Mdb.logger.error("find error tableName {} key {}", table.getName(), key, e);
            return false;
        }
    }


    public String isModified(boolean strictCheck) {
        long version = Mdb.getInstance().getCheckPointCount();
        return isModified(version, strictCheck);
    }
    /**
     * todo 可能对比json  可能直接在有修改的时候弄个标记传播上来 然后直接判断那个标记
     * 什么时候重新计算hash;
     *      select成功的时候;
     *      保存到db的时候;保存到db分为
     *          checkPoint; 再
     *          flush;
     *          clearCache;
     */
    public String isModified(long version, boolean strictCheck) {
        // 这里不用单独在判断 add remove; 因为在new对象的时候已经将version自增了
      /*  switch (getState()) {
            case ADD:
                return true;
            case REMOVE:
                return true;
        }*/
        /*if (value instanceof XBean) {
            return ((XBean) value).isModify(version);
        }
        return false;
        */



        XBean bean = (XBean) value;

        // ① 版本脏检查（快速路径）
        if (bean.checkModify(version)) {
            String json = marshalValue();
            hashArray = hash(json);
            return json;
        }

        // ② 非严格模式：到这里说明没变
        if (!strictCheck) {
            return null;
        }

        // ③ 严格模式：做 hash 检查
        String json = marshalValue();
        long[] newHash = hash(json);

        if (!checkHashEquals(hashArray, newHash)) {
            hashArray = newHash;
            log.warn("strictCheck not equals tableName {} key {}", table.getName(), key);
            return json;
        }

        return null;
    }


    public void checkpointRefreshState(int _state) {
        switch (_state) {
            case ADD:
                state.compareAndSet(ADD, GET);
                break;
        }
    }

    public void checkPointFail() {
        getValueVersion().set(1);
        hashArray = new long[]{0L,0L};
    }


    boolean flush(String reason, String valueJson) {
        try {
            switch (getState()) {
                case ADD:
                    table.getTables().getStorage().insert(table.getName(), marshalKey(), valueJson);
                    return true;
                case REMOVE:
                    table.getTables().getStorage().remove(table.getName(), marshalKey());
                    return true;
                case GET:
                    table.getTables().getStorage().replace(table.getName(), marshalKey(), valueJson);
                    return true;
            }

        } catch (Exception e) {
            // 如果flush时候报错，则清除标识，下次会继续同步
            Mdb.logger.error("TRecord flush error reason {} ", reason, e);
            // 也设置一个同步失败;
            checkPointFail();
            return false;
        } finally {

        }
        return true;
    }

    private static final XXHash64 HASH64 =
            XXHashFactory.fastestInstance().hash64();

    public long[] hash(String json) {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        long h1 = HASH64.hash(bytes, 0, bytes.length, 0);
        long h2 = HASH64.hash(bytes, 0, bytes.length, 20240213);
        return new long[]{h1, h2};
    }

    public boolean checkHashEquals(long[] hashArray1, long[] hashArray2) {
        if(hashArray1 == null || hashArray2 == null) {
            return false;
        }
        if(hashArray1.length != hashArray2.length) {
            return false;
        }
        for(int i = 0; i < hashArray1.length; i++) {
            if(hashArray1[i] != hashArray2[i]) {
                return false;
            }
        }
        return true;
    }
}
