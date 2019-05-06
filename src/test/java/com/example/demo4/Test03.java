package com.example.demo4;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Scanner;

public class Test03 {
    @Test
    public void test01() {
        Object innerObj = getInnerObj();
        System.out.println("2:.." + innerObj);
        System.out.println("4:.." + innerObj.toString());
    }

    private Object getInnerObj() {
        final int x = 3;

        class InnerObj {
            public InnerObj() {
                System.out.println("1:.." + x);
            }

            @Override
            public int hashCode() {
                System.out.println("3:.." + x);
                return x;
            }
        }

        return new InnerObj();
    }

    @Test
    public void test02() {
        long milli = 1000;
        System.out.println(30 * 60 * 1000);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(90);
        Socket accept = serverSocket.accept();
        Scanner scan = new Scanner(System.in);
        String next = scan.next();
        System.out.println(next);
        accept.close();
    }

    @Test
    public void test03() throws IOException {
        System.out.println(new Date(1556559030832L));
        System.out.println(new Date(1556571600000L));
        System.out.println(System.currentTimeMillis());

    }

    long localHour = 60L * 60 * 1000;
    long nowTime;

    @Test
    public void test04() {
        int nowHour = 20;
        nowTime = LocalDateTime.of(2019, 5, 5, nowHour, 1, 0, 0)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        int refreshHour = 19;
        System.out.println(!isTodaySplitByHour(nowTime - localHour, refreshHour));// 前 1 个小时刷新过
        System.out.println(!isTodaySplitByHour(nowTime - localHour * 2, refreshHour));// 前 2 个小时刷新过
        System.out.println(!isTodaySplitByHour(nowTime - localHour * 12, refreshHour));// 前 12 个小时刷新过
        System.out.println(!isTodaySplitByHour(nowTime - localHour * 29, refreshHour));// 前 29 个小时刷新过
    }


    public boolean isTodaySplitByHour(long time, int hour) {
        if (time > nowTime) {
            return true;
        }
        LocalDateTime resetDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN.plusHours(hour));
        long resetTime = resetDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (resetTime - time > localHour * 24) {
            return false;
        }

        return ((time - resetTime) * (nowTime - resetTime)) >= 0;
    }


    public boolean isResetByDay(int time, int hour) {
        LocalDateTime resetTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
        ;
        LocalDateTime localNextResetTime = LocalDateTime.of(resetTime.toLocalDate(), LocalTime.MIN.plusHours(hour));

        int resetHour = resetTime.getHour();
        if (resetHour >= hour) {
            localNextResetTime = localNextResetTime.plusDays(1);
        }
        long nextResetTime = localNextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTime = System.currentTimeMillis();
        return nowTime > nextResetTime;
    }

    public boolean isResetByWeek(int time, int day, int hour) {
        LocalDateTime localResetTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localNextResetTime = localResetTime.with(ChronoField.DAY_OF_WEEK, day);
        localNextResetTime = LocalDateTime.of(localNextResetTime.toLocalDate(), LocalTime.MIN.plusHours(hour));

        int resetWeekDay = localResetTime.get(ChronoField.DAY_OF_WEEK);
        int resetHour = localResetTime.getHour();

        if (resetWeekDay > day || (resetWeekDay == day && resetHour >= hour)) {
            localNextResetTime = localNextResetTime.plusWeeks(1);
        }
        long nextResetTime = localNextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTime = System.currentTimeMillis();
        return nowTime > nextResetTime;
    }


    public boolean isResetByDay1(int time, int hour) {
        LocalDateTime localResetTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDate localNextResetDate = localResetTime.toLocalDate();
        if (localResetTime.getHour() >= hour) {
            localNextResetDate = localNextResetDate.plusDays(1);
        }

        LocalDateTime localNextResetTime = LocalDateTime.of(localNextResetDate, LocalTime.MIN.plusHours(hour));
        long nextResetTime = localNextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTime = System.currentTimeMillis();
        return nowTime > nextResetTime;
    }

    public boolean isResetByWeek1(int time, int day, int hour) {
        LocalDateTime localResetTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDate localNextResetDate = localResetTime.toLocalDate().with(ChronoField.DAY_OF_WEEK, day);

        int resetWeekDay = localResetTime.get(ChronoField.DAY_OF_WEEK);
        int resetHour = localResetTime.getHour();

        if (resetWeekDay > day || (resetWeekDay == day && resetHour >= hour)) {
            localNextResetDate = localNextResetDate.plusWeeks(1);
        }
        LocalDateTime localNextResetTime = LocalDateTime.of(localNextResetDate, LocalTime.MIN.plusHours(hour));
        long nextResetTime = localNextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTime = System.currentTimeMillis();
        return nowTime > nextResetTime;
    }

    @Test
    public void test05() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime with = now.with(ChronoField.DAY_OF_WEEK, 2);

        System.out.println(with);
    }

}












