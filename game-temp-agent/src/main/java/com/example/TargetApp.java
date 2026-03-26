package com.example;

/**
 * @author zhuxiaoqing
 * @Description: TargetApp
 * @Date 2026/3/26 20:40
 **/
public class TargetApp {
    public static void main(String[] args) throws Exception {
        while (true) {
            say();
            Thread.sleep(3000);
        }
    }

    public static void say() {
        System.out.println("old");
    }
}
