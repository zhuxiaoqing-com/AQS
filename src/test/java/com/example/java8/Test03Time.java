package com.example.java8;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Test03Time {
    @Test
    public void test01() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parse = LocalTime.of(4,22);
        System.out.println(parse);
    }
}
