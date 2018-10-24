package com.example.snowflakeIdWorker;

/**
 * 生成 robot id 的类
 *
 */
public class RobotRidObj1111 {
    private static RobotRidObj1111 INSTANCE = new RobotRidObj1111();

    public static RobotRidObj1111 getInstance() {
        return INSTANCE;
    }

    /**最大序列 */
    private final long sequenceMask = -1L^(-1L<<21);

    /**十进制位数 */
    private final long decimalBits = countDecimalBits();

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    /**
     * 时间相同就序列递增
     * 然后时间和递增的序列 为小数部分
     */

    /**
     * 获得下一个 rid
     * @return SnowflakeId
     */
    double nextRid(long s) {
        long l = System.currentTimeMillis();
        if(l == lastTimestamp) {
            sequence += 1;
            if(sequence >= decimalBits) {
                while (System.currentTimeMillis()== l){}
                sequence = 0L;
            }
        } else {
            sequence = 0L;
        }
        //l = (l-startTime)*decimalBits+sequence;
        String score = s +"."+l;

        return -Double.valueOf(score);
    }



    private long countDecimalBits() {
        long  decimalBits = 1;
        long temp = sequenceMask;
        while ((temp = temp / 10) != 0) {
            decimalBits *= 10;
        }
        return  decimalBits;
    }


}
