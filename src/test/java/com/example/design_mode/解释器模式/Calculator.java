package com.example.design_mode.解释器模式;

import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    // 定义表达式
    private Expression expression;

    // 构造函数传参,并解析
    public Calculator(String expStr) {
        Stack<Expression> stack = new Stack<>();
        // 表达式拆分为字符数组
        char[] chars = expStr.toCharArray();
        // 运算
        Expression left = null;
        Expression right = null;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '+':// 加法
                    // 加法结果放到栈中
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    stack.push(new AddExpression(left, right));
                    break;
                case '-':// 减法
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    stack.push(new SubExpression(left, right));
                    break;
                default: // 公式中的变量
                    stack.push(new VarExpression(String.valueOf(chars[i])));
            }
        }
        this.expression = stack.pop();
    }

    // 开始运算
    public int run(HashMap<String, Integer> var) {
        return this.expression.interpreter(var);
    }
}
