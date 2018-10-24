package com.example.demo3;

import com.example.snowflakeIdWorker.RobotRidObj;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Test08 {
    @Test
    public void test1() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("b", "b");
        map.put("a", "b");
        map.put("c", "b");
        System.out.println(map);
        System.out.println(map.remove(null));
    }

    @Test
    public void test2() {
        int i = 0;
        System.out.println(i++ < 1);
    }

    @Test
    public void test3() {
        int x = 10;
        int[] a = {100, x};
        int[] b = {1, 10};
        int i = countRangeMappingRange2(a, 90, b);
        System.out.println(i);
    }

    private int countRangeMappingRange2(int[] knowRange, int know, int[] unknownRange) {
        int knowIndex = Math.abs(know - knowRange[0]);

        int difference1 = Math.abs(knowRange[1] - knowRange[0]) + 1;
        int unDifference = Math.abs(unknownRange[1] - unknownRange[0]) + 1;
        double divide = (difference1 + 0.0) / unDifference;

        int unKnowIndex = (int) (knowIndex / divide);
        int result = unknownRange[0] > unknownRange[1] ? unknownRange[0] - unKnowIndex : unknownRange[0] + unKnowIndex;
        return result;
    }

    @Test
    public void test4() {
        HashSet<Object> objects = new HashSet<>();
        System.out.println(objects.add(null));
        System.out.println(objects.add(null));
        System.out.println(objects.add(null));
    }

    @Test
    public void test5() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        String format = formatter.format(zonedDateTime);
        System.out.println(format);
    }

    static int lastRandom = 0;
    static int randomSpace = 3;

    public static int random(int rank) {
        int nextInt = ThreadLocalRandom.current().nextInt(lastRandom, lastRandom + randomSpace);
        // 随机数 + 1 让其不会和前面的重复
        lastRandom = nextInt + 1;
        return rank + nextInt;
    }

    @Test
    public void test6() {
        for (int x = 0; x <= 100; x++) {
            int random = random(x);
            System.out.println(random);
        }
    }


    /**
     * 这个不行垃圾
     *
     * @param knowRange
     * @param know
     * @param unknownRange
     * @return
     */
    private int countRangeMappingRange(int[] knowRange, int know, int[] unknownRange) {
        // 获取 know 在 knowRange 区间(knowRange[0] 相当于一个数组的 0 下标 knowRange[1]相当于一个数组的 (length-1) 下标)里面的下标
        //int knowIndex = Math.abs(know - knowRange[0]);

        // 因为 knowRange[1] - knowRange[0] 只是 (length-1) 所以还需要 + 1
        int difference1 = Math.abs(knowRange[1] - knowRange[0]) + 1;
        int unDifference = Math.abs(unknownRange[1] - unknownRange[0]) + 1;
        // 得出 knowRange 和 unknownRange 下标映射百分比
        double divide = (unDifference + 0.0) / difference1;
        return (int) (know * divide);
        // 得出 knowIndex 在 knowRange 下标 对应 unknownRange 所在的下标
        // int unKnowIndex = (int) (knowIndex / divide);
        // 得出 unKnowIndex 下标所在 unknownRange 的值
        //return unknownRange[0] > unknownRange[1] ? unknownRange[0] - unKnowIndex : unknownRange[0] + unKnowIndex;

    }

    @Test
    public void test7() {
        int x = 10;
        int[] a = {100, x};
        int[] b = {1, 10};
        int i = countRangeMappingRange(a, 70, b);
        System.out.println(i);
    }

    @Test
    public void test8() {
        while (true) {
            int i = ThreadLocalRandom.current().nextInt(1, 6);
            System.out.println(i);
        }
    }

    @Test
    public void test9() {
        int n = 1;
        int i = n << 23;
        System.out.println(n);
        System.out.println(i);
    }

    @Test
    public void test10() {
        int n = 2; // 0001
        int x = 3; // 0011
        int four = 4; // 1000
        System.out.println((n & x) != 0);
        System.out.println((n & four) != 0);
    }

}















