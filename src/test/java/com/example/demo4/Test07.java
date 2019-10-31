package com.example.demo4;

import org.testng.annotations.Test;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Test07 {


    @Test
    public void test01() throws CloneNotSupportedException {
        A a = new A();
        A clone = a.clone();
        System.out.println(a.atomic.hashCode());
        System.out.println(clone.atomic.hashCode());
        System.out.println(clone.atomic == a.atomic);
    }

    class A implements Cloneable{
        AtomicLong atomic = new AtomicLong();

        @Override
        protected A clone() throws CloneNotSupportedException {
            return (A) super.clone();
        }
    }

    @Test
    public void test02()  {
        PriorityQueue<Object> bossReviveQueue = new PriorityQueue<>();
        boolean add = bossReviveQueue.add(new Object());
        Object peek = bossReviveQueue.peek();
        System.out.println(peek);
        System.out.println(bossReviveQueue.poll());
        System.out.println(peek);
    }
}



















