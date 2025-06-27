package com.example.redis.jedis.funnellimitflow;

import com.example.demo4.TimeUtil;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/4 19:59
 * @Description: 令牌漏斗
 * 令牌桶算法(Token Bucket)和 Leaky Bucket 效果一样但方向相反的算法,更加容易理解.
 * 随着时间流逝,系统会按恒定1/QPS时间间隔(如果QPS=100,则间隔是10ms)往桶里加入Token(想象和漏洞漏水相反,有个水龙头在不断的加水),
 * 如果桶已经满了就不再加了.新请求来临时,会各自拿走一个Token,如果没有Token可拿了就阻塞或者拒绝服务.
 */
public class TokenLimiter2 {
	private final int capacity; // n毫秒能装多少令牌
	private final int capacityMill; // n毫秒能装多少令牌
	private double curTokenNum; // 现在桶内令牌数量(用double存)
	private long lastTime; // 上一次取令牌的时间戳

	public TokenLimiter2(int qbs, int mill) {
		capacity = qbs;
		this.capacityMill = mill;
		curTokenNum = 0;
		lastTime = 0;
	}

	public synchronized boolean tryAcquire() {
		long now = System.nanoTime();
		long intoToken = (now - lastTime) / TimeUtil.millsNano * capacity;
		lastTime = now;
		curTokenNum = Math.min(intoToken, capacity);
		// 没有令牌
		if (curTokenNum < 1) {
			return false;
		}
		// 还有令牌
		curTokenNum--;
		return false;

	}
}
