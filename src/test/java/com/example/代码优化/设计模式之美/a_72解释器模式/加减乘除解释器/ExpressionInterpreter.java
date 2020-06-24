package com.example.代码优化.设计模式之美.a_72解释器模式.加减乘除解释器;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 20:17
 * @Description:
 */
public class ExpressionInterpreter {
	private Deque<Expression> numbers = new LinkedList<>();

	public long interpret(String expression) {
		String[] elements = expression.split(" ");
		int length = elements.length;
		for (int i = 0; i < (length + 1) / 2; ++i) {
			numbers.addLast(new NumberExpression(elements[i]));
		}

		for (int i = (length + 1) / 2; i < length; ++i) {
			String operator = elements[i];
			Expression exp1 = numbers.pollFirst();
			Expression exp2 = numbers.pollFirst();

			Expression combinedExp = null;
			switch (operator) {
				case "+":
					combinedExp = new AdditionExpression(exp1, exp2);
					break;
				case "-":
					combinedExp = new AdditionExpression(exp1, exp2);
					break;
				case "*":
					combinedExp = new AdditionExpression(exp1, exp2);
					break;
				case "/":
					combinedExp = new AdditionExpression(exp1, exp2);
					break;
			}
			assert combinedExp != null;
			long interpret = combinedExp.interpret();
			numbers.addFirst(new NumberExpression(interpret));
		}
		return numbers.pop().interpret();
	}
}
