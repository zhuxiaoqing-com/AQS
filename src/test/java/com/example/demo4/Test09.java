package com.example.demo4;

import org.junit.Test;

import java.util.TreeMap;

public class Test09 {


    @Test
    public void test01() {
        new Thread(() -> System.out.println("我是匿名函数")).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是匿名函数");
            }
        }).start();
    }

    @Test
    public void test02() {
        System.out.println(1 - 0.9);
    }

    @Test
    public void test03() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "3,3,3");
        map.put(10, "10,10,10");
        map.put(100, "100,100,100");

        int paramInt = 2;

        System.out.println(map.ceilingEntry(paramInt));
        System.out.println(map.floorEntry(paramInt));
    }


    @Test
    public void test04() {
        float a = Float.MAX_VALUE;
        System.out.println(a);
        float aa = a * 1.1f;
        System.out.println(aa);
        System.out.println(0 / aa);
        System.out.println(Float.valueOf(aa / 0).intValue());
    }

    @Test
    public void test05() {
        System.out.println("");
        for (int i = 1; i < 1000; i++) {

        }
        Test08.staticFun();
    }

    @Test
    public void test06() {
        for (int i = 1; i < testSize(); i++) {

        }
    }

    private int testSize() {
        System.out.println("testSize");
        return 100;
    }


    @Test
    public void test07() {
        long startTime = System.nanoTime();
        int index =1111232;
        for (int i = 0; i <= 100000; i++) {
            int high = 0xFFFF0000 & index;
            int low = 0xFFFF & index;
        }

        System.out.println((System.nanoTime() - startTime)/1000);
    }

    public static short getIntLow(int index) {
        return (short) (0xFFFF & index);
    }

    public static short getIntHigh(int index) {
        return (short) ((0xFFFF0000 & index) >> 16);
    }

    @Test
    public void test08() {
        // 0,3 1,4 一对;
        System.out.println(3%2);
    }
}























