package com.example.代码优化.design_mode.加减乘除解释器模式;

/** 表达式支持的一元运算符。 */
public enum UnaryOperator {
    PLUS("+"),
    MINUS("-"),
    NOT("!");

    public final String symbol;

    UnaryOperator(String symbol) {
        this.symbol = symbol;
    }
}
