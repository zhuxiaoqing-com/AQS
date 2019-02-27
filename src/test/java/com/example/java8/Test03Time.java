package com.example.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Test03Time {
    @Test
    public void test01() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parse = LocalTime.of(4, 22);
        System.out.println(parse);
    }

    @Test
    public void test02() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String s = "24:00:00";
        TemporalAccessor parse1 = dateTimeFormatter.parse(s);
        LocalTime parse = LocalTime.parse(s);
        System.out.println(parse);
    }

    @Test
    public void test03() {
        // LocalDateTime 转时间戳
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        long nextBornTime = zonedDateTime.toInstant().toEpochMilli();

        // 时间戳转 localDataTime localData LocalTime
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        ZonedDateTime zonedDateTime1 = instant.atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime2 = zonedDateTime1.toLocalDateTime();

        // LocalDate 转 LocalDateTime
        LocalDateTime localDateTime1 = LocalDate.now().atStartOfDay();
        LocalDateTime localDateTime3 = LocalDate.now().atTime(LocalTime.now());

        // LocalTime 转 LocalDateTime
        LocalDateTime localDateTime5 = LocalTime.now().atDate(LocalDate.now());
        System.out.println(localDateTime5);

    }
    @Test
    public void test04() {
        LocalDateTime now = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalTime localTime = now.toLocalTime();

        LocalTime localTime1 = localTime.plusMinutes(111);
        System.out.println(localTime1);
    }
}
