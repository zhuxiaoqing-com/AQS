package com.example.代码优化.设计模式之美.a_72解释器模式.接口告警规则功能.Expression;

import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:35
 * @Description:
 */
public interface Expression {
	boolean interpret(Map<String, Long> stats);
}
