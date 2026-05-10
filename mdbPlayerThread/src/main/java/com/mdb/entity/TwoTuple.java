package com.mdb.entity;

/**
 * @author zhuxiaoqing
 * @Description: TwoTuple
 * @Date 2026/5/8 19:14
 **/
public class TwoTuple<K,V> {
    K key;
    V value;

    public TwoTuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
