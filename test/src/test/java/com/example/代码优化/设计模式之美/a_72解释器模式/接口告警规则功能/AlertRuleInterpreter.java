package com.example.代码优化.设计模式之美.a_72解释器模式.接口告警规则功能;

import com.example.代码优化.设计模式之美.a_72解释器模式.接口告警规则功能.Expression.Expression;
import com.example.代码优化.设计模式之美.a_72解释器模式.接口告警规则功能.Expression.OrEqualsExpression;

import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:29
 * @Description:
 */
public class AlertRuleInterpreter {
	Expression expression;

	//key1 > 100 && key2 < 1000 || key3 == 200
	public AlertRuleInterpreter(String ruleExpression) {
		OrEqualsExpression orEqualsExpression = new OrEqualsExpression(ruleExpression);
		this.expression = orEqualsExpression;
	}

	//<String, Long> apiStat = new HashMap<>();
	//apiStat.put("key1", 103);
	//apiStat.put("key2", 987);
	public boolean interpret(Map<String, Long> stats) {
		return expression.interpret(stats);
	}

}
