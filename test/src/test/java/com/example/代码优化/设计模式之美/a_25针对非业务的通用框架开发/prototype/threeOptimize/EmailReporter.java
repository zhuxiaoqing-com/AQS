package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.threeOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;

import java.util.*;
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
public class EmailReporter {
	private static final Long DAY_HOURS_IN_SECONDS = 86400L;

	private MetricsStorage metricsStorage;
	private Aggregator aggregator;
	private StatViewer viewer;
	private ScheduledExecutorService executor;

	public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
		this.metricsStorage = metricsStorage;
		this.aggregator = aggregator;
		this.viewer = viewer;
		this.executor = Executors.newSingleThreadScheduledExecutor();
	}

	public void startRepeatedReport() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date firstTime = calendar.getTime();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// 1、得根据给定的时间区间，从数据库中拉取数据
				long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
				long endTimeMillis = System.currentTimeMillis();
				long startTimeMillis = endTimeMillis - durationInMillis;

				Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeMillis, endTimeMillis);
				Map<String, RequestStat> aggregate = aggregator.aggregate(requestInfos, durationInMillis);
				viewer.output(aggregate, startTimeMillis, endTimeMillis);

				// todo将统计数据显示到终端(命令行或邮件)
				// todo 格式化为 html 格式，并且发送邮件

			}
		}, firstTime, DAY_HOURS_IN_SECONDS *1000);
	}

}
