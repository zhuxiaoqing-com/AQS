package com.example.TestArithmetic.snowflakeIdWorker;

/**
 * 生成 robot id 的类
 *
 * @author zhuxiaoqing create 2018/10/11
 */
public class ArenaRobotRidObj {
    private static ArenaRobotRidObj INSTANCE = new ArenaRobotRidObj();

    public static ArenaRobotRidObj getInstance() {
        return INSTANCE;
    }

    /** 开始时间截 (2018-01-01) 38位 8年 */
    private final long startTime = 1514736000000L;

    /**前缀所占的位数 7 */
    private final long rankTypeBits = 3L;

    /**排名所占的位数 16383 */
    private final long rankBits = 14L;

    /**career 所占的位数 最大 15  */
    private final long careerBits = 4L;

    /** 毫秒内序列(0~15) */
    private final long sequenceBits = 4L;

    /** 最大支持的 排名 16383 */
    private final long maxRank = -1L ^ (-1L << rankBits);

    /** 最大支持的 前缀 15 */
    private final long maxRankType = -1L ^ (-1L << rankTypeBits);

    /** 最大支持的 职业序列号 31 */
    private final long maxCareer = -1L ^ (-1L << careerBits);

    /** 最大支持的 毫秒内序列 31 */
    private final long maxSequence = -1L ^ (-1L << careerBits);

    /** 排名向左移 4L */
    private final long careerOffSet = sequenceBits;

    /** 排名向左移 (4+4) 8L */
    private final long rankOffSet = careerBits + sequenceBits;

    /** 前缀向左移 (14+4+4)23 位 */
    private final long rankTypeOffSet = rankBits + rankOffSet;

    /** 随机数向左移 (3+14+4+4)25L */
    private final long timeOffSet = rankTypeBits + rankTypeOffSet;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    /** 上次生成rank */
    private long lastRank = -1L;

    /** 上次生成rankType */
    private long lastRankType = -1L;

    /**
     * 获得下一个 rid
     * @return SnowflakeId
     */
    Long nextRid(long rankType, long rank, long career) {
        if(rank > maxRank) {
            return null;
        }
        if(career > maxCareer) {
            return null;
        }
        if(rankType > maxRankType) {
            return null;
        }
        long currTime = System.currentTimeMillis();

        // 如果 rankType 和 rank 都相等
        if(lastRankType == rankType && lastRank == rank) {
            if (lastTimestamp == currTime) {
                sequence = (sequence + 1) & maxSequence;
            }
        }
        // 保存 rankType  rank currTime
        lastRankType = rankType;
        lastRank = rank;
        lastTimestamp = currTime;

        return ((currTime - startTime) << timeOffSet)
                | (rankType << rankTypeOffSet)
                | (rank << rankOffSet)
                | (career << careerOffSet)
                | sequence;
    }

    /**
     * 根据 rid 查找 rank
     * @param rid
     * @return
     */
    long findRank(long rid) {
        return (rid & (maxRank << rankOffSet)) >> rankOffSet;
    }

    /**
     * 根据 rid 查找 career
     * @param rid
     * @return
     */
    long findCareer(long rid) {
        return (rid & (maxCareer<< careerOffSet)) >> careerOffSet;
    }

    /**
     * 根据 rid 查找 前缀
     * @param rid
     * @return
     */
    long findRankType(long rid) {
        return (rid & (maxRankType << rankTypeOffSet)) >> rankTypeOffSet;
    }
}
