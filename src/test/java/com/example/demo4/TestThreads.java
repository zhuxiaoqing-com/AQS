package com.example.demo4;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TestThreads {

    /**
     * START 这段代码不能动 START
     */
    public static void main(String[] args) throws Exception {

        final Object key = new Object();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (true) {
            //记录一下
            System.out.println("历史：" + simpleDateFormat.format(new Date()) + "战争打响！");
            //开炮人就位检查营长的意大利炮
            new ThreadShut(key).start();
            //检查了500年
            Thread.sleep(500L);
            //装弹人就位，等待指令装弹
            new ThreadBullet(key).start();
            //等2000年再开一炮
            Thread.sleep(2000L);

        }
    }


    /*负责开炮*/
    public static class ThreadShut extends Thread {

        private final Object key;

        public ThreadShut(Object key) {
            this.key = key;
        }

        @Override
        public void run() {
            synchronized (key) {
                try {
                    System.out.println("没有炮弹，快上弹");
                    key.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("开炮");
                System.out.println("==========");
            }

        }
    }


    /*负责装弹*/
    public static class ThreadBullet extends Thread {

        private final Object key;

        public ThreadBullet(Object key) {
            this.key = key;
        }

        @Override
        public void run() {
            synchronized (key) {
                System.out.println("正在装弹请等待");
                System.out.println("装弹完成");
                key.notify();
            }
        }
    }
}