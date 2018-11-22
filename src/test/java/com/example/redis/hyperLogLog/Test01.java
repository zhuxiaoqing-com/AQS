package com.example.redis.hyperLogLog;

import org.junit.Test;

public class Test01 {
    @Test
    public void test01() {
        System.out.println(Long.toBinaryString(0xfff0000));
        System.out.println(Long.toBinaryString((-1L & 0xfff0000) >> 16));
        System.out.println(Long.toBinaryString((1000000 & 0xfff0000) >> 16));
    }

    @Test
    public void test02() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
