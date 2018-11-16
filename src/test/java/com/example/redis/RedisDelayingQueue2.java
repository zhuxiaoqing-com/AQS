package com.example.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.concurrent.Task;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

public class RedisDelayingQueue2<T> {
    static class TaskItem<T> {
        public String id;
        public T msg;
    }

    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueue2(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    Type type = new TypeReference<TaskItem<T>>(){}.getType();

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<>();
        task.msg = msg;
        // 不清楚为什么要 id 不能直接实例化 msg 嘛
        task.id = UUID.randomUUID().toString();
        String s = JSON.toJSONString(task);
        // 插入 zset 将当前时间作为 score  然后在循环遍历，取出消息消费
        // 插入队列，延迟 5 秒再试
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
    }

    public void loop() {
        while (!Thread.interrupted()) {
            // 只取一条
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if(values.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            // 删除消息, 因为消息只有一条,所以只有一个消息可以删除成功
            if(jedis.zrem(queueKey, s) > 0) {
                TaskItem<T> task = JSON.parseObject(s, type);
                // 消费消息
                handleMsg(task.msg);
            }
        }
    }

    public void handleMsg(T msg) {
        System.out.println(msg);
    }
}














