package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
