package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 15:55
 * @Description:
 */
public class Demo {

	public static void main(String[] args) {
		RedisMetricsStorage storage = new RedisMetricsStorage();
		ConsoleReporter consoleReporter = new ConsoleReporter(storage);
		consoleReporter.startRepeatedReport(60, 60);

		EmailReporter emailReporter = new EmailReporter(storage);
		emailReporter.addToAddress("zhuxiaiqong@qq.com");
		emailReporter.startRepeatedReport();

		MetricsCollector collector = new MetricsCollector(storage);
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("login", 123, 10234));
		collector.recordRequest(new RequestInfo("login", 123, 10234));

	}
}
