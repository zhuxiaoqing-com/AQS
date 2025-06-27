package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.fourOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize.Aggregator;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize.StatViewer;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/5 11:32
 * @Description:
 */
public class ScheduledReporter {
	protected MetricsStorage storage;
	protected Aggregator aggregator;
	protected StatViewer viewer;

	public ScheduledReporter(MetricsStorage storage, Aggregator aggregator, StatViewer viewer) {
		this.storage = storage;
		this.aggregator = aggregator;
		this.viewer = viewer;
	}

	protected void doStatAndReport(long startTimeMillis, long endTimeMillis) {
		long durationInMillis = endTimeMillis - startTimeMillis;
		Map<String, List<RequestInfo>> requestInfos = storage.getRequestInfos(startTimeMillis, endTimeMillis);
		Map<String, RequestStat> aggregate = aggregator.aggregate(requestInfos, durationInMillis);
		viewer.output(aggregate, startTimeMillis, endTimeMillis);
	}
}
