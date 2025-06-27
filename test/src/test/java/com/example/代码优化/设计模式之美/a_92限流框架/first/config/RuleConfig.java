package com.example.代码优化.设计模式之美.a_92限流框架.first.config;

import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/1 20:09
 * @Description:
 */
public class RuleConfig {
	private List<AppRuleConfig> limitList;

	public RuleConfig() {
	}

	public RuleConfig(List<AppRuleConfig> limitList) {
		this.limitList = limitList;
	}

	public List<AppRuleConfig> getLimitList() {
		return limitList;
	}

	public void setLimitList(List<AppRuleConfig> limitList) {
		this.limitList = limitList;
	}
}
