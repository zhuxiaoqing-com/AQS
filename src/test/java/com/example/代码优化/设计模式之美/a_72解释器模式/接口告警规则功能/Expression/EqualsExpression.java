package com.example.代码优化.设计模式之美.a_72解释器模式.接口告警规则功能.Expression;

import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:36
 * @Description:
 */
public class EqualsExpression implements Expression {
	private String key;
	private int value;

	public EqualsExpression(String key, int value) {
		this.key = key;
		this.value = value;
	}

	public EqualsExpression(String strExpression) {
		parse(strExpression);
	}


	private void parse(String strExpression) {
		String[] split = strExpression.trim().split("\\s+");
		if (split.length < 3 || !split[1].equals("==")) {
			throw new RuntimeException();
		}
		this.key = split[0];
		this.value = Integer.parseInt(split[2]);
	}


	@Override
	public boolean interpret(Map<String, Long> stats) {
		long currValue = stats.getOrDefault(key, 0L);
		return currValue == value;
	}
}
