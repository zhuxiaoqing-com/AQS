package com.example.代码优化.design_mode.加减乘除解释器模式;

/** 表达式编译阶段的语法错误。 */
public final class ExpressionParseException extends IllegalArgumentException {
    private final int position;

    ExpressionParseException(String message, int position) {
        // 把位置拼进异常消息，让日志中可以直接定位表达式错误。
        super(message + "（位置：" + position + "）");
        this.position = position;
    }

    public int getPosition() {
        // 保留结构化位置，调用方不必从异常字符串中解析位置。
        return position;
    }
}
