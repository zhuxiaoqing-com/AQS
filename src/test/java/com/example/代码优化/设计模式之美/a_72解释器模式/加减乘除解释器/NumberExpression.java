package com.example.代码优化.设计模式之美.a_72解释器模式.加减乘除解释器;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:14
 * @Description:
 */
public class NumberExpression implements Expression {
	private long number;

	public NumberExpression(long number) {
		this.number = number;
	}

	public NumberExpression(String number) {
		this.number = Long.parseLong(number);
	}

	@Override
	public long interpret() {
		return this.number;
	}
}
