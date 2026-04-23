package com.mdb.collection;

import java.util.ArrayDeque;

public class ConcurrentArrayDequeX<E> extends TrackedQueue<E> {

    public ConcurrentArrayDequeX() {
        super(new ArrayDeque<>(), null);
    }

    public ConcurrentArrayDequeX(com.mdb.XBean _xp_) {
        super(new ArrayDeque<>(), _xp_);
    }
}
