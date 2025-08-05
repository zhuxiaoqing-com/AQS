package com.room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Room {
    public static final int READY = 0;
    public static final int GAMING = 1;
    public static final int OVER = 2;
    public static final int STOP = 3;


    long createTime;
    int state;

    public static final int maxReadyTime = 10; // 准备阶段最长时间
    public static final int maxGamingTime = 20 * 60 * 1000; // 游戏时长最长20分钟
    public static final int maxFameDataPerMsg = 60; // 每隔消息包最多包含多少帧数据
    public static final int broadcastOffsetFrames = 2; // 每隔多少帧广播一次

    public static final int FramesPerSecond = 20; // 每秒20帧
    public static final int TickTimerMill = 1000 / FramesPerSecond; // 帧间隔时间 毫秒


    public static final int heartTimeout = 5 * 1000; // 5秒心跳超时

    public int roomId;
    public Map<Long, Player> playerMap = new HashMap<>();

    public Room(List<Player> playerList) {
        this.createTime = System.currentTimeMillis();
        this.state = READY;
        for (Player player : playerList) {
            playerMap.put(player.getPlayerId(), player);
        }

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::tick, 0, FramesPerSecond, TimeUnit.MILLISECONDS);
    }

    public void tick() {
        switch (state) {
            case READY:
                long onlineNum = playerMap.values().stream().filter(Player::isOnline).count();
                if (System.currentTimeMillis() - createTime > maxReadyTime) {
                    if (onlineNum <= 0) {
                        state = OVER;
                    } else {
                        doStart();
                        state = GAMING;
                    }
                } else {
                    if (onlineNum >= playerMap.size()) {
                        doStart();
                        state = GAMING;
                    }
                }
                break;
        }
    }


    public void doStart() {

    }
}
