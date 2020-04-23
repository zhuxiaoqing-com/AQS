package com.example.demo4;

import com.example.demo1.util.GeomUtil;
import org.junit.Test;

import java.util.TreeMap;

public class Test10 {
    @Test
    public void test01() {
        long start = System.nanoTime();
        short s;
        for (int i = 1; i <= 100_000_000_0; i++) {
            s = 0x00;
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000);
    }

    @Test
    public void test02() {
        long start = System.nanoTime();
        short s;
        for (int i = 1; i <= 100_000_000_0; i++) {
            s = 0;
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000);
    }

    @Test
    public void test03() {
        float attr1 = 0.2f;
        float attr2 = 0.2f;
        float result1 = (1 + attr1) * (1 + attr2) - 1;
        System.out.println(result1);
        System.out.println(100 * 1.2 * 1.2);
        System.out.println(100 + 100 * result1);
        System.out.println(100 * (1 + attr1));
    }

    @Test
    public void test04() {
        // -2^31 <= int1 <= 2^31-1
        // -2^32 <= int1+int2 <= 2^32-2
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);
    }

    @Test
    public void test05() {
        double v = Math.atan2(2, 1);
        System.out.println((Math.toDegrees(v) + 360) % 360);
        System.out.println(Math.toDegrees(v));

        double v1 = GeomUtil.toUnityDegrees(v);
        System.out.println(v1);
    }


    @Test
    public void test06() {
        System.out.println(-115);
        System.out.println(90 - -115);
        System.out.println(90 + 115);
        System.out.println(90 + (-1));
    }
    /*
    360 - x +90 == 90 - x
    270 -x == 90-x  ?
     */

    @Test
    public void test07() {
       Long a = 0L;
       Long a1 = 3L;
       if(a ==a1){

       }
    }
}























