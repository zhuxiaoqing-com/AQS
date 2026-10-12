package com.example.代码优化.design_mode.加减乘除解释器模式.method;

import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;

/**
 * 表达式方法的执行实现。
 *
 * <p>方法指令会先从 double 栈中取出参数，再调用这里的方法实现。
 * 方法实现只负责计算并返回 NUMBER，不需要感知执行栈。BOOLEAN 方法使用
 * {@link BooleanExpressionMethod}，以 Java boolean 返回结果。</p>
 */
public interface ExpressionMethod {
    double applyDouble(DoubleValueProvider values, double... arguments);
}
