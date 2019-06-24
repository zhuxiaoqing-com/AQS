package com.example.demo4;

import org.junit.Test;

public class Test04 {

    @Test
    public void test01() {
        long start = System.currentTimeMillis();
        for(int i =0; i<Integer.MAX_VALUE;i++) {
            System.currentTimeMillis();
        }

        long end = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        for(int i =0; i<Integer.MAX_VALUE;i++) {
            System.currentTimeMillis();
        }

        long end2 = System.currentTimeMillis();
        System.out.println((end2 - start2));
    }
}
