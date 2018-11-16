package com.example.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

public class RedisDelayingQueue<T> {
    static class TaskItem<T> {
        public String id;
        public T msg;
    }

    // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference
    private Type TaskType = new TypeReference(){}.getType();

    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    /**
     * 延迟
     * @param msg 消息
     */
    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<>();
        task.id = UUID.randomUUID().toString();// 分配唯一id
        task.msg = msg;
        String s = JSON.toJSONString(task); // fastjson 序列化
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);// 塞入延时队列， 5s 后再试
    }

    public void loop() {
        while (!Thread.interrupted()) {
            // 只取一条
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if(values.isEmpty()) {
                try {
                    Thread.sleep(500); // 歇下继续
                } catch(InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            if(jedis.zrem(queueKey, s) > 0) { // 抢到了
                TaskItem<T> task = JSON.parseObject(s, TaskType);// fastjson 反序列化
                // 处理逻辑
                this.handleMsg(task.msg);
            }
        }
    }

    public void handleMsg(T msg) {
        System.out.println(msg);
        Thread thread = Thread.currentThread();
    }
}














