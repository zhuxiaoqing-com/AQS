package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.fiveOptimize;


import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.RedisMetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize.Aggregator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/5 10:47
 * @Description:
 */
public class PerfCounterTest {
	public static void main(String[] args) {
		MetricsStorage storage = new RedisMetricsStorage();
		Aggregator aggregator = new Aggregator();

		ConsoleReporter consoleReporter = new ConsoleReporter();
		consoleReporter.startRepeatedReport(60, 60);

		List<String> email = new ArrayList<>();


		MetricsCollector collector = new MetricsCollector();
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("login", 123, 10234));
		collector.recordRequest(new RequestInfo("login", 123, 10234));

	}
}
