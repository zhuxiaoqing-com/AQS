package com.mdb;

@FunctionalInterface
public interface MdbPlayerFinishCallback {
    public void callback(Object key, boolean success, Exception exception);
}
