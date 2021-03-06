package com.example.demo4;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

public class Test01 {
    @Test
    public void test1() {
        int s = 2222222;
        System.out.println(Integer.toBinaryString(s));
        int i = s ^ (s >>> 16);
        System.out.println(Integer.toBinaryString(i));
    }

    @Test
    public void test2() {
        int x = 5;
        System.out.println(17 & x);
        System.out.println(18 & x);
        System.out.println(19 & x);
        System.out.println(15 & x);
        System.out.println(14 & x);
    }

    @Test
    public void test3() {
        int num = 23;
        for (int x = 0; x < 10; x++) {
            double v = Math.random();
            System.out.println(v);
            System.out.println((int) (v * num));
        }
    }

    @Test
    public void test4() {
        int[] x = new int[]{1, 2, 3, 4};
        int[] x1 = new int[0];
        int[] ints = Arrays.stream(x1).filter(z -> z == 1).toArray();
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void test5() {
        HashMap<Object, Object> map = new HashMap<>();
        A a = null;
        for (int x = 0; x <= 20; x++) {
            map.put(a = new A(), x);
        }
        System.out.println(map.get(a));
    }

    @Test
    public void test6() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        LinkedList<Object> objects = new LinkedList<>();
        for(int x= 1; x<=8; x++) {
            objects.add(x);
        }
        Collections.shuffle(objects);
        System.out.println(objects);
    }

    @Test
    public void test7() {
        Integer s = 2;
        s.compareTo(null);
    }

    @Test
    public void test8() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new s() {
            @Override
            public void run() {
                System.out.println("run");
            }

            @Override
            public Object call() throws Exception {
                System.out.println("call");
                return 1;
            }
        });
    }
    interface s extends Runnable, Callable{

    }

    @Test
    public void test9() {
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(0xFFFFFFFF);
        System.out.println(Integer.toBinaryString(-1));
    }


    @Test
    public void test11() {
        String s = "电话";
        char[] chars = s.toCharArray();
        System.out.println(Arrays.toString(chars));
    }
    @Test
    public void test12() {
        String s = "电话";
        char[] chars = s.toCharArray();
        System.out.println((long)chars[0]);
    }
    @Test
    public void test13() {
        int s = (24*100)/1688;
        System.out.println(s);
    }






}

class A {
    @Override
    public int hashCode() {
        return 1;
    }
}

class B extends A {

}

















