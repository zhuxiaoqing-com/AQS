package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 14:18
 * @Description: 计算器
 */
public class Aggregator {
	public static RequestStat aggregate(List<RequestInfo> requestInfos, long duration) {
		double maxResponseTime = Double.MIN_VALUE;
		double minResponseTime = Double.MAX_VALUE;
		double avgResponseTime = -1;
		double p999ResponseTime = -1;
		double p99ResponseTime = -1;
		double sumRespTime = 0;
		long count = 0;

		for (RequestInfo requestInfo : requestInfos) {
			++count;
			double respTime = requestInfo.getResponseTime();
			if (maxResponseTime < respTime) {
				maxResponseTime = respTime;
			}
			if (minResponseTime > respTime) {
				minResponseTime = respTime;
			}
			sumRespTime += respTime;
		}
		if (count != 0) {
			avgResponseTime = sumRespTime / count;
		}
		long tps = (long) (count / duration * 1000);

		requestInfos.sort((RequestInfo o1, RequestInfo o2) -> {
			double diff = o1.getResponseTime() - o2.getResponseTime();
			if (diff < 0.0) {
				return -1;
			}
			if (diff > 0.0) {
				return 1;
			}
			return 0;
		});

		int idx999 = (int) (count * 0.999);
		int idx99 = (int) (count * 0.99);
		if (count != 0) {
			p999ResponseTime = requestInfos.get(idx999).getResponseTime();
			p99ResponseTime = requestInfos.get(idx99).getResponseTime();
		}
		RequestStat requestStat = new RequestStat();
		requestStat.setMaxResponseTime(maxResponseTime);
		requestStat.setMinResponseTime(minResponseTime);
		requestStat.setAvgResponseTime(avgResponseTime);
		requestStat.setP999ResponseTime(p999ResponseTime);
		requestStat.setP99ResponseTime(p99ResponseTime);
		requestStat.setCount(count);
		requestStat.setTps(tps);
		return requestStat;
	}
}
