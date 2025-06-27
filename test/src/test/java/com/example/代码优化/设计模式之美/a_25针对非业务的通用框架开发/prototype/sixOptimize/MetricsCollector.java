package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.sixOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.platform.commons.util.StringUtils;

import java.util.concurrent.Executors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/5 14:19
 * @Description:
 */
public class MetricsCollector {
	private static final int  DEFAULT_STORAGE_POOL_SIZE = 20;

	private MetricsStorage metricsStorage;
	private EventBus eventBus;

	public MetricsCollector(MetricsStorage storage) {
		this(storage, DEFAULT_STORAGE_POOL_SIZE);
	}

	public MetricsCollector(MetricsStorage storage, int threadNumToSaveData) {
		this.metricsStorage = storage;
		this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(threadNumToSaveData));
		this.eventBus.register(new EventListener());
	}

	public void recordRequest(RequestInfo requestInfo) {
		if(requestInfo == null ||StringUtils.isBlank(requestInfo.getApiName())) {
			return;
		}
		eventBus.post(requestInfo);
	}
}
