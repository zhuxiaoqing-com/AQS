package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.*;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.stack.DoubleStack;

public final class NumberInstruction implements ExpressionInstruction {
    private final double value;

    public NumberInstruction(String text) {
        // 统一先解析成 double，保证小数也可以用于 int 和 long 执行器。
        value = Double.parseDouble(text);
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("数字超出 double 范围：" + text);
        }
    }

    @Override
    public void applyDouble(DoubleStack stack, DoubleValueProvider values) {
        stack.push(value);
    }
}
