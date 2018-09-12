package com.example.demo1;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Test3 {
    ReentrantReadWriteLock rwk = new ReentrantReadWriteLock();
    ReentrantLock wk = new ReentrantLock();
    @Test
    public void fun1() {
        rwk.writeLock().lock();
        rwk.writeLock().lock();
        Condition condition = rwk.writeLock().newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun2() {
        Condition condition = rwk.writeLock().newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun3() {
        wk.unlock();
    }

    /**
     * 突然发现 park 以后 thread.interrupt(); 竟然会瞬间醒过来
     * 运行结果 1 2 醒过来了啦！
     */
    @Test
    public void fun4() {
        Thread thread = Thread.currentThread();
        new Thread(()->{
            try {
                Thread.sleep(1000);
                thread.interrupt();
                System.out.println("2");
                //LockSupport.unpark(thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("1");
        LockSupport.park();
        System.out.println("醒过来了啦！");
    }

    @Test
    public void fun5() {
        //暗示调度器让当前线程出让正在使用的处理器。调度器可自由地忽略这种暗示。也就是说让或者不让是看心情哒
        Thread.yield();
    }

    @Test
    public void fun6() {
        Thread thread2 = new Thread(() -> testWk(() -> {
            return "thread2";
        }),"thread2");
        Thread thread1 = new Thread(() -> testWk(() -> {
            try {
                Thread.sleep(500);
                thread2.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "thread1";
        }),"thread1");


        thread1.start();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testWk(Supplier supplier) {
        try {
            wk.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println("xx");
        }
        String temp = null;
        try{
            temp = supplier.get().toString();
        } catch (Exception e) {
            System.out.println("出现异常了!!!");
        }finally {
            System.out.println(Thread.currentThread().getName());
            temp = temp;
            wk.unlock();
        }
    }

    @Test
    public void fun7() {
        Thread thread1 = Thread.currentThread();
        thread1.interrupt();
        LockSupport.park();
        LockSupport.park();
        LockSupport.park();
        LockSupport.park();
        System.out.println("取消了吗");
        System.out.println(thread1.isInterrupted());
    }


}

























