package com.mdb.util;

public class TimeUtils {

    // 一毫秒(单位：纳秒)
    public static final long millsNano = 1_000_000;
    public static final long secondMills = 1000;
    public static final long minuteMills = 1000 * 60L;
    public static final long hourMills = 1000 * 60 * 60L;
    public static final long dayMills = hourMills * 24L;
    public static final long weekMills = dayMills * 7L;

    public static long jvmStartTick;

    static {
        jvmStartTick = System.nanoTime();
    }


    /**
     * 后面有可能改成jvm启动时间
     */
    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static long getMinuteConst() {
        return minuteMills * millsNano;
    }


}
