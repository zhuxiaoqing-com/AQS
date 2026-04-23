package com.mdb.collection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapX<K,V> extends TrackedMap<K,V> {
    public ConcurrentHashMapX() {
        super(new ConcurrentHashMap<>(), null);
    }

    public ConcurrentHashMapX(com.mdb.XBean _xp_) {
        super(new ConcurrentHashMap<>(), _xp_);
    }
}
