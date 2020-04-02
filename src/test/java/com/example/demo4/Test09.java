package com.example.demo4;

import org.junit.Test;

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
        System.out.println(1-0.9);
    }
}























