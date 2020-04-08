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
}























