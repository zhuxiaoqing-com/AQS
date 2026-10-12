package com.example.代码优化.design_mode.加减乘除解释器模式;

/**
 * 编译阶段查询变量的静态类型；实际变量值仍由 DoubleValueProvider 提供。
 * BOOLEAN 变量的运行时值按约定使用 0.0 和 1.0；未知变量应返回 null。
 */
public interface VariableTypeProvider {
    ExpressionValueType getType(String variableName);
}
