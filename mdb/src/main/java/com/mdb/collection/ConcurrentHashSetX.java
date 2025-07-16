package com.mdb.collection;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashSetX<E> extends TrackedSet<E> {
    public ConcurrentHashSetX() {
        super(Collections.newSetFromMap(new ConcurrentHashMap<>()));
    }
}
