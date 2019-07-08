package com.example.demo4;

import com.example.custom_annotation.IndexDesc;
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
    public void test07() {
        System.out.println(Integer.toBinaryString(-1 >> 2));
        System.out.println(1 >> 2);
        System.out.println(-1 << 2);
    }

    @Test
    public void test08() {
        int num = 10000;
        System.out.println(num * 10 * 8);
    }

    @Test
    public void test09() {
        IndexDesc indexDesc = new IndexDesc();
        indexDesc.setName("name");
        indexDesc.setColumnNames(new String[]{"a", "b"});
        indexDesc.setUnique(true);
        System.out.println(indexDesc.toIndexDDL());
    }

    @Test
    public void test10() {
        List<Object> list = new ArrayList<>();
        list.add(0, 1);
        list.add(0, 2);
        System.out.println(list);
    }

    @Test
    public void test11() {
        int s = 50;
        int radio = 10;
        int radio1 = s / radio;
        for (int i = s; i >= 0; i = i - radio1) {
            System.out.println(i);
        }

    }

    @Test
    public void test12() {
        int s = 55;
        int ratio = 10;
        int radio1 = s / ratio;
      for(int i = 1; i < ratio; i++) {
          System.out.println(s%i);
      }

    }

}
