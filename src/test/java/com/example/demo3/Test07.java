package com.example.demo3;

import com.example.snowflakeIdWorker.RobotRidObj;
import org.junit.Test;
import sun.instrument.InstrumentationImpl;

import java.lang.instrument.Instrumentation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test07 {
    @Test
    public void test1() {
        Long s = 211L;
        System.out.println(s.equals(211));
        System.out.println(s.equals(211L));
        System.out.println(s == 211);
        System.out.println(s == 211L);
    }
    @Test
    public void test2() {
        String  s = "abc113";
        String spilt = "abc";
        System.out.println(s.substring(spilt.length()));
    }

    @Test
    public void test3() {
        int prefix = 312;
        int rank = 1111;
        long time = 999999999L;
        int random = 2222;

        int i = (prefix << 4) | rank;
    }

    @Test
    public void test4() {
        System.out.println(-1L^(-1L << 5));
    }

    @Test
    public void test5() {
        RobotRidObj snowflakeIdWorker = new RobotRidObj();
        Long aLong = snowflakeIdWorker.nextRid(1, 5181, 2);
        System.out.println(aLong.toString().length());
        System.out.println(aLong);
        long rank1 = snowflakeIdWorker.findPrefix(aLong);
        long rank2 = snowflakeIdWorker.findRank(aLong);
        long rank3 = snowflakeIdWorker.findCareer(aLong);
        System.out.println(rank1);
        System.out.println(rank2);
        System.out.println(rank3);
        System.out.println(Long.MAX_VALUE);
        System.out.println((long)1<<34);
    }

    @Test
    public void test6() {
        long rid = 2222 << 7;
        System.out.println(rid & (131071 << 7));
    }

    @Test
    public void test7() {
        long rid = 7 << 1;
        System.out.println(rid & (8 << 1));
    }

    @Test
    public void test8() {
        System.out.println((long)452<<24);
    }

    @Test
    public void test9() {
        long l = System.currentTimeMillis() - 1420041600000L;
        System.out.println(l);
        System.out.println((1L << 38) / (1000L * 60 * 60 * 24 * 365) );
    }
    @Test
    public void test10() {
        System.out.println(-1L^(-1L << 16));
    }

    @Test
    public void test11() {
        LocalDate of = LocalDate.of(2018, 1, 1);
        LocalDateTime localDateTime = of.atTime(0, 0);
        long l = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    @Test
    public void test12() {
        int s = (0 + 1) & 7;
        System.out.println(s);
    }

    @Test
    public void test13() {
        int s = (0 + 1) & 7;
        System.out.println(s);
    }

    @Test
    public void test14() {
        int objHead = 12;
        int ss = 8+4+3*7+4+4;
        int Obj = objHead+ss;
        System.out.println(objHead+ss);
        int nowObj;
        if (Obj % 8 == 0) {
            nowObj = Obj;
        } else {
            int i = Obj / 8;
            nowObj = (i+1)*8;
        }

        System.out.println(nowObj);
        System.out.println(nowObj*40000/1024/1024);
    }

    @Test
    public void test15() {
        Map map  = new HashMap<>();
        map.put(Integer.parseInt("1"), 2);
        System.out.println(map.get(1));
    }

    @Test
    public void test16() {
        Long l = 1L;
        System.out.println(l.equals(1));
    }
}















