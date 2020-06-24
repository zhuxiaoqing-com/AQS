package com.example.代码优化.设计模式之美.a_72解释器模式.加减乘除解释器;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:16
 * @Description:
 */
public class AdditionExpression implements Expression {
	private Expression exp1;
	private Expression exp2;

	public AdditionExpression(Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public long interpret() {
		return exp1.interpret() + exp2.interpret();
	}
}
