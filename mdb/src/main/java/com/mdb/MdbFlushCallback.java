package com.mdb;

import com.mdb.entity.MdbPlayerInfo;

@FunctionalInterface
public interface MdbFlushCallback {


    public void onFlushOver(MdbPlayerInfo mdbPlayerInfo, boolean allSuccess, Exception exception);
}
