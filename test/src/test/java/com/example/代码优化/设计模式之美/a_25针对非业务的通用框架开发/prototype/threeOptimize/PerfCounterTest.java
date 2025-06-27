package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsCollector;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.RedisMetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;

import javax.validation.constraints.Email;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/5 10:47
 * @Description:
 */
public class PerfCounterTest {
	public static void main(String[] args) {
		MetricsStorage storage = new RedisMetricsStorage();
		Aggregator aggregator = new Aggregator();

		// 定时触发统计并将结果显示到终端
		StatViewer viewer = new ConsoleViewer();
		ConsoleReporter reporter = new ConsoleReporter(storage, aggregator, viewer);
		reporter.startRepeatedReport(60, 60);

		// 定时触发统计并将结果输出到邮件
		EmailViewer emailViewer = new EmailViewer();
		emailViewer.addToAddress("zhuxiaoqing@qq.com");
		EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
		emailReporter.startRepeatedReport();

		MetricsCollector collector = new MetricsCollector(storage);
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("register", 123, 10234));
		collector.recordRequest(new RequestInfo("login", 123, 10234));
		collector.recordRequest(new RequestInfo("login", 123, 10234));

	}
}
