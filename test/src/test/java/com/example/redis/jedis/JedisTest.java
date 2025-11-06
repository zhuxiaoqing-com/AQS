package com.example.redis.jedis;

import com.alibaba.fastjson.JSON;
import com.example.demo4.testObj1.ReportLog;
import org.junit.Test;
import redis.clients.jedis.*;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

public class JedisTest {
    static JedisPool jedisPool;
    static  {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(20);
        config.setMinIdle(20);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        // 连接池耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时，会抛出超时异常，默认为true
        config.setBlockWhenExhausted(true);

        // 这里连接超时时间默认走2s
        jedisPool = new JedisPool(config, "127.0.0.1", 6379, 3000, "123456");
    }
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
        int index = 20;
        ArrayList<Jedis> objects = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            Jedis jedis = jedisPool.getResource();
            jedis.select(1);
            jedis.set(key, "1212");
            System.out.println(jedis.get(key));
            objects.add(jedis);
        }
        for (Jedis object : objects) {
            object.close();
        }

        for (int i = 0; i < index; i++) {
            Jedis jedis = jedisPool.getResource();
            System.out.println(jedis.get(key));
        }
	/*	jedis.zadd(, , );
		jedis.zrank(, );
		jedis.zrange(, , );*/
	}

    @Test
    public void TestJedisPool5(){
        Jedis resource = jedisPool.getResource();
       // resource.lpush("aaa", testReport(1));
        //resource.lpush("aaa", testReport(2));
        //String aaa = resource.rpop("aaa");
        System.out.println(resource.brpop(2, "aaa"));
        System.out.println(resource.brpop(2, "aaa"));
        System.out.println(resource.brpop(2, "aaa"));
    }

    String testReport(int i){
        ReportLog reportLog = new ReportLog();
        reportLog.setReportTime(System.currentTimeMillis());
        reportLog.setLobbyId(101);
        reportLog.setDungeonId(999);
        reportLog.setReportUserId("local$mzj" + i);
        reportLog.setReportPlayer(i);
        reportLog.setReportPlayerName("mzddj" + i);
        reportLog.setBeReportedUserId("bili$hnn" + i);
        reportLog.setBeReportedPlayerId(i);
        reportLog.setBeReportedPlayerName("hnddn" + i);
        reportLog.setReason(1);
        reportLog.setDetail("你为什么挂机呢？" + i);
        return JSON.toJSONString(reportLog);
    }

    @Test
    public void TestJedisPool6(){
        Jedis resource = jedisPool.getResource();
        resource.select(1);
        resource.set("aa", "aad");
        System.out.println(resource.get("aa"));
        resource.close();

        int index = 1000;
        for (int i = 0; i < index; i++) {
            Jedis resource1 = jedisPool.getResource();
            System.out.println(resource1.get("aa"));
        }
    }

    @Test
    public void TestJedisPool7(){
        Jedis resource = jedisPool.getResource();
        Set<String> strings = resource.zrangeByScore("112:bt:wild:rank:1107", 0, 10, 0, 1);
        System.out.println(strings);

        strings = resource.zrangeByScore("112:bt:wild:rank:1107", 0, 10, 100, 10);
        System.out.println(strings);

        System.out.println(resource.zlexcount("112:bt:wild:rank:1107", "[15763792716005376","[1112"));
        System.out.println(resource.zlexcount("112:bt:wild:rank:1107", "[2","[1"));
        System.out.println(resource.zcount("112:bt:wild:rank:1107", 0 ,1));
        System.out.println(resource.zcard("112:bt:wild:rank:1107"));
    }

    @Test
    public void TestJedisPool8(){
        Jedis resource = jedisPool.getResource();
        Set<String> strings = resource.zrangeByScore("112:bt:wild:rank:1107", 0, 10, 0, 1);
        System.out.println(strings);
        Double zscore = resource.zscore("112:bt:wild:rank:1107", "11");
        System.out.println(zscore);
    }

    @Test
    public void TestJedisPool9(){
        Jedis resource = jedisPool.getResource();
        List<String> strings = resource.hmget("112:bt:wild:rank:1107", "1","2","3");
        System.out.println(strings);
    }
}
