package com.mdb.collection;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashSetX<E> extends TrackedSet<E> {
    public ConcurrentHashSetX(com.mdb.XBean _xp_) {
        super(Collections.newSetFromMap(new ConcurrentHashMap<>()), _xp_);
    }
}
