package com.mdb.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCopyAndWriteListX<E> extends TrackedList<E>{

    public ConcurrentCopyAndWriteListX() {
        super(new CopyOnWriteArrayList<>());
    }
}
