package com.example.代码优化.design_mode.加减乘除解释器模式;

/** 表达式支持的二元运算符。逻辑运算符由编译器生成跳转，不生成普通运算指令。 */
public enum BinaryOperator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    MODULO("%"),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">="),
    EQ("=="),
    NE("!="),
    AND("&&"),
    OR("||");

    public final String symbol;

    BinaryOperator(String symbol) {
        this.symbol = symbol;
    }
}
