package com.room;

import com.google.protobuf.Message;
import com.google.protobuf.TextFormat;
import com.pb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.pb.ID.*;

public class Room {
    Logger logger = LoggerFactory.getLogger(Room.class);

    public static final int READY = 0;
    public static final int GAMING = 1;
    public static final int OVER = 2;
    public static final int STOP = 3;


    public static final int maxReadyTime = 10 * 1000; // 准备阶段最长时间
    public static final int maxGamingTime = 20 * 60 * 1000; // 游戏时长最长20分钟
    public static final int maxFameDataPerMsg = 60; // 每隔消息包最多包含多少帧数据
    public static final int broadcastOffsetFrames = 1; // 每隔多少帧广播一次

    public static final int FramesPerSecond = 30; // 每秒20帧
    public static final int TickTimerMill = 1000 / FramesPerSecond; // 帧间隔时间 毫秒


    public static final int heartTimeout = 5 * 1000; // 5秒心跳超时


    public int roomId;
    long createTime;
    long startTime;
    long allOfflineTime;
    int state = READY;

    public Map<Long, Player> playerMap = new HashMap<>();
    public int randomSeed;
    private int clientFrameIndex;
    private boolean dirty;
    FrameManager frameManager = new FrameManager();
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> scheduledFuture = null;

    // 只是做一个记录; 有超过1个人的话 可以简单对比结果,看是否一致; 都一致就算通过(没有管大家都在一起作弊的情况, 因为正常是系统匹配的不会出现问题)
    // 还有一种就是不管结果; 直接去校验服进行获取结果, 然后对比结果,看哪个不同  就是出问题了,作弊或者客户端不同步了
    public Map<Long, C2S_ResultMsg> resultMsgMap = new HashMap<>();

    public Room(List<Player> playerList) {
        this.createTime = System.currentTimeMillis();
        this.state = READY;
        for (Player player : playerList) {
            player.setRoom(this);
            playerMap.put(player.getPlayerId(), player);
        }
        randomSeed = ThreadLocalRandom.current().nextInt(0, 10000);

        scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(this::tick, 0, TickTimerMill, TimeUnit.MILLISECONDS);
    }

    public void tick() {
        switch (state) {
            case READY:
                long readyNum = playerMap.values().stream().filter(Player::isReady).count();
                if (System.currentTimeMillis() - createTime > maxReadyTime) {
                    if (readyNum <= 0) { // 全都没连进来，直接结束
                        state = OVER;
                    } else {
                        doStart();
                        state = GAMING;
                    }
                } else {
                    if (readyNum >= playerMap.size()) { // 大于最大准备时间，只要有在线的，就强制开始
                        doStart();
                        logger.info("room start ");
                        state = GAMING;
                    }
                }
                break;
            case GAMING:
                if (checkOver()) {
                    state = OVER;
                    logger.info("room over ");
                    return;
                }
                frameManager.tick(this);
                broadcastFrameData();
                checkPlayerHeartTimeout();
                break;
            case OVER:
                Game.destroyRoom(this);
                scheduledExecutorService.shutdown();
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                break;
        }
    }

    private void checkPlayerHeartTimeout() {
        long now = System.currentTimeMillis();
        for (Player value : playerMap.values()) {
            if (!value.isOnline() || !value.isReady()) {
                continue;
            }
            if (now - value.getLastHeartbeat() < heartTimeout) {
                continue;
            }
            // 关闭连接
            value.getUkcp().close();
        }
    }


    private boolean checkOver() {
        if (System.currentTimeMillis() - createTime > maxGamingTime) {
            logger.error("游戏时间太久了 强行结束游戏 roomId {} playerIDs {} ", roomId, playerMap.keySet());
            return true;
        }


        // 检测是否已经全部结算; 所有在线的玩家都结算了 或者所有玩家都离线 但是都结算了

        // 所有玩家都结算了
        if (playerMap.size() == resultMsgMap.size()) {
            return true;
        }

        // 如果还有在线玩家就检测是否所有的在线玩家都结算了
        if (allOfflineTime <= 0) {
            // 只要有人没发结果并且还在线，就不结束
            for (Player value : playerMap.values()) {
                if (!value.isOnline()) {
                    continue;
                }
                C2S_ResultMsg c2SResultMsg = resultMsgMap.get(value.getPlayerId());
                if (c2SResultMsg == null) {
                    return false;
                }
            }
            return true;
        }

        // 所有玩家都下线了  副本还能保持10秒
        if (allOfflineTime > 0) {
            if (System.currentTimeMillis() - allOfflineTime > 10 * 1000) {
                logger.error("玩家都下线了 强行结束游戏 roomId {} playerIDs {} ", roomId, playerMap.keySet());
                return true;
            }
        }

        return false;
    }


    public void doStart() {
        clientFrameIndex = 0;
        for (Player value : playerMap.values()) {
            if (!value.isOnline()) {
                continue;
            }
            value.setReady(true);
            value.setProcess(100);
        }
        startTime = System.currentTimeMillis();
        S2C_StartMsg.Builder builder = S2C_StartMsg.newBuilder().setTimeStamp(startTime);
        sendAll(MSG_Start_VALUE, builder.build());
    }


    public void onPlayerOffline(Player player) {
        player.setUkcp(null);
        player.setOnline(false);
        player.setReady(false);

        long count = playerMap.values().stream().filter(Player::isOnline).count();
        if (count <= 0) {
            allOfflineTime = System.currentTimeMillis();
        }
    }

    public void sendAll(int id, Message message) {
        sendAllExclude(id, message, 0);
    }

    public void sendAllExclude(int id, Message message, long playerId) {
        for (Player value : playerMap.values()) {
            if (playerId > 0 && value.getPlayerId() == playerId) {
                continue;
            }
            value.send(id, message);
        }
    }


    public int getState() {
        return state;
    }

    public int getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(int randomSeed) {
        this.randomSeed = randomSeed;
    }

    public long getRoomId() {
        return roomId;
    }


    public void doReady(Player player) {
        if (!player.isReady()) {
            player.setReady(true);
            player.send(MSG_Ready_VALUE, null);
            logger.info("doReady roomId {} playerId {}", roomId, player.getPlayerId());
        }

    }

    public void onBattleResult(Player player, C2S_ResultMsg c2SResultMsg) {
        resultMsgMap.put(player.getPlayerId(), c2SResultMsg);
        logger.info("onBattleResult roomId {} playerId {} pb {}", roomId, player.getPlayerId(), TextFormat.printer().shortDebugString(c2SResultMsg));
    }


    public void pushInput(Player player, C2S_InputMsg c2SInputMsg) {
        dirty = true;
        InputData.Builder builder = InputData.newBuilder().setInput(c2SInputMsg.getInput())
                .setEvent(c2SInputMsg.getEvent())
                .setId(player.getPlayerId())
                .setRoomseatid(0);
        Frame frame = frameManager.pushInput(builder.build());

        if(builder.getInput().getSid() > 0) {
            if(player.debug_receiveInputStateChange != true) {
                player.debug_receiveInputStateChange = true;
                logger.error("玩家输入input 移动 frameId {} time {} ", frame.getFrameId(), System.currentTimeMillis());
            }
        }else {
            if(player.debug_receiveInputStateChange != false) {
                player.debug_receiveInputStateChange = false;
                logger.error("玩家输入input 停止移动 frameId {} time {}", frame.getFrameId(), System.currentTimeMillis());
            }
        }
    }


    private void broadcastFrameData() {
        //logger.error("tick  {}", System.currentTimeMillis());
        int currentFrame = frameManager.getCurrentFrame();
        if (!dirty && currentFrame - 1 - clientFrameIndex < broadcastOffsetFrames) {
            return;
        }
        dirty = false;
        clientFrameIndex = currentFrame - 1;

        long now = System.currentTimeMillis();
        for (Player player : playerMap.values()) {
            if (!player.isOnline() || !player.isReady()) {
                continue;
            }

            int sendFrameCount = player.getSendFrameCount();

            S2C_FrameMsg.Builder frameMsg = S2C_FrameMsg.newBuilder();

            for (int i = sendFrameCount + 1; i < currentFrame; i++) {
                Frame frame = frameManager.getFrame(i);
                //如果是null就只广播最后一帧, 其他帧不需要,客户端会自动补
                if (frame == null && i != currentFrame - 1) {
                    continue;
                }

                FrameData.Builder frameData = FrameData.newBuilder();
                frameData.setFrameID(i);
                if (frame != null) {
                    for (List<InputData> value : frame.getInputMap().values()) {
                        frameData.addAllInput(value);
                    }
                }
                frameMsg.addFrames(frameData);
                logger.error("sendInput sid {} frameId {}  time {} ",
                        frameData.getInputCount() <= 0 ? 0 : frameData.getInput(0).getInput().getSid(), frameData.getFrameID(), System.currentTimeMillis());
                // 如果是最后一帧或者达到这个消息包能装下的最大帧数，就发送
                if (i == currentFrame - 1 || i - player.getSendFrameCount() >= maxFameDataPerMsg) {
                    player.send(MSG_Frame_VALUE, frameMsg.build());
                    player.setSendFrameCount(i);
                    frameMsg.clear();
                }
            }
        }
    }


    public void doReconnect(Player player) {
        player.setReady(true);
        allOfflineTime = 0;

        player.setSendFrameCount(0);// 从头开始发
        logger.info("doReconnect roomId {} playerId {}", roomId, player.getPlayerId());
        startTime = System.currentTimeMillis();
        S2C_StartMsg.Builder builder = S2C_StartMsg.newBuilder().setTimeStamp(startTime);
        player.send(MSG_Start_VALUE, builder.build());

        S2C_FrameMsg.Builder s2cFrameMsg = S2C_FrameMsg.newBuilder();
        for (int l = 0; l < clientFrameIndex; l++) {
            Frame frame = frameManager.getFrame(l);
            if (frame == null && l != clientFrameIndex - 1) {
                continue;
            }

            FrameData.Builder frameData = FrameData.newBuilder();
            frameData.setFrameID(l);
            if (frame != null) {
                for (List<InputData> value : frame.getInputMap().values()) {
                    frameData.addAllInput(value);
                }
            }
            s2cFrameMsg.addFrames(frameData);
            // 如果是最后一帧或者达到这个消息包能装下的最大帧数，就发送
            if (l == clientFrameIndex - 1 || l - player.getSendFrameCount() >= maxFameDataPerMsg) {
                player.send(MSG_Frame_VALUE, s2cFrameMsg.build());
                s2cFrameMsg.clear();
            }
        }

    }

}
