package com.example.代码优化.设计模式之美.a_72解释器模式.接口告警规则功能.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:36
 * @Description:
 */
public class OrEqualsExpression implements Expression {
	private List<Expression> list = new ArrayList<>();

	public OrEqualsExpression(List<Expression> list) {
		this.list = list;
	}

	public OrEqualsExpression(String strExpression) {
		parse(strExpression);
	}


	private void parse(String strExpression) {
		String[] split = strExpression.split("\\|\\|");
		for (String s : split) {
			list.add(new AndEqualsExpression(s));
		}
	}


	@Override
	public boolean interpret(Map<String, Long> stats) {
		for (Expression expression : list) {
			if (expression.interpret(stats)) {
				return true;
			}
		}
		return true;
	}
}
