package com.example.demo4;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.*;

public class Test04 {

    @Test
    public void test01() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.currentTimeMillis();
        }

        long end = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.currentTimeMillis();
        }

        long end2 = System.currentTimeMillis();
        System.out.println((end2 - start2));
    }

    @Test
    public void test02() {
        String s = "\\|";
        String s1 = "|";
        String aa = "1|3|2|3|1";
        System.out.println(Arrays.toString(aa.split(s)));
        System.out.println(Arrays.toString(aa.split(s1)));
    }

    @Test
    public void test03() {
        String s = "0|203015|1&0|103|10&0|42001|10&0|42100|5&0|42123|5&0|101|150000";
        System.out.println(s.length());
    }

    @Test
    public void test04() {
        String s = "383490927757018";
        System.out.println(s.length());
    }

    @Test
    public void test05() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i <= 10000; i++) {
            list.add(i);
        }

        System.out.println(list.toString().length());
        System.out.println(list.toString());
    }


    @Test
    public void test06() {
        long a = 100_000_000_000_000L;
        long b = 123456789;
        StringBuilder builder = new StringBuilder(200);
        HashMap<Long, Long> hashMap = new LinkedHashMap<>();

        for (int i = 0; i <= 600; i++) {
            hashMap.put(a + i, b);
        }
        Set<Map.Entry<Long, Long>> entries = hashMap.entrySet();
        int rank = 1;
        for (Map.Entry<Long, Long> entry : entries) {

            builder.append(entry.getKey());
            builder.append("|");
            builder.append(rank);
            builder.append("|");
            builder.append(entry.getValue());
            builder.append("&");
        }
        System.out.println(builder.subSequence(0, builder.length() - 1));
    }

    @Test
    public void test07(){
        System.out.println(Integer.toBinaryString(-1>>2));
        System.out.println(1>>2);
        System.out.println(-1<<2);
    }

    @Test
    public void test08(){
       String s = "A894110C0002EEEEEEEEEEEE7F0000008D1E54117E16";
        System.out.println(Long.parseLong(s, 16));
    }

}
