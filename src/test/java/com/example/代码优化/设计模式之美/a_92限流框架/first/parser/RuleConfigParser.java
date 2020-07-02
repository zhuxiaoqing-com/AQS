package com.example.代码优化.设计模式之美.a_92限流框架.first.parser;

import com.example.代码优化.设计模式之美.a_92限流框架.first.config.RuleConfig;

import java.io.InputStream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/2 10:20
 * @Description:
 */
public interface RuleConfigParser {
	RuleConfig parse(String condigText);
	RuleConfig parse(InputStream in);

}
