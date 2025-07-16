package com.mdb;

@FunctionalInterface
public interface MdbFlushCallback {
    public void onFlushOver(Object key);
}
