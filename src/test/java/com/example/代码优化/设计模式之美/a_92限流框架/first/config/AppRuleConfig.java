package com.example.代码优化.设计模式之美.a_92限流框架.first.config;

import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/1 20:09
 * @Description:
 */
public class AppRuleConfig {
	private String appId;
	private List<ApiLimit> limitList;

	public AppRuleConfig() {
	}

	public AppRuleConfig(String appId, List<ApiLimit> limitList) {
		this.appId = appId;
		this.limitList = limitList;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public List<ApiLimit> getLimitList() {
		return limitList;
	}

	public void setLimitList(List<ApiLimit> limitList) {
		this.limitList = limitList;
	}
}
