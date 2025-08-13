package com.room;

import com.PacketWraper;

import com.google.protobuf.Message;
import kcp.Ukcp;

/**
 * 第一次进来走正常流程; 掉线以后重连进来;也还需要走 connect;joinRoom;Process;Ready
 */
public class Player {
    Room room;
    Ukcp ukcp;
    private Long playerId;
    private int sendFrameCount;
    private int process; // 客户端加载进度
    private boolean online;
    private boolean ready;
    private long lastHeartbeat;

    public boolean debug_receiveInputStateChange;


    public Player(Long playerId) {
        this.playerId = playerId;
    }

    public boolean isOnline() {
        return online;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Ukcp getUkcp() {
        return ukcp;
    }

    public void setUkcp(Ukcp ukcp) {
        this.ukcp = ukcp;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void send(int id, Message message) {
        if(!isOnline()) {
            return;
        }
        PacketWraper.send(ukcp,id, message);
    }

    public long getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(long lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public int getSendFrameCount() {
        return sendFrameCount;
    }

    public void setSendFrameCount(int sendFrameCount) {
        this.sendFrameCount = sendFrameCount;
    }
}
