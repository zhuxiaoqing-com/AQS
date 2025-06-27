package com.example.TestArithmetic.snowflakeIdWorker;

import java.util.concurrent.ThreadLocalRandom;

public class SnowflakeIdWorker2 {

    /**前缀所占的位数 */
    private final long prefixBits = 10L;

    /**排名所占的位数 65535 */
    private final long rankBits = 16L;

    /**career 所占的位数 最大 63  */
    private final long careerBits = 6L;

    /** 最大支持的 排名 131071 */
    private final long maxRank = -1L ^ (-1L << rankBits);

    /** 最大支持的 前缀 1023 */
    private final long maxPrefix = -1L ^ (-1L << prefixBits);

    /** 最大支持的 职业序列号 127 */
    private final long maxCareer = -1L ^ (-1L << careerBits);

    /** 排名向左移 6L */
    private final long rankOffSet = careerBits;

    /** 前缀向左移 (16+6)22 位 */
    private final long prefixOffSet = rankBits + careerBits;

    /** 随机数向左移 (10+16+6)32L */
    private final long randomOffSet = prefixBits + rankBits + careerBits;

    /**
     * 获得下一个 rid
     * @return SnowflakeId
     */
    public Long nextRid(int prefix, int rank, int career) {
        if(rank > maxRank) {
            return null;
        }
        if(career > careerBits) {
            return null;
        }
        if(prefix > maxPrefix) {
            return null;
        }

        long i = ThreadLocalRandom.current().nextInt(1, 10000);
        return (i << randomOffSet)
        | (prefix << prefixOffSet)
        | (rank << rankOffSet)
        | career;
    }

    /**
     * 根据 rid 查找 rank
     * @param rid
     * @return
     */
    public long findRank(long rid) {
        return (rid & (maxRank << rankOffSet)) >> rankOffSet;
    }

    /**
     * 根据 rid 查找 career
     * @param rid
     * @return
     */
    public long findCareer(long rid) {
        return (rid & (maxCareer));
    }

    /**
     * 根据 rid 查找 前缀
     * @param rid
     * @return
     */
    public long findPrefix(long rid) {
        return (rid & (maxPrefix << prefixOffSet)) >> prefixOffSet;
    }
}
