package com.example.demo4;

import com.example.demo1.demo.TimeUtil;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class Test02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //这里特别注意一下类路径必须这样写
        //获取指定包下的所有类
        Resource[] resources = resourcePatternResolver.getResources("classpath*:");

        MetadataReaderFactory metadata = new SimpleMetadataReaderFactory();
        for (Resource resource : resources) {
            System.out.println(resource);
            MetadataReader metadataReader = metadata.getMetadataReader(resource);
            ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
            sbd.setResource(resource);
            sbd.setSource(resource);
            candidates.add(sbd);
        }
        for (BeanDefinition beanDefinition : candidates) {
            String classname = beanDefinition.getBeanClassName();
            //扫描controller注解
            Controller c = Class.forName(classname).getAnnotation(Controller.class);
            //扫描Service注解
            Service s = Class.forName(classname).getAnnotation(Service.class);
            //扫描Component注解
            Component component = Class.forName(classname).getAnnotation(Component.class);
            if (c != null || s != null || component != null) {
                System.out.println(classname);
            }
        }
    }

    @Test
    public void test01() {
        int s = Integer.MAX_VALUE;
        int s2 = 100;
        int s3 = (int) (s * 100L / 100L);
        System.out.println(s3);
        System.out.println(s);
    }

    @Test
    public void test02() {
        int id = 3032;
        System.out.println(id % 1000);
    }

    @Test
    public void test03() {
        int id = 10000;
        int temp = 3;
        System.out.println(3 * 10000 / 10000);
        System.out.println(3 * 0.1 / 10000);
    }

    @Test
    public void test04() {
        long id = 1_000_000000;
        int temp = 3;
        System.out.println(id * id);
        System.out.println(Math.toIntExact(id * id));
    }

    @Test
    public void test05() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(3);
        objects.add(2);
        objects.add(4);
        objects.add(5);
        objects.add(6);
        objects.add(7);
        objects.add(8);
        objects.add(1);
        System.out.println(objects);

        Collections.sort(objects, (a, b) -> {
            return a - b;
        });
        System.out.println(objects);
    }

    @Test
    public void test06() {
        int hashcode = 3;
        int mask = 4 - 1;
        /**
         * 1...000
         * 1...101
         *
         * 从右到左
         */
        System.out.println(hashcode |= ~mask);
    }

    @Test
    public void test07() {
        ConcurrentSkipListSet c = new ConcurrentSkipListSet();
        c.add(2);
        c.add(2);
        System.out.println(c);
    }

    @Test
    public void test08() {
        String s = "2d";
        System.out.println(s == 2 + "d");
    }

    public String join(int x) {
        return x + "d";
    }

    @Test
    public void test09() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>() {
            {
                put(1, 1);
                put(2, 2);
                put(3, 3);
                put(4, 4);
                put(5, 5);
                put(6, 6);
                put(7, 7);
            }
        };
        /*map.forEach((key, value)->{
            System.out.println(key);
            map.remove(key);
        });*/
        Collection<Integer> values = map.values();
        for (Integer s : values) {
            System.out.println(s);
            map.remove(s);
        }
    }

    @Test
    public void test10() {
        List<Integer> integers = Arrays.asList(2, 3, 4, 1, 3, 5);
        Collections.sort(integers, (a, b) -> b - a);
        System.out.println(integers);
    }

    @Test
    public void test11() {
        System.out.println(exception());
    }

    public int exception() {
        int i = 10;
        try {
            int a = 1 / 0;
            return 1;
        } catch (Exception e) {
            return i;
        } finally {
            i = 20;
            return i;
        }
    }

    @Test
    public void test12() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        int i = 27714560 / 1000 / 60;
        System.out.println(i);
        System.out.println(new Date(1551229021000L));
    }

    @Test
    public void test13() {
        long s = Integer.MAX_VALUE + 4;
        System.out.println(s);
    }

    @Test
    public void test14() {
        long l = 2L * 60 * 60 * 1000;
        System.out.println(l);
        System.out.println(l / 4);
        System.out.println(l / (1000 * 60));
    }

    @Test
    public void test15() {
        System.out.println(0 % 2);
        System.out.println(2 % 0);
        System.out.println(new Date(1551517260000L));
    }

    @Test
    public void test16() { //开朗的帕吉特
        int max = 13234;
        int i = 13;
        System.out.println(i * 10000 / 13234);
        System.out.println(0 % 2);
        System.out.println(new Date(1551517260000L));
    }

    @Test
    public void test17() { //开朗的帕吉特
        int rank1 = 9400;
        int[] rank = new int[]{10000, 9001};
        int[] level = new int[]{10, 1};

        int total1 = rank[0] - rank[1] + 1;
        int total2 = level[0] - level[1] + 1;

        int i1 = rank[1] - rank1;

        double i = total1 * 1.0 / total2;

        System.out.println((10000 - rank1) / i);
    }

    /**
     * 1、找到排名所在的区间
     * 2、排名 和 等级的比例
     * 3、根据比例换算等级下标
     */

    @Test
    public void fun01() {
        int rank1 = 9002;
        int[] rank = new int[]{10000, 9001};
        int[] level = new int[]{1, 10};

        double k = (level[1] - level[0]) * 1.0 / (rank[0] - rank[1]);

        int y = (int) (level[0] + k * (rank[0] - rank1));
        System.out.println(y);
        int i = countRangeMappingRange(rank, rank1, level);
        System.out.println(i);
    }

    private int countRangeMappingRange(int[] knowRange, int know, int[] unknownRange) {
        // 获取 know 在 knowRange 的下标 know - knowRange[0] 代表着 know 与 knowRange[0] 相差多少
        int knowIndex = Math.abs(know - knowRange[0]);

        // 因为 knowRange[0] - knowRange[1] 只是 得出 knowRange[1] 的下标 为 (length-1) 所以还需要 + 1
        int length = Math.abs(knowRange[0] - knowRange[1]) + 1;
        int unLength = Math.abs(unknownRange[0] - unknownRange[1]) + 1;
        // 得出 一份 length 对应几分 unLength
        double percent = (unLength + 0.0) / length;

        // 得出 knowIndex 在 knowRange 下标 对应 unknownRange 所在的下标
        int unKnowIndex = (int) (knowIndex * percent);

        // 得出 unKnowIndex 下标所在 unknownRange 的值
        return unknownRange[0] > unknownRange[1] ? unknownRange[0] - unKnowIndex : unknownRange[0] + unKnowIndex;
    }


    @Test
    public void fun02() {
        int rank1 = 9400;
        int[] rank = new int[]{10000, 9001};
        int[] level = new int[]{1, 2};

        double v = ((rank1 - rank[1]) * 1.0) / (rank[0] - rank[1]);
        double v1 = (level[1] - level[0]) * (1 - v);
        double v2 = level[0] + v1;

        System.out.println(Math.round(v2));
        int i = countRangeMappingRange(rank, rank1, level);
        System.out.println(i);
    }

    @Test
    public void fun03() {
        long time = 10000 * 60 * 60 * 1000;
        System.out.println(time);
    }

    @Test
    public void fun04() {
        int i = -3;
        System.out.println((i & 1) != 0 ? "奇" : "偶");
        System.out.println(i & 1);
    }

    @Test
    public void fun05() {
        short x = 1;
        num(x);
    }


    @Test
    public void fun06() {
        int x = 1;
        int y = 2;
        int z = 3;

        System.out.println(y += z-- / ++x);
    }

    @Test
    public void fun07() {
        int x = 0;
        x = x++;
        System.out.println(x);
    }


    public void num(double x) {
        System.out.println("double");
    }
   /* public void num(short x) {
        System.out.println("short");
    }*/

    public void num(long x) {
        System.out.println("long");
    }

    public void num(int x) {
        System.out.println("int");
    }

    @Test
    public void fun08() {
        long milli = 300000;
        System.out.println();
    }

    @Test
    public void fun09() {
        int minute = (int) Math.ceil(0 * 1.0 / TimeUtil.ONE_MINUTE_IN_MILLISECONDS);
        System.out.println(minute);
    }

    @Test
    public void fun10() {
        double RTTm = 1.5;
        double RTTs = 1.5;
        double RTTD = 1.5 / 2;
        double RTO = 0;
        RTTm = 0.5;
        RTTs = (1 - 0.125) * RTTs + 0.125 * RTTm;
        RTTD = (1 - 0.25) * RTTD + 0.25 * Math.abs(RTTs - RTTm);
        RTO = RTTs + 4 * RTTD;
        System.out.println(RTO);
    }

    @Test
    public void fun11() {
        long a = 2;
        long b = 123456789;
        String scoreStr = a + "." + b;
        double score = Double.valueOf(scoreStr);
        System.out.println(score);

        String scoreStr1 = score + "";
        String[] split = scoreStr1.split("\\.");
        System.out.println(Long.valueOf(split[1]));
    }

    @Test
    public void fun12() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 65, 65, 67);
        while (true) {
            while1(list);
        }
    }

    private void while1(List list) {
        Object s = new Object();
        list.forEach(a -> {
            System.out.println(a);
        });
    }

    @Test
    public void fun13() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x = 0; x < 10; x++) {
            map.put(x, x);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            iterator.remove();
        }
        System.out.println(map);
    }

    @Test
    public void fun14() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x = 0; x < 10; x++) {
            map.put(x, x);
        }
        Collection<Integer> values = map.values();
        Iterator<Integer> iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            Integer next = iterator1.next();
            if (next == 2) {
                iterator1.remove();
            }
        }
        System.out.println(map);
    }

    @Test
    public void fun15() {
        double d = 2.3;
        double s = d + 1334;
        System.out.println(d);
        System.out.println(s);
    }

    @Test
    public void fun16() {
        double a = ((2 * 1460) + 0.0) / (2 * 1538 + 84);
        double b = 10_000_000 * 1.0 / 8;
        System.out.println(a * b);
    }

    @Test
    public void fun17() {
        System.out.println(-1L ^ (-1L << 13));
    }

    @Test
    public void fun18() {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.set(Calendar.YEAR, 2050);
        long l = instance.getTimeInMillis() - System.currentTimeMillis();
        System.out.println(Math.toIntExact(l / 1000));
    }

    @Test
    public void fun19() {
        System.out.println("9691640659".length());
        System.out.println("10000000000000000".length());
        System.out.println((Long.MAX_VALUE + "").length());
    }

    @Test
    public void fun20() {
        long max = -1L ^ (-1L << 51);

        System.out.println(max);

    }

    @Test
    public void fun21() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt());
        }
    }

    /**
     * 浮点数最多只能精确到 52 位
     * 符号位 1   指数位 11 尾数 52
     * <p>
     * 4.2322 = 100.xxx
     * 0.2322 = 0.2322/(1/2) = 0.2322*2
     */

    @Test
    public void fun22() {
        long s = Long.MAX_VALUE;
        double d = s * 0.1 * 1;
        System.out.println(s);
        System.out.println(d);
        System.out.println((long) (s * 0.1));
        System.out.println((long) (s - d));
    }

    @Test
    public void fun23() {
        System.out.println(-1L ^ (-1L << 11));
    }

    @Test
    public void fun24() {
        CloneTest cloneable = new CloneTest();
        System.out.println(cloneable.clone());
    }

    @Test
    public void fun25() {
        System.out.println(150 & 50);
        System.out.println(Integer.toBinaryString(150));
        System.out.println(Integer.toBinaryString(50));
        System.out.println(Integer.toBinaryString(18));
    }


    class CloneTest implements Cloneable {
        @Override
        protected CloneTest clone() {
            CloneTest cloneTest = null;
            try {
                cloneTest = (CloneTest) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return cloneTest;
        }
    }

    @Test
    public void fun26() {
        int s = 0;
        switch (1) {
            case 1:
                System.out.println(1);

            case 2:
                System.out.println(2);

            case 3:
                System.out.println(3);
                break;
        }

    }

    @Test
    public void fun27() {
        System.out.println(Math.cos(20000000 * Math.PI / 180));
    }

    @Test
    public void fun28() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("祝", 1);
        map.put("小", 2);
        map.put("庆", 3);
        System.out.println(map);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        System.out.println(entries);
        Set<Map.Entry<String, Integer>> entries1 = map.entrySet();


    }
}












