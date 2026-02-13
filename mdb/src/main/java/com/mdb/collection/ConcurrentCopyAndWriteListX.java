package com.mdb.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCopyAndWriteListX<E> extends TrackedList<E>{

    public ConcurrentCopyAndWriteListX() {
        super(new CopyOnWriteArrayList<>(), null);
    }

    public ConcurrentCopyAndWriteListX(com.mdb.XBean _xp_) {
        super(new CopyOnWriteArrayList<>(), _xp_);
    }
}
