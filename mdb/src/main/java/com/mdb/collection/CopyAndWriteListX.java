package com.mdb.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyAndWriteListX<E> extends TrackedList<E>{

    public CopyAndWriteListX() {
        super(new CopyOnWriteArrayList<>());
    }
}
