package com.example.代码优化.design_mode.加减乘除解释器模式.method;

import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;

/** 返回 BOOLEAN 的表达式方法；布尔结果保持 Java boolean，执行上下文再编码入 double 栈。 */
public interface BooleanExpressionMethod {
    boolean applyBoolean(DoubleValueProvider values, double... arguments);
}
