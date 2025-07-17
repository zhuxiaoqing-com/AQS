package com.mdb;

import com.mdb.utils.TimeUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class TRecord<K, V> {
    public static final int ADD = 1;
    public static final int REMOVE = 2;
    public static final int GET = 3;
    public static final int REMOVE_SYNC_DB = 4;// 删除已经存入数据库了

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
        /*switch (getState()) {
            case REMOVE:
                state.set(ADD);
            case ADD:
            case GET:// get为什么不变add; 因为不需要变,get已经有modify判断了;不需要在变成add了;
                break;
        }*/
        value = newValue;
    }

    public void remove() {
        access();
        value = null;
        state.getAndUpdate(a -> {
            // 如果已经是REMOVE_SYNC_DB 就不再变成remove状态了;
            if (a == REMOVE_SYNC_DB) {
                return a;
            } else {
                return REMOVE;
            }
        });
    }

    public V get() {
        access();
        return value;
    }


    public void access() {
        lastAccessTime = TimeUtils.getTime();
    }

    public int getState() {
        return state.get();
    }
    public AtomicInteger getStateAtomic() {
        return state;
    }

    public boolean isRemoveState(){
        return state.get() == REMOVE || state.get() == REMOVE_SYNC_DB;
    }


    public boolean isModified() {
        return isModified(Mdb.getInstance().getCheckPointCount());
    }
    /**
     * todo 可能对比json  可能直接在有修改的时候弄个标记传播上来 然后直接判断那个标记
     */
    public boolean isModified(long version) {
        switch (getState()) {
            case ADD:
                return true;
            case REMOVE:
                return true;
        }
        if (value == null) {
            return false;
        }
        if(value instanceof XBean) {
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

    boolean flush() {
        try {
            switch (getState()) {
                case ADD:
                    table.getTables().getStorage().insert(table.getName(), marshalKey(), marshalValue());
                    return true;
                case REMOVE:
                    table.getTables().getStorage().remove(table.getName(), marshalKey());
                    return true;
                case GET:
                    if (isModified()) {
                        table.getTables().getStorage().replace(table.getName(), marshalKey(), marshalValue());
                        return true;
                    }
            }

        } catch (Exception e) {
            // 如果flush时候报错，则清除标识，下次会继续同步
            Mdb.logger.error("TRecord flush error ", e);
            return false;
        } finally {

        }
        return true;
    }
}
