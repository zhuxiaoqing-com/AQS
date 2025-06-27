package com.example.代码优化.设计模式之美.a_72解释器模式.接口告警规则功能;


import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:32
 * @Description:
 */
public class DemoTest {
	public static void main(String[] args) {
		String rule = "key1 > 100 && key3 < 30 || key3 < 100 || key4 == 88 && !key1 && key2";
		//String rule = "key1 > 100 && key3 < 30 || key3 < 100 || key4 == 88";
		AlertRuleInterpreter interpreter = new AlertRuleInterpreter(rule);
		Map<String, Long> stats = new HashMap<>();
		stats.put("key1", 101l);
		stats.put("key3", 121l);
		stats.put("key4", 88l);
		boolean alert = interpreter.interpret(stats);
		System.out.println(alert);
	}
}
