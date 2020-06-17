package com.example.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;
import java.util.concurrent.*;

public class Test3 {
    JedisPool jedisPool = new JedisPool();
    Jedis jedis = null;//jedisPool.getResource();
    @Test
    public void fun1() {
        String join = String.join(":", "a", "b", "c", "d", "e");
        System.out.println(join);
    }

    @Test
    public void fun2() {
        //JedisPool jedisPool = new JedisPool();
        //Jedis jedis = jedisPool.getResource();
        Jedis jedis = new Jedis();
        Double zscore = jedis.zscore("zset", "w");
        System.out.println(zscore);
        System.out.println(jedis.ping());
        jedis.close();
        System.out.println(jedis.ping());
        Double zscore2 = jedis.zscore("zset", "w");
        System.out.println(zscore2);
    }

    @Test
    public void fun3() {
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();

        Double zscore = jedis.zscore("zset", "w");
        System.out.println(zscore);
    }

    @Test
    public void fun4() {
        JedisPool jedisPool = new JedisPool();
        for(int i = 0; i < 10; i++) {
            Jedis jedis = jedisPool.getResource();
            //jedis.close();
        }

    } @Test
    public void fun5() {
        JedisPool jedisPool = new JedisPool();
        for(int i = 0; i < 9; i++) {
            try(Jedis jedis = jedisPool.getResource()) {

            }//jedis.close();
        }
    }

    @Test
    public void fun6() {
        jedis.zadd("zset",2,"w");
    }

    @Test
    public void fun7() {
        Set<String> zet = jedis.zrangeByScore("z1et", 1, 2);
        System.out.println(zet);
    }

    @Test
    public void fun8() {
        String hget = jedis.hget("a", "a");
        System.out.println(hget);
    }

    @Test
    public void fun9() {
        ConcurrentLinkedDeque<Object> objects = new ConcurrentLinkedDeque<>();
        ConcurrentLinkedQueue<Object> objects1 = new ConcurrentLinkedQueue<>();

        Spliterator<Object> spliterator = objects1.spliterator();
        Object peek = objects1.poll();
        // 一开始得是30的, 也就是说一开始填充后面,就入队,出队
        objects1.add("a");
        Object poll = objects1.poll();
        while (poll == null) {
            objects1.poll();
        }
    }

    @Test
    public void fun10() {
        ArrayList<ArrayList<String>> list = new ArrayList<>(30);
        ArrayList<String>[] s = new ArrayList[30];
        Arrays.fill(s, new ArrayList());
        System.out.println(s.length);
        List<ArrayList<String>> arrayLists = Arrays.asList(s);
        System.out.println(arrayLists.size());
        System.out.println(arrayLists);
    }

    @Test
    public void fun11() {
        ArrayList<ArrayList<String>> list = new ArrayList<>(30);
        ArrayList<String>[] tempList = new ArrayList[30];
        Arrays.fill(tempList,  new ArrayList());
        List<ArrayList<String>> arrayLists = Arrays.asList(tempList);
        ConcurrentLinkedQueue arenaServerDareLogMap = new ConcurrentLinkedQueue<>(arrayLists);
        Object[] objects = arenaServerDareLogMap.toArray();
        System.out.println(objects.length);
    }

    @Test
    public void fun12() {
        ArrayList<ArrayList<String>> list = new ArrayList<>(30);
        ArrayList<String>[] tempList = new ArrayList[30];
        Arrays.fill(tempList,  new ArrayList());
        List<ArrayList<String>> arrayLists = Arrays.asList(tempList);

        Queue<List<String>> arenaServerDareLogMap = new LinkedList<>();

        Object[] objects = arenaServerDareLogMap.toArray();
        System.out.println(objects.length);
    }

    /**
     * 测试 boolean 变 String 是什么
     */
    @Test
    public void fun13() {
       boolean s = false;
        System.out.println(String.valueOf(false));
    }

    /**
     * 测试 数组 可以并发安全嘛(多个线程同时插入数据)
     */
    @Test
    public void fun14() throws InterruptedException {
        int index = 10;
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Integer[] strings = new Integer[2];
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0; i < index; i++) {
            executorService.execute(()-> {
                strings[1] = ThreadLocalRandom.current().nextInt(2222);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(strings[1]);

    }
    @Test
    public void fun15() throws InterruptedException {
        String s = ".2222231";
        System.out.println(Double.parseDouble(s));
    }
}
