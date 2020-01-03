package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.example.demo1.util.ProtostuffSerializer;
import com.example.demo4.testObj1.Child;
import com.example.demo4.testObj1.Temp;
import com.example.javase.DecimalToBit;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
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

        list.sort((a, b) -> b - a);
        System.out.println(list);
    }

    @Test
    public void test10() {
        int a = 100000 * 10000;
        System.out.println(a);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void test11() {
        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 1);
        hashMap.put(2, 1);
        String s = JSON.toJSONString(hashMap);
        System.out.println(s);

        Object parse = JSON.parseObject(s);
        System.out.println(parse);
    }

    @Test
    public void test12() {
       /* toBinary(0.5);
        toBinary(1.3);*/
        float a = 7.8125f;
        DecimalToBit.getInstance().toBinary(a);
        int l = Float.floatToRawIntBits(a);
        long l1 = Float.floatToIntBits(a);
        System.out.println(Long.toBinaryString(l));
        System.out.println(Integer.toBinaryString(l));
        System.out.println(l1);
        System.out.println(Integer.toBinaryString(129));

    }

    @Test
    public void test14() {
        System.out.println("111111010100000000000000000000".equals("11111101010000000000000000000"));
        // 1000000111110100000000000000000
        // 1000000111110100000000000000000
    }


    /**
     * 0.3 转 double 二进制数
     * 2^8 = 256
     * 256 -1 = 255
     * 0.0
     * 255
     * 0.3 = 1*M*2^E
     * <p>
     * 0.0100110011001100110011001100110011001100110011001100110011001100110011001100110011001100110011001100
     * 0 100110011001100110011001100110011001100110011001100110011001100110011001100110011001100110011001100
     * <p>
     * 0.8125f
     * 0.1101
     * 需要处理成1.xxx 这样才能变成有隐含的 1。
     * 往左移动 1.101  需要 * 2; *2 就要把 阶数 -1 。因为这里的2 是从阶数这里借的。
     * 00000000  -1 =  e - 127  -> -1+127 = e -> 126 = e  ->  e  bit= 1111110;
     * 1.101
     * 0 01111110 1010 0000 0000 0000 0000 00
     * <p>
     * <p>
     * 111.8125f
     * 111.1101 -> 1.111101 右移了二位
     *
     *  阶数 +2
     * 00000000  2 =  e - 127  -> 2+127 = e -> 129 = e  ->  e  bit= 10000001;
     * 110 = e-127 -> 110 + 127 = e  -> 237 = e
     * 1.101
     * 0 10000001 1111 0100 0000 0000 0000 000
     */
    @Test
    public void test13() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void test15() {
        long n = 32;
        long memory = (long) Math.pow(2, n);
        long memoryKb = (long) (memory/Math.pow(2,10));
        System.out.println(memoryKb/4);
    }

    public static final String DEFAULT_FORMAT = "YYYY-MM-dd";
    public static DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);

    @Test
    public void test16() throws ParseException {
        String dateStr = "2019-12-29";
        TemporalAccessor parse = SIMPLE_DATE_FORMAT.parse(dateStr);
        System.out.println(parse);
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
        Date parse1 = format.parse(dateStr);
        System.out.println(parse1);
    }

    @Test
    public void test17() throws ParseException {
        Temp a = new Temp(1, "a",new Child(1,"child"));
        String s = JSON.toJSONString(a);
        System.out.println(s);
        //System.out.println(JSON.parseObject(s, Temp.class).getClass());
        System.out.println(JSON.parseObject(s,Temp.class));

    }
    @Test
    public void test18() throws Exception {
        Constructor<?>[] constructors = Temp.class.getConstructors();
        Object a = constructors[0].newInstance(1, "a");
        System.out.println(a);
    }


    @Test
    public void test19() throws Exception {
        Temp a = new Temp(1, "a",new Child(1,"child"));
        String s = JSON.toJSONString(a);
        System.out.println(s);
        //System.out.println(JSON.parseObject(s, Temp.class).getClass());
        System.out.println(JSON.parseObject(s,Temp.class));
    }


}

















