package com.mdb;


public interface MdbFlushCallback {
    public void onFlushOver(Object key, boolean allSuccess, Exception exception);
}
