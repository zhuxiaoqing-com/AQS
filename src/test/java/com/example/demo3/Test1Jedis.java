package com.example.demo3;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

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


}
