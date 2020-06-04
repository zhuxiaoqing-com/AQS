package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestStat;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 14:19
 * @Description: 显示器 view;
 */
public class EmailReporter {
	private static final Long DAY_HOURS_IN_SECONDS = 86400L;

	private MetricsStorage metricsStorage;
	private Object emailSender;
	private List<String> toAddresses = new ArrayList<>();

	public EmailReporter(MetricsStorage metricsStorage) {
		this(metricsStorage, new Object());
	}

	public EmailReporter(MetricsStorage metricsStorage, Object emailSender) {
		this.metricsStorage = metricsStorage;
		this.emailSender = emailSender;
	}

	public void addToAddress(String address){
		toAddresses.add(address);
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

				HashMap<String, RequestStat> stats = new HashMap<>();

				requestInfos.forEach((key, value) -> {
					// 根据原始数据，计算得到统计数据
					RequestStat aggregate = Aggregator.aggregate(value, durationInMillis);
					stats.put(key, aggregate);
				});

				// todo将统计数据显示到终端(命令行或邮件)
				// todo 格式化为 html 格式，并且发送邮件

			}
		}, firstTime, DAY_HOURS_IN_SECONDS *1000);
	}
}
