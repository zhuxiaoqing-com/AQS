package com.example.demo3;

import org.junit.Test;
import redis.clients.jedis.*;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Set;

public class JedisTest {

    @Test
    public void TestJedisPool(){
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();
        String key = "haha";
        Instant start = Instant.now();
        Transaction transaction=jedis.multi();//返回一个事务控制对象
        for(int i = 0; i < 500; i++) {
            Response<Long> haha = transaction.zadd("haha", i, i + key);
        }
        System.out.println("dd");
        Instant end = Instant.now();
        transaction.exec();
        jedis.close();

        System.out.println(end.toEpochMilli() - start.toEpochMilli());
    }

    @Test
    public void TestJedisPool2() throws IOException {
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        String key = "haha";
        Instant start = Instant.now();
        Pipeline pipelined = jedis.pipelined();
        for(int i = 0; i < 50; i++) {
            Response<Long> haha = pipelined.zadd("haha", i, i + key);
            System.out.println(haha);
            if(i == 20000) {
                pipelined.sync();
            }
        }
        Instant end = Instant.now();
        pipelined.sync();
        pipelined.close();
        //pipelined.syncAndReturnAll();
        jedis.close();

        System.out.println(end.toEpochMilli() - start.toEpochMilli());
    }

    @Test
    public void TestJedisPool3(){
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        String key = "haha";
        Instant start = Instant.now();
        //Pipeline pipelined = jedis.pipelined();
        HashMap<String, Double> objectObjectHashMap = new HashMap<>();
        for(int i = 0; i < 500000; i++) {
            objectObjectHashMap.put(key+i, i+0.0);
        }
       // jedis.hmset();
        //jedis.mset();

        jedis.zadd(key, objectObjectHashMap);
        Instant end = Instant.now();
        //pipelined.syncAndReturnAll();
        jedis.close();

        System.out.println(end.toEpochMilli() - start.toEpochMilli());
    }

    @Test
    public void TestJedisPool4(){
        String key = "haha";
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();
        Set<String> strings = jedis.zrangeByScore(key, 0, 20);
        System.out.println(strings);
    }
}
