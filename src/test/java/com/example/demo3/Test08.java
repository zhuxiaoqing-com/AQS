package com.example.demo3;

import com.example.snowflakeIdWorker.BitMap;
import com.example.snowflakeIdWorker.RobotRidObj;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.springframework.stereotype.Controller;

import java.sql.SQLOutput;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.*;

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

    @Test
    public void test11() {
        int[] bitMap = BitMap.create(1111);
        BitMap.add(bitMap, 232);
        BitMap.add(bitMap, 231);
        BitMap.add(bitMap, 234);
        BitMap.add(bitMap, 23);
        BitMap.print(bitMap);
    }

    @Test
    public void test12() {
        System.out.println(1 << 32);
    }

    @Test
    public void test13() {
        BitSet bitSet = new BitSet(0);
        bitSet.and(bitSet); // &
        bitSet.andNot(bitSet); // & ~
        bitSet.or(bitSet); // |
        bitSet.xor(bitSet);// ^

        bitSet.nextSetBit(0);


    }

    @Test
    public void test14() {
        String b = "a";
        BitSet bitSet = new BitSet(11111);
        System.out.println(A.temp);
        //System.out.println(A.temp2);
//      System.out.println(new A());
    }

    @Test
    public void test15() {
        int[] ints = new int[2];
        ints.clone();
        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>();
        objects.remove(2);
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.remove(2);
    }


    @Test
    public void test16() {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.poll();
        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
        set.remove(2);

        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();
        ConcurrentHashMap.KeySetView<Object, Boolean> objects = ConcurrentHashMap.newKeySet();
        boolean add = objects.add(2);

    }

    @Test
    public void test17() {
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String format = formatter.format(localDate);
        System.out.println(format);
    }

    @Test
    public void test18() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer i : integers) {
            if(i == 2) continue;
            System.out.println(i);
        }
    }

    @Test
    public void test19() {
        String hour1 = "09:20";
        String hour2 = "09:35";
        DateTimeFormatter isoLocalTime = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime parse1 = LocalTime.parse(hour1, isoLocalTime);
        LocalTime parse2 = LocalTime.parse(hour2, isoLocalTime);
        LocalTime now = LocalTime.now();
        System.out.println(now.isAfter(parse1) && now.isBefore(parse2));
    }

    @Test
    public void test20() {
        System.out.println(Long.toBinaryString(System.currentTimeMillis()).length());
    }

    @Test
    public void test21() {
        System.out.println(-1L^(-1L<<41));
        System.out.println(System.currentTimeMillis()+1000*60*60*24*365*20);
    }

    @Test
    public void test22() {
        System.out.println(-1L^(-1L<<14));
        System.out.println(System.currentTimeMillis()+1000*60*60*24*365*20);
    }

    @Test
    public void test23() {
        A a = new A();
        System.out.println(A.a);
    }

    @Test
    public void test24() {
        List<Integer> list = Arrays.asList(1, 2, 4, 5);
        Optional<Integer> any = list.stream().findAny();
        System.out.println(any.get());
    }

    @Test
    public void test25() {
        double v = (1.2 + 0.0) / 10;
        System.out.println((1.2+0.0)/10);
        System.out.println((int)(v*10)*10);
    }

    @Test
    public void test26() {
     HashMap<Object, Object> map = new HashMap<>();
        map.put(1,true);
        map.put(2,true);
         /*  Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            map.put(iterator.next().getKey(), false);
        }
        System.out.println(map);*/
        map.forEach((x,y)->map.put(x, false));
        System.out.println(map);
    }

    @Test
    public void test27() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        //Collections.shuffle(integers);
        for (int i=integers.size(); i>1; i--){
            int index = 3;//ThreadLocalRandom.current().nextInt(i);
            Collections.swap(integers, i-1, index);
        }

        System.out.println(integers);

    }
    @Test
    public void test28() {
        double x = 1.1;
        System.out.println(Math.ceil(x));
    }


}

class A {
    static {
        a = 3;
    }
    static int a = 1;
    static final int temp = 1;
    static String temp2 = "a";

    static {
        System.out.println("A初始化了!!!");
    }
}















