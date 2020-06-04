package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
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
 * @Date: 2020/6/4 19:29
 * @Description: 组装类并定时触发执行统计显示。
 * 在将核心逻辑剥离出来之后，这个类的代码变得更加简洁、清晰，
 * 只负责组装各个类 来完成整个工作流程
 */
public class ConsoleReporter {
	private MetricsStorage metricsStorage;
	private Aggregator aggregator;
	private StatViewer viewer;
	private ScheduledExecutorService executor;

	public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
		this.metricsStorage = metricsStorage;
		this.aggregator = aggregator;
		this.viewer = viewer;
		this.executor = Executors.newSingleThreadScheduledExecutor();
	}

	public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
		executor.scheduleAtFixedRate(() -> {
			// 1、得根据给定的时间区间，从数据库中拉取数据
			long durationInMillis = durationInSeconds * 1000;
			long endTimeMillis = System.currentTimeMillis();
			long startTimeMillis = endTimeMillis - durationInMillis;

			Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeMillis, endTimeMillis);
			Map<String, RequestStat> aggregate = aggregator.aggregate(requestInfos, durationInMillis);
			viewer.output(aggregate, startTimeMillis, endTimeMillis);

		}, 0L, periodInSeconds, TimeUnit.SECONDS);
	}

}
