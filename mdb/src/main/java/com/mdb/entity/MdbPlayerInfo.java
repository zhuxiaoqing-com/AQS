package com.mdb.entity;

import com.mdb.MdbFlushCallback;
import com.mdb.MdbLoadCallback;

/**
 * @author zhuxiaoqing
 * @Description: MdbPlayerInfo
 * @Date 2026/4/24 13:46
 **/
public class MdbPlayerInfo {
    public static final int load_wait = 1; // 等待load
    public static final int load_ing = 2; // load中
    public static final int load_finish = 3; // load完成
    public static final int flush_wait = 4; // 等待flush
    public static final int flush_ing = 5; // flush中
    public static final int flush_finish = 6; // flush完成

    public static String stateName(int state) {
        switch (state) {
            case load_wait:
                return "load_wait";
            case load_ing:
                return "load_ing";
            case load_finish:
                return "load_finish";
            case flush_wait:
                return "flush_wait";
            case flush_ing:
                return "flush_ing";
            case flush_finish:
                return "flush_finish";
            default:
                return "unknown(" + state + ")";
        }
    }

    private Long playerId;
    private long offlineTime; // 离线时间
    private long flushStartTime; // 开始时间
    private Object param;

    private MdbFlushCallback mdbFlushCallback;
    private MdbLoadCallback mdbLoadCallback;

    private int state;

    public MdbPlayerInfo(Long playerId, Object param, int state) {
        this.playerId = playerId;
        this.param = param;
        this.state = state;
    }


    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public long getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(long offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getFlushStartTime() {
        return flushStartTime;
    }

    public void setFlushStartTime(long flushStartTime) {
        this.flushStartTime = flushStartTime;
    }

    public MdbFlushCallback getMdbFlushCallback() {
        return mdbFlushCallback;
    }

    public void setMdbFlushCallback(MdbFlushCallback mdbFlushCallback) {
        this.mdbFlushCallback = mdbFlushCallback;
    }

    public MdbLoadCallback getMdbLoadCallback() {
        return mdbLoadCallback;
    }

    public void setMdbLoadCallback(MdbLoadCallback mdbLoadCallback) {
        this.mdbLoadCallback = mdbLoadCallback;
    }
}
