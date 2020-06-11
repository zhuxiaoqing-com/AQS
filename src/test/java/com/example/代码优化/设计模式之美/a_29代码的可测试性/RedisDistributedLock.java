package com.example.代码优化.设计模式之美.a_29代码的可测试性;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/11 15:19
 * @Description:
 */
public class RedisDistributedLock {
	private static final RedisDistributedLock INSTANCE = new RedisDistributedLock();

	private RedisDistributedLock() {
	}

	public static RedisDistributedLock getInstance() {
		return INSTANCE;
	}

	public boolean lockTransction(String id) {
		return true;
	}

	public void unlockTransction(String id) {

	}
}
