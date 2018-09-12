package com.example.demo3;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.stream.IntStream;

public class Test2List {
    private Random random = new Random();;

    List<Long> list;
    {
        list = new ArrayList<>();
        for(long i = 0L; i < 10L; i++) {
            list.add(i);
        }
    }

    /**
     * subList start 包含
     *  end 不包含
     *  所有的删除基于 subList 最终会映射到 list 里面
     */
    @Test
    public void fun1() {
        List<Long> longs = list.subList(0, 9);
        System.out.println(longs);
        longs.remove(0);
        System.out.println(longs);
        System.out.println(list);
    }

    @Test
    public void fun2() {
        Long[] temps = new Long[9999];
        Arrays.fill(temps,-1L);
        List<Long> list = Arrays.asList(temps);
        List<Long> longs = list.subList(2, 44);
        System.out.println(longs);
        System.out.println(list);
    }

    /**
     * 不会报错
     */
    @Test
    public void fun3() {
        Long[] temps = new Long[9999];
        Arrays.fill(temps,-1L);
        List<Long> list = Arrays.asList(temps);
        ArrayList<Long> longs = new ArrayList<>(list);
        longs.remove(2);
    }

    /**
     * 不会报错
     */
    @Test
    public void fun4() {
        int min = 2;
        int max = 22;

        while(true) {
        int i = random.nextInt(1);
        System.out.println(i);

        }
    }
    private static final Map<Integer, Integer> randomMap = new LinkedHashMap();
    {
        /**
         * 大于 5000
         * 最左的玩家 根据玩家自身排名往前取 500 内随机，显示在最左的位置
         * 中间的玩家 根据玩家自身排名往前取 1000 内随机，显示在中间的位置
         * 最右的玩家 根据玩家自身排名往前取 2000 内随机，显示在最右的位置
         */
        randomMap.put(5000, 11);
        randomMap.put(1000, 22);
        randomMap.put(500, 33);
        randomMap.put(100, 44);
        randomMap.put(50, 55);
        randomMap.put(10, 66);
    }
    @Test
    public void fun5() {
        while (true) {
            for ( int rankRange  : randomMap.keySet()) {
                System.out.println(rankRange);
            }
            System.out.println("---------------------------------------------");

        }
    }

    /**
     * list 为 null 还是有值的就是 [null]
     */
    @Test
    public void fun6() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(null);
        System.out.println(list);
    }

    @Test
    public void fun7() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(null);
        System.out.println(list);
    }

}
