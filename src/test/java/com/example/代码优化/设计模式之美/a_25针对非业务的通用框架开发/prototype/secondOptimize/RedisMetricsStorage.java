package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 15:58
 * @Description:
 */
public class RedisMetricsStorage implements MetricsStorage {
	@Override
	public void saveRequestInfo(RequestInfo requestInfo) {

	}

	@Override
	public List<RequestInfo> getRequestInfos(String apiName, long startTimeMillis, long endTimeMillis) {
		return null;
	}

	@Override
	public Map<String, List<RequestInfo>> getRequestInfos(long startTimeMillis, long endTimeMillis) {
		return null;
	}
}
