package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.*;
import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethod;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.stack.DoubleStack;

/** 把已注册的方法适配成普通的后缀执行指令。 */
public final class MethodInstruction implements ExpressionInstruction {
    private final ExpressionMethod method;
    private final int argumentCount;

    public MethodInstruction(ExpressionMethod method, int argumentCount) {
        this.method = method;
        this.argumentCount = argumentCount;
    }

    @Override
    public void applyDouble(DoubleStack stack, DoubleValueProvider values) {
        double[] arguments = new double[argumentCount];
        for (int i = argumentCount - 1; i >= 0; i--) {
            arguments[i] = stack.pop();
        }
        stack.push(method.applyDouble(values, arguments));
    }
}
