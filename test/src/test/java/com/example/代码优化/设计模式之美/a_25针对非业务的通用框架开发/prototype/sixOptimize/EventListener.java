package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.sixOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.google.common.eventbus.Subscribe;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/5 14:21
 * @Description:
 */
public class EventListener {
	private MetricsStorage metricsStorage;

	@Subscribe
	public void saveRequestInfo(RequestInfo requestInfo) {
		metricsStorage.saveRequestInfo(requestInfo);
	}
}
