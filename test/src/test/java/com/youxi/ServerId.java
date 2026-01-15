package com.youxi;

/**
 * @author zhuxiaoqing
 * @Description: ServerID
 * @Date 2026/1/15 14:33
 **/
public class ServerId {


    // ---------- bit width ----------
    public static final int RESERVE_BITS     = 27;
    public static final int INDEX_BITS       = 5;
    public static final int PLAYER_SID_BITS  = 16;
    public static final int SERVER_TYPE_BITS = 6;
    public static final int PLATFORM_BITS    = 9;

    // ---------- bit shift ----------
    public static final int RESERVE_SHIFT     = 0;
    public static final int INDEX_SHIFT       = RESERVE_SHIFT + RESERVE_BITS;         // 27
    public static final int PLAYER_SID_SHIFT  = INDEX_SHIFT + INDEX_BITS;              // 32
    public static final int SERVER_TYPE_SHIFT = PLAYER_SID_SHIFT + PLAYER_SID_BITS;    // 48
    public static final int PLATFORM_SHIFT    = SERVER_TYPE_SHIFT + SERVER_TYPE_BITS;  // 54

    // ---------- masks ----------
    public static final long RESERVE_MASK     = (1L << RESERVE_BITS) - 1;
    public static final long INDEX_MASK       = (1L << INDEX_BITS) - 1;
    public static final long PLAYER_SID_MASK  = (1L << PLAYER_SID_BITS) - 1;
    public static final long SERVER_TYPE_MASK = (1L << SERVER_TYPE_BITS) - 1;
    public static final long PLATFORM_MASK    = (1L << PLATFORM_BITS) - 1;


    int platform;
    int serverType;
    int playerSid;
    int index;
    int reserve;

    long serverId;

    public ServerId(int platform, int serverType, int playerSid, int index, int reserve) {
        // -------- 参数合法性校验（强烈建议保留）--------
        checkRange(platform, PLATFORM_BITS, "platform");
        checkRange(serverType, SERVER_TYPE_BITS, "serverType");
        checkRange(playerSid, PLAYER_SID_BITS, "playerSid");
        checkRange(index, INDEX_BITS, "index");
        checkRange(reserve, RESERVE_BITS, "reserve");

        this.platform = platform;
        this.serverType = serverType;
        this.playerSid = playerSid;
        this.index = index;
        this.reserve = reserve;

        serverId = ((long) platform   & PLATFORM_MASK)   << PLATFORM_SHIFT
                | ((long) serverType & SERVER_TYPE_MASK) << SERVER_TYPE_SHIFT
                | ((long) playerSid  & PLAYER_SID_MASK)  << PLAYER_SID_SHIFT
                | ((long) index      & INDEX_MASK)       << INDEX_SHIFT
                | ((long) reserve    & RESERVE_MASK);
    }

    public static ServerId parse(long serviceId) {
        // 可选：符号位校验（防脏数据）
        if (serviceId < 0) {
            throw new IllegalArgumentException("serviceId must be non-negative: " + serviceId);
        }

        int platform   = getPlatform(serviceId);
        int serverType = getServerType(serviceId);
        int playerSid  = getPlayerSid(serviceId);
        int index      = getIndex(serviceId);
        int reserve    = getReserve(serviceId);

        return new ServerId(
                platform,
                serverType,
                playerSid,
                index,
                reserve
        );
    }

    public static int getPlatform(long serviceId) {
        return (int) ((serviceId >>> PLATFORM_SHIFT) & PLATFORM_MASK);
    }

    public static int getServerType(long serviceId) {
        return (int) ((serviceId >>> SERVER_TYPE_SHIFT) & SERVER_TYPE_MASK);
    }

    public static int getPlayerSid(long serviceId) {
        return (int) ((serviceId >>> PLAYER_SID_SHIFT) & PLAYER_SID_MASK);
    }

    public static int getIndex(long serviceId) {
        return (int) ((serviceId >>> INDEX_SHIFT) & INDEX_MASK);
    }

    public static int getReserve(long serviceId) {
        return (int) (serviceId & RESERVE_MASK);
    }

    private static void checkRange(int value, int bits, String name) {
        int max = (1 << bits) - 1;
        if (value < 0 || value > max) {
            throw new IllegalArgumentException(
                    name + " out of range: " + value + ", max=" + max
            );
        }
    }

    public static void main(String[] args) {
        ServerId serviceId = new ServerId(
                1,      // platform
                3,      // serverType
                1001,   // playerSid
                2,      // index
                0       // reserve
        );

        System.out.println("platform   = " + ServerId.getPlatform(serviceId.serverId));
        System.out.println("serverType = " + ServerId.getServerType(serviceId.serverId));
        System.out.println("playerSid  = " + ServerId.getPlayerSid(serviceId.serverId));
        System.out.println("index      = " + ServerId.getIndex(serviceId.serverId));

    }

}
