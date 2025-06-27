package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.TDD;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.Metrics;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/21 20:53
 * @Description:
 */
public class UserController {
	private Metrics metrics = new Metrics();

	public UserController() {
		this.metrics.startRepeatedReport(60, TimeUnit.SECONDS);
	}

	public void register(Object object) {
		//...
		long startTimestamp = System.currentTimeMillis();
		metrics.recordTimestamp("register", startTimestamp);
	}

	public Object login(String telephone, String password) {
		//...
		return null;
	}
}
