package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/21 20:55
 * @Description: 指标
 */
public class Metrics {

	/**
	 * 要输出接口的响应时间的最大值、平均值和接口调用次数，我们首先要采集每次接口请求的响应时间，
	 * 并存储起来，然后按照某个时间间隔做聚合统计，最后才是将结果输出。
	 */


	// Map 的 Key 是接口名称， value 对应接口请求的响应时间或时间戳;
	private Map<String, List<Double>> responseTimes = new HashMap<>();

	private Map<String, List<Double>> timestamps = new HashMap<>();

	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	public void recordResponseTime(String apiName, double responseTime) {
		responseTimes.computeIfAbsent(apiName, a -> new ArrayList<>()).add(responseTime);
	}

	public void recordTimestamp(String apiName, double timestamp) {
		responseTimes.computeIfAbsent(apiName, a -> new ArrayList<>()).add(timestamp);
	}

	public void startRepeatedReport(long period, TimeUnit unit) {
		executor.scheduleWithFixedDelay(() -> {
			Gson gson = new Gson();
			Map<String, Map<String, Double>> stats = new HashMap<>();
			for (Map.Entry<String, List<Double>> entry: responseTimes.entrySet()){
				String apiName = entry.getKey();
				List<Double> apiRespTimes = entry.getValue();
				stats.putIfAbsent(apiName, new HashMap<>());
				stats.get(apiName).put("max", max(apiRespTimes));
				stats.get(apiName).put("avg", avg(apiRespTimes));
			}
			for (Map.Entry<String, List<Double>> entry: timestamps.entrySet()){
				String apiName = entry.getKey();
				List<Double> apiTimestamps = entry.getValue();
				stats.putIfAbsent(apiName, new HashMap<>());
				stats.get(apiName).put("count", (double)apiTimestamps.size());
			}
			System.out.println(gson.toJson(stats));
		}, 0, period, unit);
	}


	private Double avg(List<Double> apiRespTimes) {
		return -1d;
	}

	private Double max(List<Double> apiRespTimes) {
		return -1d;
	}

}








