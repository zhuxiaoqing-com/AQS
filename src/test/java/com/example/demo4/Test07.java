package com.example.demo4;

import com.example.demo1.util.ProtostuffSerializer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Test07 {


    @Test
    public void test01() throws CloneNotSupportedException {
        com.example.demo4.Test07.A a = new com.example.demo4.Test07.A();
        com.example.demo4.Test07.A clone = a.clone();
        System.out.println(a.atomic.hashCode());
        System.out.println(clone.atomic.hashCode());
        System.out.println(clone.atomic == a.atomic);
    }

    class A implements Cloneable {
        AtomicLong atomic = new AtomicLong();

        @Override
        protected com.example.demo4.Test07.A clone() throws CloneNotSupportedException {
            return (com.example.demo4.Test07.A) super.clone();
        }
    }

    @Test
    public void test02() {
        PriorityQueue<Object> bossReviveQueue = new PriorityQueue<>();
        boolean add = bossReviveQueue.add(new Object());
        Object peek = bossReviveQueue.peek();
        System.out.println(peek);
        System.out.println(bossReviveQueue.poll());
        System.out.println(peek);
    }

    @Test
    public void test03() {
        Integer a = 2;
        System.out.println(a.equals(2L));
        System.out.println(a.equals(2));
    }

    public void a(Object a) {
        Integer aa = 1;
        Long ll = 2L;
        System.out.println(ll.equals(2));
    }

    @Test
    public void test04() {
        String s = "\"\"";
        System.out.println(s.substring(1, -1));
    }

    @Test
    public void test05() {
        System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        System.out.println(6);
        System.out.println(7);
        System.out.println(8);
        System.out.println(9);
        System.out.println(10);
    }

    @Test
    public void test06() {
        B a = new B();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            list.add(i);
        }
        a.setList(list);


        new Thread(() -> {
            while (true) {
                List<Integer> list1 = a.getList();


                list1.add(1);
                list1.remove(1);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                ProtostuffSerializer.getInstance().encode(a, B.class);
            }
        }).start();

        while (true) {

        }

    }

    class B {
        private int i;
        private List<Integer> list;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }


    @Test
    public void test07() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.sort((a, b) -> {
            int i = a - b;
            System.out.println(i);
            return i;
        });
        System.out.println(list);


        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list2.add(i);
        }

        list2.sort((a, b) -> {
            int i = b - a;
            System.out.println(i);
            return i;
        });
        System.out.println(list2);
    }

    @Test
    public void test08() {
        ArrayList<List<Integer>> list = new ArrayList<>();
        a(list, Integer.class);
    }

    private <T> void a(List<List<T>> list, Class<T> c) {
        HashMap<Object, Object> hashMap = new HashMap<>();
    }

    @Test
    public void test09() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(0);

        list.sort((a, b) -> a - b);
        System.out.println(list);
    }

    @Test
    public void test10() {
        int a = 100000*10000;
        System.out.println(a);
        System.out.println(Integer.MAX_VALUE);
    }


}



















