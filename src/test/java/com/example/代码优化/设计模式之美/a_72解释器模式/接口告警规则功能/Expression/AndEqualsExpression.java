package com.example.代码优化.设计模式之美.a_72解释器模式.接口告警规则功能.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:36
 * @Description:
 */
public class AndEqualsExpression implements Expression {
	private List<Expression> list = new ArrayList<>();

	public AndEqualsExpression(List<Expression> list) {
		this.list = list;
	}

	public AndEqualsExpression(String strExpression) {
		parse(strExpression);
	}


	private void parse(String strExpression) {
		String[] split = strExpression.trim().split("&&");
		for (String s : split) {
			Expression expression = null;
			if (s.contains(">")) {
				expression = new GreaterExpression(s);
			} else if (s.contains("<")) {
				expression = new LessExpression(s);
			} else if (s.contains("==")) {
				expression = new EqualsExpression(s);
				break;
			}
			if (expression == null) {
				throw new RuntimeException();
			}
			list.add(expression);
		}
	}


	@Override
	public boolean interpret(Map<String, Long> stats) {
		for (Expression expression : list) {
			if (!expression.interpret(stats)) {
				return false;
			}
		}
		return true;
	}
}
