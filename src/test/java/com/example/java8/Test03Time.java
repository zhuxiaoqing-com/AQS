package com.example.java8;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Test03Time {
    @Test
    public void test01() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parse = LocalTime.of(4,22);
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
}
