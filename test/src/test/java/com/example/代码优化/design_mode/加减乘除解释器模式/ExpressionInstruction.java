package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.stack.DoubleStack;

/** 表达式后缀指令，统一使用 double 栈执行。 */
public interface ExpressionInstruction {
    void applyDouble(DoubleStack stack, DoubleValueProvider values);
}
