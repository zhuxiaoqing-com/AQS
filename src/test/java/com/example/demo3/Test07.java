package com.example.demo3;

import com.example.snowflakeIdWorker.RobotRidObj;
import org.junit.Test;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.time.*;
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
        String s = "abc113";
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
        System.out.println(-1L ^ (-1L << 5));
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
        System.out.println((long) 1 << 34);
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
        System.out.println((long) 452 << 24);
    }

    @Test
    public void test9() {
        long l = System.currentTimeMillis() - 1420041600000L;
        System.out.println(l);
        System.out.println((1L << 38) / (1000L * 60 * 60 * 24 * 365));
    }

    @Test
    public void test10() {
        System.out.println(-1L ^ (-1L << 16));
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
        int ss = 8 + 4 + 3 * 7 + 4 + 4;
        int Obj = objHead + ss;
        System.out.println(objHead + ss);
        int nowObj;
        if (Obj % 8 == 0) {
            nowObj = Obj;
        } else {
            int i = Obj / 8;
            nowObj = (i + 1) * 8;
        }

        System.out.println(nowObj);
        System.out.println(nowObj * 40000 / 1024 / 1024);
    }

    @Test
    public void test15() {
        Map map = new HashMap<>();
        map.put(Integer.parseInt("1"), 2);
        System.out.println(map.get(1));
    }

    @Test
    public void test16() {
        Long l = 1L;
        System.out.println(l.equals(1));
    }

    @Test
    public void test17() throws ParseException {
        String cron = "";

        /*CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("Caclulate Date").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        BaseCalendar baseCalendar = new BaseCalendar();
        baseCalendar.setDescription();

        TriggerUtils.computeFireTimes(trigger, baseCalendar,)*/

        CronExpression cronExpression = new CronExpression("0/10 * * * * ?");
        System.out.println(cronExpression.getNextValidTimeAfter(new Date()));

    }

    @Test
    public void test18() {
        System.out.println(new Date(findSignUpDate(73800000, 10800000, 3600000)));
        System.out.println(new Date(findStartDate(73800000)));
    }

    private long findSignUpDate(long openTime, long signUpTime, long durationTime) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        long zeroTime = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return zeroTime + openTime - signUpTime;
    }

    private long findStartDate(long openTime) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        long zeroTime = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return zeroTime + openTime;
    }


    @Test
    public void test19() {
        long a = (-1L ^ (-1L << 32))+1;
        System.out.println(111+3);
        int b = (int)(111+(a+3));
        System.out.println(b);
        System.out.println(Integer.toBinaryString(111+3));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Long.toBinaryString(111+(a+3)));
    }
}















