package com.example.代码优化.design_mode.加减乘除解释器模式.provider;

/** 为 double 表达式提供变量值。 */
@FunctionalInterface
public interface DoubleValueProvider {
    double getValue(String name);
}
