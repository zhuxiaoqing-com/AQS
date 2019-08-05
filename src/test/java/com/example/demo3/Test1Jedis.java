package com.example.demo3;

import org.junit.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.lang.instrument.Instrumentation;
import java.time.Instant;
import java.util.*;

public class Test1Jedis {

    Jedis jedis = new Jedis("127.0.0.1", 6379);
    //Jedis jedis = new Jedis("192.168.5.83", 6379);

    @Test
    public void fun1() {
        jedis.zadd("zset1", 22, "a");
        jedis.zadd("zset1", 23, "b");
    }

    /**
     * 通过 区间取值
     */
    @Test
    public void fun2() {
        Set<Tuple> zset1 = jedis.zrangeByScoreWithScores("zset1", 22, 23);
        System.out.println(zset1);
    }

    /**
     * 通过 value 取 sorted
     */
    @Test
    public void fun3() {
        Double zscore = jedis.zscore("zset1", "a");
        System.out.println(zscore);
    }

    /**
     * 替换  只要将 value 重新存取就会替换新的 分数;
     */
    @Test
    public void fun4() {
        jedis.zadd("zset1", 23, "a");
        System.out.println(jedis.zrangeByScoreWithScores("zset1", 23, 23));
    }

    @Test
    public void fun5() {
        double s = 11.11;
        System.out.println((int) s);
    }

    List list = new ArrayList<>();

    {
        for (int i = 0; i <= 10000; i++) {
            list.add(i);
        }
    }

    @Test
    public void fun6() throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.yield();
            } catch (Exception e) {
                e.printStackTrace();
            }
            List list2 = new ArrayList<>();
            list2.iterator();
            for (int i = 0; i <= 10000; i++) {
                list2.add(i + "ss");
            }
            System.out.println("------------------------");
            list = list2;
        }).start();

        System.out.println(list.size());

        for (Object s : list) {
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
    public void fun7() {
        Long zscore = jedis.zrank("zset", "dd");
        System.out.println(zscore);
    }

    @Test
    public void fun8() {
        Long zrem = jedis.zrem("zset", "ddddddd");
        System.out.println(zrem);
        Set<Tuple> xx = jedis.zrangeWithScores("xx", 1, 2);
        Iterator<Tuple> iterator = xx.iterator();
        iterator.hasNext();
        Tuple next = iterator.next();
        next.getScore();
        next.getElement();
    }

    @Test
    public void fun9() {
        int s = 123 / 100;
        System.out.println(s);
    }

    // arena:ArenaRank:1:1
    @Test
    public void fun10() {
        jedis.zadd("a", 1, "c");
        jedis.zadd("a", 2, "b");
        jedis.zadd("a", 3, "a");
        Set<Tuple> xx = jedis.zrangeWithScores("a", 0, 3);
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
        for (int i = 0; i < 52220; i++) {
            Response<Long> haha = pipelined.zadd("haha", i, i + key);
            if (i == 995) {
                throw new RuntimeException("11");
            }
        }
        Instant end = Instant.now();
       /* pipelined.sync();
        //pipelined.syncAndReturnAll();
        jedis.close();*/

        System.out.println(end.toEpochMilli() - start.toEpochMilli());
    }

    @Test
    public void fun12() {
        JedisPool jedisPool = new JedisPool("192.168.5.83", 6379);
        Jedis jedis = jedisPool.getResource();
        String index = "0";
        ScanParams scanParams = new ScanParams().count(1000);
        List<Map.Entry<String, String>> result = null;
        while (true) {
            ScanResult<Map.Entry<String, String>> hscan = jedis.hscan("arena_robot:4", index, scanParams);
            result = hscan.getResult();
            index = hscan.getStringCursor();
            System.out.println(result.size());
            if("0".equals(index)) {
                return;
            }
        }
    }

    @Test
    public void fun13() {
        jedis.del("a");
        jedis.zadd("a",  1.001, "11");
        jedis.zadd("a",  23.232321, "22");
        jedis.zadd("a",  23.232322, "23");
        jedis.zadd("a",  23.232324, "24");

        Set<Tuple> xx = jedis.zrangeWithScores("a", 0, 3);
        Iterator<Tuple> iterator = xx.iterator();
        while (iterator.hasNext()) {
            Tuple next = iterator.next();
            System.out.println(next.getElement());
            System.out.println(next.getScore());
        }
       // System.out.println(jedis.zcount("a", 3,-1));
        //jedis.zremrangeByRank("a", 3, 3);

    }


    @Test
    public void fun14() {
        Double zscore = jedis.zscore("arena:1:1:1", "3");
        GeoRadiusParam param = GeoRadiusParam.geoRadiusParam().sortAscending().count(1).withCoord().withDist();
        GeoCoordinate geoCoordinate = new GeoCoordinate(22, 22);
       // jedis2.geoadd("location",geoCoordinate.getLongitude(),geoCoordinate.getLatitude(),"b");
       //List<GeoRadiusResponse> geoRadiusResponses = jedis.georadiusByMember("location", "32ds", 1, GeoUnit.KM, param);
        jedis.close();
    }
}
