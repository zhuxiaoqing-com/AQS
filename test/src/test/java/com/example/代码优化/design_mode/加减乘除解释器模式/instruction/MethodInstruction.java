package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.EvaluationContext;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionValueType;
import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethod;
import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;

/** 把已注册的方法适配成普通的后缀执行指令。 */
public final class MethodInstruction implements ExpressionInstruction {
    private final ExpressionMethod method;
    private final int argumentCount;

    public MethodInstruction(ExpressionMethod method, int argumentCount) {
        this.method = method;
        this.argumentCount = argumentCount;
    }

    public MethodInstruction(ExpressionMethodRegistry.RegisteredMethod method) {
        if (method.returnType != ExpressionValueType.NUMBER) {
            throw new IllegalArgumentException("BOOLEAN 方法必须使用 BooleanMethodInstruction");
        }
        this.method = method.implementation;
        this.argumentCount = method.argumentCount;
    }

    @Override
    public void execute(EvaluationContext context) {
        DoubleValueProvider values = context.values();
        double[] arguments = new double[argumentCount];
        for (int i = argumentCount - 1; i >= 0; i--) {
            arguments[i] = context.pop();
        }
        context.push(method.applyDouble(values, arguments));
    }
}
