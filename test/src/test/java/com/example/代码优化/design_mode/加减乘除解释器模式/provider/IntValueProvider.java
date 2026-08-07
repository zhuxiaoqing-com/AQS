package com.example.代码优化.design_mode.加减乘除解释器模式.provider;

/** 为 int 表达式提供变量值。 */
@FunctionalInterface
public interface IntValueProvider {
    int getValue(String name);
}
