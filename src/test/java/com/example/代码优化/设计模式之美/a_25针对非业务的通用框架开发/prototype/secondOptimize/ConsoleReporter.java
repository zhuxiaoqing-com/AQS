package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 14:19
 * @Description: 显示器 view;
 */
public class ConsoleReporter {
	private MetricsStorage metricsStorage;
	private ScheduledExecutorService executor;

	public ConsoleReporter(MetricsStorage metricsStorage) {
		this.metricsStorage = metricsStorage;
		this.executor = Executors.newSingleThreadScheduledExecutor();
	}

	public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
		executor.scheduleAtFixedRate(() -> {
			// 1、得根据给定的时间区间，从数据库中拉取数据
			long durationInMillis = durationInSeconds * 1000;
			long endTimeMillis = System.currentTimeMillis();
			long startTimeMillis = endTimeMillis - durationInMillis;
			Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeMillis, endTimeMillis);

			HashMap<String, RequestStat> stats = new HashMap<>();

			requestInfos.forEach((key, value) -> {
				// 根据原始数据，计算得到统计数据
				RequestStat aggregate = Aggregator.aggregate(value, durationInMillis);
				stats.put(key, aggregate);
			});

			// todo将统计数据显示到终端(命令行或邮件)
			// todo 格式化为 html 格式，并且发送邮件

		}, 0, periodInSeconds, TimeUnit.SECONDS);
	}
}
