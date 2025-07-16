package com.mdb.collection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapX<K,V> extends TrackedMap<K,V> {
    public ConcurrentHashMapX() {
        super(new ConcurrentHashMap<>());
    }
}
