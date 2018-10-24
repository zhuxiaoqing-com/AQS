package com.example.demo3;

import org.junit.Test;
import redis.clients.jedis.*;

import java.lang.instrument.Instrumentation;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test1Jedis {

    Jedis jedis = new Jedis("127.0.0.1", 6379);

    @Test
    public void fun1(){
        jedis.zadd("zset1", 22,"a");
        jedis.zadd("zset1", 23,"b");
    }

    /**
     * 通过 区间取值
     */
    @Test
    public void fun2(){
        Set<Tuple> zset1 = jedis.zrangeByScoreWithScores("zset1",22, 23);
        System.out.println(zset1);
    }

    /**
     * 通过 value 取 sorted
     */
    @Test
    public void fun3(){
        Double zscore = jedis.zscore("zset1", "a");
        System.out.println(zscore);
    }

    /**
     * 替换  只要将 value 重新存取就会替换新的 分数;
     */
    @Test
    public void fun4(){
        jedis.zadd("zset1", 23,"a");
        System.out.println(jedis.zrangeByScoreWithScores("zset1", 23,23));
    }

    @Test
    public void fun5(){
        double s = 11.11;
        System.out.println((int)s);
    }

    List list = new ArrayList<>();

    {
        for(int i = 0; i <= 10000; i++) {
            list.add(i);
        }
    }
    @Test
    public void fun6() throws InterruptedException {
        new Thread(()-> {
            try {
                Thread.yield();
            } catch (Exception e) {
                e.printStackTrace();
            }
            List list2 = new ArrayList<>();
            list2.iterator();
            for(int i = 0; i <= 10000; i++) {
                list2.add(i+"ss");
            }
            System.out.println("------------------------");
            list = list2;
        }).start();

        System.out.println(list.size());

        for (Object s: list) {
            Thread.yield();
            System.out.println(s.toString());
        }
      /*  for(int i = 0; i < list.size(); i++) {
            Thread.yield();
            System.out.println(list.get(i));
        }*/
    }

    /**
     * 通过 value 取 sorted
     */
    @Test
    public void fun7(){
        Long zscore = jedis.zrank("zset", "dd");
        System.out.println(zscore);
    }
    @Test
    public void fun8(){
        Long zrem = jedis.zrem("zset", "ddddddd");
        System.out.println(zrem);
        Set<Tuple> xx = jedis.zrangeWithScores("xx",1,2);
        Iterator<Tuple> iterator = xx.iterator();
        iterator.hasNext();
        Tuple next = iterator.next();
        next.getScore();
        next.getElement();
    }

    @Test
    public void fun9(){
        int s = 123/100;
        System.out.println(s);
    }

    // arena:ArenaRank:1:1
    @Test
    public void fun10(){
        jedis.zadd("a", 1, "c");
        jedis.zadd("a", 2, "b");
        jedis.zadd("a", 3, "a");
        Set<Tuple> xx = jedis.zrangeWithScores("a",0,3);
        Iterator<Tuple> iterator = xx.iterator();
        while (iterator.hasNext()) {
            Tuple next = iterator.next();
            System.out.println(next.getElement());
            System.out.println(next.getScore());
        }
    }
    @Test
    public void fun11() throws InterruptedException {
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();
        Thread.sleep(4444);
        String key = "haha";
        Instant start = Instant.now();
        Pipeline pipelined = jedis.pipelined();
        for(int i = 0; i < 52220; i++) {
            Response<Long> haha = pipelined.zadd("haha", i, i + key);
            if(i == 995) {
                throw new RuntimeException("11");
            }
        }
        Instant end = Instant.now();
       /* pipelined.sync();
        //pipelined.syncAndReturnAll();
        jedis.close();*/

        System.out.println(end.toEpochMilli() - start.toEpochMilli());
    }

    public void fun12() {}

}