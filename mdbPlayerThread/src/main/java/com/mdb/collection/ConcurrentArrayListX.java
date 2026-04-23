package com.mdb.collection;

import java.util.ArrayList;

public class ConcurrentArrayListX<E> extends TrackedLockList<E> {

    public ConcurrentArrayListX() {
        super(new ArrayList<>(), null);
    }

    public ConcurrentArrayListX(com.mdb.XBean _xp_) {
        super(new ArrayList<>(), _xp_);
    }
}
