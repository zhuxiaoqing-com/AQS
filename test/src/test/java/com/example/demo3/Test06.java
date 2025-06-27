package com.example.demo3;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Test06 {

    @Test
    public void fun1() {
        double s1 = 1.1;
        double s2 = 1.0;
        System.out.println((long)s1 == s1);
        System.out.println((long)s2 == s2);
    }

    @Test
    public void fun2() {
        double s1 = 1111.111111111111111111111111111111111111111111111;
        double s2 = 1.0;
        //System.out.println((int)s1 == s1);
        //System.out.println((int)s2 == s2);
        System.out.println((int) s1);
    }

    @Test
    public void fun3() {
        try{
            System.out.println(1);
        } finally {
            System.out.println(3);
        }
        System.out.println(2);
    }

    @Test
    public void fun4() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<Number> collect = list.stream().collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 测试 switch case 在匹配到 case 以后
     */
    @Test
    public void fun5() {

        switch (4) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
            case 6:
                System.out.println(6);
            case 7:
                System.out.println(7);
        }
    }
    @Test
    public void fun6() {
        System.out.println(-2>0);
    }

    @Test
    public void fun7() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        System.out.println(hashMap.putIfAbsent(1, "ss"));
        System.out.println(hashMap.putIfAbsent(1, "dd"));
    }

    @Test
    public void fun8() {
        long sum = 0;
        for(long i= 1; i<= 1111111111L; i++) {
            sum += i;
            sum = sum -1L;
            sum = sum +1L;
            sum = sum +2L;
        }
        System.out.println(sum);
    }

    @Test
    public void fun9() {
        int sum = 0;
        for(int i= 1; i<= 1111111111; i++) {
            sum += i;
            sum = sum -1;
            sum = sum +1;
            sum = sum +2;
        }
        System.out.println(sum);
    }

    // 2 s 952
    @Test
    public void fun10() {
        int i1 = 2;
        long l2 = 2222222222L;
        for(long i= 1; i<= 11111111111L; i++) {
            long s = (long)i1;
        }
    }

    // 2 s 997
    @Test
    public void fun11() {
        int i1 = 2;
        long l2 = 2222222222L;
        for(long i= 1; i<= 11111111111L; i++) {
            int s = i1;
        }
    }


    // 2 s 997
    @Test
    public void fun12() {
        System.out.println((8+1)&8);
        System.out.println(4095&4095);
        System.out.println(4096&4095);
        System.out.println(8&8);

    }

    @Test
    public void fun13() {
        String s = Long.toBinaryString(status);
        System.out.println(s);
        long process = process(5);
        System.out.println(Long.toBinaryString(process));


    }
    long status = 10;

    /**
     * 插入
     * @param offset
     * @return
     */
    public long process(int offset) {
        int s = offset - 1;
        Long i = 1L << s;
        status = status | i;
        return status;
    }

    /**
     * 比较是否存在
     * 存在的话就会返会值
     * 不存在返回 0
     */
    public long process2(int offset) {
        System.out.println(Long.toBinaryString(1 << (offset - 1)));
        long l = status & (1 << (offset - 1));
        return l;
    }
    @Test
    public void fun14() {
        System.out.println(Long.toBinaryString(status));
        long process = process2(2);
        System.out.println(process);
        System.out.println(Long.toBinaryString(status));
    }

    @Test
    public void fun15() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> collect = integers.stream().sorted((x, y) -> x > y ? -1 : 1).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void fun16() {
        System.out.println(new ArrayList<>(new HashSet<>()));
    }

    @Test
    public void fun17() {
        List<Integer> integers = Arrays.asList();
        System.out.println(integers);
        Optional<Integer> any = integers.stream().sorted((x, y) -> x > y ? -1 : 1).findAny();
        System.out.println(any.orElseGet(()->null));
    }


    @Test
    public void fun18() {
        List<Integer> integers = Arrays.asList(1,2,3);
        System.out.println(integers);
        Optional<Integer> any = integers.stream().skip(-1).findAny();
        System.out.println(any.orElseGet(()->null));
    }

    @Test
    public void fun19() {
       String 哈哈 = "0002";
        System.out.println(Integer.parseInt(哈哈));
    }

    @Test
    public void fun20() {
        long s = 100000000000000L;
        System.out.println(s*2+1);
    }

    @Test
    public void fun21() {
        System.out.println(randomNoRepeatNums(1, 4,3));
    }

    /**
     * 就是 如果已经有值了 就使用当前可以的最大值来进行替换已经有的值
     * 没值就直接使用随机的值
      * @param min
     * @param max
     * @param n
     * @return
     */
    public static List<Integer> randomNoRepeatNums(int min, int max, int n) {
        int range = max - min + 1;
        //当下列情况任何一个成立时，返回null
        //①指定数字范围的最小值大于最大值，
        //②需要随机获取的数字个数小于0，
        //③需要随机获取的数字个数大于指定数字范围的数字个数
        List<Integer> randomlist = new ArrayList<>();
        if (min > max || n < 0 || n > range) {
            return randomlist;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //随机产生一个数，若已存在则取其对应的值,每次随机范围减一，即去掉上次的最大值
            int num = ThreadLocalRandom.current().nextInt(range - i) + min;
            randomlist.add(map.getOrDefault(num, num));

            //将被随机到的数，用map映射当前最大值（表示该值已被取走，用当前最大值替换该值,继续随机）
            int j = max - i;
            map.put(num, map.getOrDefault(j, j));
        }

        return randomlist;

    }

    /**
     * 位移操作不能对小数 double float 使用
     */
    @Test
    public void fun22() {
        Map<Long,Long> map = new HashMap<>();
        map.put(1L,1L);
        Long aLong = map.get(1L);
        System.out.println(aLong);
        map.clear();
        System.out.println(aLong);
    }




    @Test
    public void fun23() {
        System.out.println(-1L^(-1L<<20));
    }

    @Test
    public void fun25() {
        String s = (123456789123L+"."+1234567891123456789L);
        String s2 = (123456789123L+"."+1234567891123456787L);
        //String s = (123478123L+"");
        System.out.println(s);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.parseDouble(s2)>Double.parseDouble(s));
    }

    @Test
    public void fun24() {
        System.out.println(countDecimalBits());
    }

    private long countDecimalBits() {
        long  decimalBits = 1;
        long temp = 2111L;
        while ((temp = temp / 10) != 0) {
            decimalBits *= 10;
        }
        return  decimalBits;
    }
}














