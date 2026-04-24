package com.mdb;

import com.mdb.entity.MdbPlayerInfo;

@FunctionalInterface
public interface MdbLoadCallback {
    public void callback(MdbPlayerInfo mdbPlayerInfo, boolean allSuccess, Exception exception);
}
