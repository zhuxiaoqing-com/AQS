package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.fiveOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.MetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.RedisMetricsStorage;
import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;
import org.junit.platform.commons.util.StringUtils;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 14:15
 * @Description: 负责数据的收集
 */
public class MetricsCollector {
	private MetricsStorage metricsStorage; // 基于接口而非实现编程

	// 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
	public MetricsCollector() {
		this(new RedisMetricsStorage());
	}

	// 兼顾灵活性和代码的可测试性，这个构造函数继续保留
	public MetricsCollector(MetricsStorage metricsStorage) {
		this.metricsStorage = metricsStorage;
	}

	// 容一个函数代替了最小原型中的两个函数
	public void recordRequest(RequestInfo requestInfo){
		if(requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
			return;
		}
		metricsStorage.saveRequestInfo(requestInfo);
	}
}
