package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.sixOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize.Aggregator;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize.StatViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/5 11:32
 * @Description:
 */
public class ScheduledReporter {
	private static final long MAX_STAT_DURATION_IN_MILLIS = 10 * 60 * 1000; // 10mi
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

	protected Map<String, RequestStat> doStat(long startTimeInMills, long endTimeInMills) {
		Map<String, List<RequestStat>> segmentStats = new HashMap<>();
		long segmentStartTimeMills = startTimeInMills;
		while (segmentStartTimeMills < endTimeInMills) {
			long segmentEndTimeMills = segmentStartTimeMills + MAX_STAT_DURATION_IN_MILLIS;
			if (segmentEndTimeMills > endTimeInMills) {
				segmentEndTimeMills = endTimeInMills;
			}
			Map<String, List<RequestInfo>> requestInfos = storage.getRequestInfos(segmentStartTimeMills, segmentEndTimeMills);
			if (requestInfos == null || requestInfos.isEmpty()) {
				continue;
			}

			Map<String, RequestStat> segmentStat = aggregator.aggregate(requestInfos, segmentEndTimeMills - segmentStartTimeMills);
			addStat(segmentStats, segmentStat);
			segmentStartTimeMills += MAX_STAT_DURATION_IN_MILLIS;
		}
		long durationInMillis = endTimeInMills - startTimeInMills;
		Map<String, RequestStat> aggregatedStats = aggregatedStates(segmentStats, durationInMillis);
		return aggregatedStats;
	}


	private void addStat(Map<String, List<RequestStat>> segmentStats, Map<String, RequestStat> segmentStat) {
		segmentStat.forEach((key, value) -> segmentStats.computeIfAbsent(key, k -> new ArrayList<>()).add(value));
	}


	private Map<String, RequestStat> aggregatedStates(Map<String, List<RequestStat>> segmentStats, long durationInMillis) {
		HashMap<String, List<RequestStat>> aggregatedStats = new HashMap<>();
		for (Map.Entry<String, List<RequestStat>> entry : segmentStats.entrySet()) {
			String apiName = entry.getKey();
			List<RequestStat> apiStats = entry.getValue();
			double maxRespTime = Double.MIN_VALUE;
			double minRespTime = Double.MAX_VALUE;
			long count = 0;
			double sumRespTime = 0;
			for(RequestStat stat : apiStats) {
				//if(stat.getMaxResponseTime())
				// todo 数据合并就不实现了
			}
		}
		return null;
	}
}








