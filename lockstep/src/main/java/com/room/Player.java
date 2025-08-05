package com.room;

import com.sun.org.apache.regexp.internal.RE;

public class Player {
    private Long playerId;
    private long clientFrameIndex;
    private int process; // 客户端加载进度

    public Player(Long playerId) {
        this.playerId = playerId;
    }

    public boolean isOnline() {
        return true;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public long getClientFrameIndex() {
        return clientFrameIndex;
    }

    public void setClientFrameIndex(long clientFrameIndex) {
        this.clientFrameIndex = clientFrameIndex;
    }
}
