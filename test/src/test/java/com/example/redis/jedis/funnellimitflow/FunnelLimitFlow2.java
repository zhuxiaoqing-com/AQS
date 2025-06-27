package com.example.redis.jedis.funnellimitflow;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 漏斗限流
 * 漏桶(Leaky Bucket)算法思路很简单,水(请求)先进入到漏桶里,
 * 漏桶以一定的速度出水(接口有响应速率),当水流入速度过大会直接溢出(访问频率超过接口响应速率),
 * 然后就拒绝请求,可以看出漏桶算法能强行限制数据的传输速率.示意图如下:
 */
public class FunnelLimitFlow2 {
	private final long capacity;                                            // 水桶容量, 一秒流光
	private double remainWater;                                             // 目前水桶剩下的水量
	private long lastTime;                                                  // 时间戳

	FunnelLimitFlow2(int qps) {
		capacity = qps;
		remainWater = capacity;
		lastTime = 0;
	}

	public synchronized boolean tryAcquire() {
		long now = System.currentTimeMillis();
		double outWater = ((now - lastTime) / 1000.0) * capacity;         // 计算这段时间匀速流出的水
		lastTime = now;
		// 计算流出了多少水
		remainWater = Math.max(0, remainWater - outWater);
		// 如果漏斗还没满 就通过
		if (remainWater < capacity) {
			remainWater++;
			return true;
		} else return false;

	}
}
