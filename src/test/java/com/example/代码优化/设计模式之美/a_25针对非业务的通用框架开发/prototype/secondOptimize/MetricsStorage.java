package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize;

import com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 14:18
 * @Description: 存储 DAO
 */
public interface MetricsStorage {

	void saveRequestInfo(RequestInfo requestInfo);

	List<RequestInfo> getRequestInfos(String apiName, long startTimeMillis, long endTimeMillis);

	Map<String, List<RequestInfo>> getRequestInfos(long startTimeMillis, long endTimeMillis);
}
