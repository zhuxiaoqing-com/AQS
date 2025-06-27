package com.example.代码优化.设计模式之美.a_92限流框架.first.datasource;

import com.example.代码优化.设计模式之美.a_92限流框架.first.config.RuleConfig;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/2 10:31
 * @Description:
 */
public interface RuleConfigSource {
	RuleConfig load();
}
