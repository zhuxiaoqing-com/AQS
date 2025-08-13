package com.room;

import com.PacketWraper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.pb.*;
import kcp.Ukcp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.pb.ID.*;

public class Game {
    static Logger logger = LoggerFactory.getLogger(Game.class);
    static int nextRoomId = 1;
    static Map<Long, Room> roomMap = new HashMap<>();

    public static void createRoom(long roomId, List<Integer> members) {
        // 创建房间;
        List<Player> collect = members.stream().map(a -> new Player(a.longValue())).collect(Collectors.toList());
        roomMap.put(roomId, new Room(collect));
        logger.info("Room: " + roomId + " created, players: " + collect);
    }

    public static void onMessageProcess(Ukcp ukcp, int id, byte[] data) throws Exception {
        if (id != MSG_Input_VALUE && id != MSG_Heartbeat_VALUE) {
            logger.info("onMessageProcess id {} ", id);
        }
        switch (id) {
            case MSG_Connect_VALUE:
                C2S_ConnectMsg c2SConnectMsg = C2S_ConnectMsg.parseFrom(data);
                onMsgConnect(ukcp, c2SConnectMsg);
                break;
            case MSG_JoinRoom_VALUE:
                Player player = ukcp.user().getCache();
                if(player == null || !player.isOnline()) {
                    return;
                }
                S2C_JoinRoomMsg.Builder builder = S2C_JoinRoomMsg.newBuilder();
                builder.setRandomSeed(player.getRoom().getRandomSeed());
                builder.setRoomseatid(player.getRoom().roomId);
                for (Player value : player.getRoom().playerMap.values()) {
                    if(Objects.equals(value.getPlayerId(), player.getPlayerId())) {
                        continue;
                    }
                    builder.addOthers(value.getPlayerId());
                    builder.addPros(value.getProcess());
                }

                player.send(MSG_JoinRoom_VALUE, builder.build());
                break;
            case MSG_Progress_VALUE:
                C2S_ProgressMsg c2SProgressMsg = C2S_ProgressMsg.parseFrom(data);
                player = ukcp.user().getCache();
                if(player == null || !player.isOnline()) {
                    return;
                }
                if (player.getRoom().getState() > Room.READY) {
                    return;
                }
                int pro = c2SProgressMsg.getPro();
                player.setProcess(pro);

                S2C_ProgressMsg.Builder s2c = S2C_ProgressMsg.newBuilder()
                        .setId(player.getPlayerId())
                        .setPro(pro);
                player.getRoom().sendAllExclude(MSG_Progress_VALUE, s2c.build(), player.getPlayerId());
                break;

            case MSG_Heartbeat_VALUE:
                player = ukcp.user().getCache();
                if(player == null || !player.isOnline()) {
                    return;
                }
                player.send(MSG_Heartbeat_VALUE, null);
                player.setLastHeartbeat(System.currentTimeMillis());
                break;
            case MSG_Ready_VALUE:
                player = ukcp.user().getCache();
                if(player == null || !player.isOnline()) {
                    return;
                }
                // 准备阶段
                if (player.getRoom().getState() == Room.READY) {
                    player.getRoom().doReady(player);
                }

                // 进行中阶段, 重连
                if(player.getRoom().getState() == Room.GAMING) {
                   player.getRoom().doReconnect(player);
                }
                break;
            case MSG_Input_VALUE:
                player = ukcp.user().getCache();
                if (player == null || !player.isOnline() || !player.isReady()) {
                    return;
                }
                C2S_InputMsg c2SInputMsg = C2S_InputMsg.parseFrom(data);
                player.getRoom().pushInput(player, c2SInputMsg);
                break;
            case MSG_Result_VALUE:
                player = ukcp.user().getCache();
                if (player == null || !player.isOnline() || !player.isReady()) {
                    return;
                }
                C2S_ResultMsg c2SResultMsg = C2S_ResultMsg.parseFrom(data);
                player.getRoom().onBattleResult(player, c2SResultMsg);
                break;
        }
    }

    private static void onMsgConnect(Ukcp ukcp, C2S_ConnectMsg c2SConnectMsg) throws InvalidProtocolBufferException {

        long playerID = c2SConnectMsg.getPlayerID();
        long roomId = c2SConnectMsg.getBattleID();
        String token = c2SConnectMsg.getToken();

        S2C_ConnectMsg.Builder builder = S2C_ConnectMsg.newBuilder();
        builder.setErrorCode(ERRORCODE.ERR_Ok);
        Room room = roomMap.get(roomId);
        if (room == null) {
            builder.setErrorCode(ERRORCODE.ERR_NoRoom);
            PacketWraper.send(ukcp, MSG_Connect_VALUE, builder.build());
            logger.error("Room: " + roomId + " not found");
            return;
        }

        Player player = room.playerMap.get(playerID);
        if (!room.playerMap.containsKey(playerID)) {
            builder.setErrorCode(ERRORCODE.ERR_NoPlayer);
            PacketWraper.send(ukcp, MSG_Connect_VALUE, builder.build());
            logger.error("Player: " + playerID + " not found");
            return;
        }

        if (room.getState() == Room.OVER) {
            builder.setErrorCode(ERRORCODE.ERR_RoomState);
            PacketWraper.send(ukcp, MSG_Connect_VALUE, builder.build());
            logger.error("Room: " + roomId + " is over");
        }

        // 如果玩家已经在线;
        if (player.isOnline()) {
            Ukcp oldUkcp = player.getUkcp();
            oldUkcp.close();
            logger.warn("Player: " + playerID + " is online");
        }

        player.setOnline(true);
        player.setUkcp(ukcp);
        ukcp.user().setCache(player);
        logger.info("Player: " + playerID + " is connected");
        PacketWraper.send(ukcp, MSG_Connect_VALUE, builder.build());
    }

    /**
     * 网络线程和用户线程没有分开，可能会有并发
     */
    public static void onNetWorkClose(Ukcp ukcp) {
        Player player = ukcp.user().getCache();
        if (player == null) {
            return;
        }
        Room room = player.getRoom();
        if (room == null) {
            return;
        }

        room.onPlayerOffline(player);
    }

    public static void destroyRoom(Room room) {
        roomMap.remove(room.getRoomId());
    }
}








