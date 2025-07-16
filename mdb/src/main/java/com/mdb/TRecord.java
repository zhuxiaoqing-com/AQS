package com.mdb;

import com.mdb.utils.TimeUtils;

public class TRecord<K, V> {
    public static final int ADD = 1;
    public static final int REMOVE = 2;
    public static final int GET = 3;

    private int state;
    private TTable<K, V> table;
    private K key;
    private V value;

    /**
     * 上一次访问时间
     */
    private volatile long lastAccessTime;

    private boolean modified;

    public TRecord(TTable<K, V> table, K key, V value, int state) {
        this.table = table;
        this.key = key;
        this.value = value;
        this.state = state;
        access();
    }



    public void add(V newValue) {
        access();
        value = newValue;
        state = ADD;
        /*switch (state) {
            case ADD:
                value = newValue;
                break;
            case GET:
                value = newValue;
                state = ADD;
        }*/
    }

    public void remove() {
        access();
        value = null;
        state = REMOVE;
    }

    public V get() {
        access();
        return value;
    }


    public void access() {
        lastAccessTime = TimeUtils.getTime();
    }

    public int getState() {
        return state;
    }


    public boolean isModified() {
        return isModified(Mdb.getInstance().getCheckPointCount());
    }
    /**
     * todo 可能对比json  可能直接在有修改的时候弄个标记传播上来 然后直接判断那个标记
     */
    public boolean isModified(long version) {
        switch (state) {
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
            switch (state) {
                case ADD:
                    table.getMdb().getStorage().insert(table.getName(), marshalKey(), marshalValue());
                    return true;
                case REMOVE:
                    table.getMdb().getStorage().remove(table.getName(), marshalKey());
                    return true;
                case GET:
                    if (isModified()) {
                        table.getMdb().getStorage().replace(table.getName(), marshalKey(), marshalValue());
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
