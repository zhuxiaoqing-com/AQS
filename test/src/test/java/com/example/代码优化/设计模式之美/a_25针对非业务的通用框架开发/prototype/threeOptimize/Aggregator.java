package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 14:18
 * @Description: 计算器
 */
public class Aggregator {
	public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMills) {
		Map<String, RequestStat> requestStats = new HashMap<>();
		requestInfos.forEach((key, value) -> {
			requestStats.put(key, doAggregate(value, durationInMills));
		});
		return requestStats;
	}

	private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMills) {
		List<Double> respTimes = requestInfos.stream().map(RequestInfo::getResponseTime).collect(Collectors.toList());

		RequestStat requestStat = new RequestStat();
		requestStat.setMaxResponseTime(max(respTimes));
		requestStat.setMinResponseTime(min(respTimes));
		requestStat.setAvgResponseTime(avg(respTimes));
		requestStat.setP999ResponseTime(percentile999(respTimes));
		requestStat.setP99ResponseTime(percentile99(respTimes));
		requestStat.setCount(respTimes.size());
		requestStat.setTps((long) tps(respTimes.size(), durationInMills / 1000));
		return requestStat;
	}

	private double max(List<Double> dataset) {
		return 0;
	}

	private double min(List<Double> dataset) {
		return 0;
	}

	private double avg(List<Double> dataset) {
		return 0;
	}

	private double tps(int count, double duration) {
		return 0;
	}

	private double percentile999(List<Double> dataset) {
		return 0;
	}

	private double percentile99(List<Double> dataset) {
		return 0;
	}

	private double percentile(List<Double> dataset) {
		return 0;
	}
}
