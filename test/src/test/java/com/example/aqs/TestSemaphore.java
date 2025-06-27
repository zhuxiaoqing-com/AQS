package com.example.aqs;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    private static Semaphore sem = new Semaphore(0);

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            sem.acquireUninterruptibly();
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            sem.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000000; i++) {
            Thread t1 = new Thread1();
            Thread t2 = new Thread1();
            Thread t3 = new Thread2();
            Thread t4 = new Thread2();
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            System.out.println(i);
        }
    }
}
