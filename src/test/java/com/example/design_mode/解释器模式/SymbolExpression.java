package com.example.design_mode.解释器模式;

import java.util.HashMap;

// 抽象运算符号解析器
public abstract class SymbolExpression extends Expression {

    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
