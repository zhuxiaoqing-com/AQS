package com.example.代码优化.设计模式之美.a_25针对非业务的通用框架开发.prototype.secondOptimize.entity;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/4 14:34
 * @Description:
 */
public class RequestInfo {
	private String apiName;
	private double responseTime;
	private long timestamp;

	public RequestInfo(String apiName, double responseTime, long timestamp) {
		this.apiName = apiName;
		this.responseTime = responseTime;
		this.timestamp = timestamp;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public double getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
