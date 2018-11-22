package com.example.redis.simplelimitflow;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * 简单限制流量
 * 用一个 zset 结构记录用户的行为历史，每一个行为都会作为 zset 中的一个 Key 保存下来。
 * 同一个用户同一种行为用一个  zset 记录
 * <p>
 * 为了节省内存，我们只需要保留时间窗口内的行为记录，同时如果用户是冷用户，滑动时间窗口内的
 * 行为是空记录，那么这个 zset 就可以用内存中移除，不再占用空间;
 */
public class SimpleRateLimiter {
    private Jedis jedis = new Jedis();

   /* public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }
*/

    /**
     * @param userId    用户Id
     * @param actionKey 活动 Key
     * @param period    时间段
     * @param maxCount  最大数
     * @return
     */
    public boolean isActionAllowed01(String userId, String actionKey, int period, int maxCount) {
        // 准备 key
        String connector = ":";
        String hits = "hits";
        String key = String.join(connector, hits, userId, actionKey);

        /*
        1、判断长度超过不添加
        2、进行添加
        3、删除过期键
        4、设置过期时间
        5、
         */
        long nowTs = System.currentTimeMillis();
        // 在管道之前先获取次数判断, 因为 pipeline 是要等 sync 以后才能获取值
        try {
            Long count = jedis.zcount(key, nowTs - period * 1000, nowTs);
            // 如果超过次数了就直接返回
            if (count >= maxCount) {
                return false;
            }
            Pipeline pipe = jedis.pipelined();
            pipe.multi();
            pipe.zadd(key, nowTs, "" + nowTs);
            pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
            // 过期时间设置多一秒
            pipe.expire(key, period + 1);
            // 提交事物
            pipe.exec();
            pipe.sync();
            return true;
        } finally {
            jedis.close();
        }
    }

    /**
     * @param userId    用户Id
     * @param actionKey 活动 Key
     * @param period    时间 分钟
     * @param maxCount  最大数
     * @return
     */
    public boolean isActionAllowed02(String userId, String actionKey, int period, int maxCount) {
        String delimiter = ":";
        String prefix = "hits";
        String key = String.join(delimiter, prefix, userId, actionKey);
        long nowTimes = System.currentTimeMillis();
        try {
            Long count = jedis.zcount(key, nowTimes - period * 1000, nowTimes);
            if (count >= maxCount) {
                return false;
            }
            Pipeline pipe = jedis.pipelined();
            pipe.multi();
            // 值需要填因为要保证 value 唯一 因为一次用户点击间隔应该都是超过一毫秒的
            pipe.zadd(key, nowTimes, "" + nowTimes);
            // 删除过期键
            pipe.zremrangeByScore(key, 0, nowTimes - period * 1000);
            // 设置过期时间 多一秒
            pipe.expire(key, period + 1);
            /*Response<Long> zcount = pipe.zcount(key, nowTimes - period * 1000, nowTimes);
            // 事务要在前面 因为事物没有提交其实并没有真正运行语句
            // 如果你没有 使用到管道的返回值没事，但是你使用到了就会报错;因为并 redis 的返回值还没有返回
            System.out.println(zcount.get());
            pipe.exec();
            System.out.println(zcount.get());
            pipe.sync();
            System.out.println(zcount.get());*/
            pipe.sync();
            pipe.exec();
            return true;
        } finally {
            jedis.close();
        }
    }

    @Test
    public void test01() {
        isActionAllowed02("dd", "aa", 2, 4);
    }
}
