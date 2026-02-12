package com.mdb;

import com.mdb.utils.TimeUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TRecord<K, V> {
    public static final int ADD = 1;
    public static final int REMOVE = 2;
    public static final int GET = 3;

    private AtomicInteger state = new AtomicInteger(0);
    private TTable<K, V> table;
    private K key;
    private V value;

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



    public void add(V newValue) {
        access();
        state.set(ADD);
        value = newValue;
    }

    public void remove() {
        access();
        state.set(REMOVE);
        // 将旧的释放掉;但是由于需要version 所以又new一个新的来保存version
        value = table.newValue();
    }

    public V get() {
        access();
        return value;
    }


    public void checkpointRefreshState(int _state) {
        switch (_state) {
            case ADD:
                state.compareAndSet(ADD, GET);
                break;
        }
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
        return ((XBean) value).getVersion();
    }


    public boolean isModified() {
        return isModified(Mdb.getInstance().getCheckPointCount());
    }
    /**
     * todo 可能对比json  可能直接在有修改的时候弄个标记传播上来 然后直接判断那个标记
     */
    public boolean isModified(long version) {
        // 这里不用单独在判断 add remove; 因为在new对象的时候已经将version自增了
      /*  switch (getState()) {
            case ADD:
                return true;
            case REMOVE:
                return true;
        }*/
        // 在解析xml的时候已经规定了 table vaule必须是xbean 所以这里一定成立
        if (value instanceof XBean) {
            return ((XBean) value).isModify(version);
        }
        return false;
    }
    public String marshalKey() {
        return table.marshalKey(key);
    }

    public String marshalValue() {
        return table.marshalValue(value);
    }

    boolean flush(String reason) {
        try {
            switch (getState()) {
                case ADD:
                    table.getTables().getStorage().insert(table.getName(), marshalKey(), marshalValue());
                    return true;
                case REMOVE:
                    table.getTables().getStorage().remove(table.getName(), marshalKey());
                    return true;
                case GET:
                    table.getTables().getStorage().replace(table.getName(), marshalKey(), marshalValue());
                    return true;
            }

        } catch (Exception e) {
            // 如果flush时候报错，则清除标识，下次会继续同步
            Mdb.logger.error("TRecord flush error reason： {} ", reason, e);
            return false;
        } finally {

        }
        return true;
    }
}
