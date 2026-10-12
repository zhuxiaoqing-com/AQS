package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.EvaluationContext;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionValueType;
import com.example.代码优化.design_mode.加减乘除解释器模式.method.BooleanExpressionMethod;
import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;

/** 执行返回 Java boolean 的表达式方法。 */
public final class BooleanMethodInstruction implements ExpressionInstruction {
    private final BooleanExpressionMethod method;
    private final int argumentCount;

    public BooleanMethodInstruction(BooleanExpressionMethod method, int argumentCount) {
        this.method = method;
        this.argumentCount = argumentCount;
    }

    public BooleanMethodInstruction(ExpressionMethodRegistry.RegisteredMethod method) {
        if (method.returnType != ExpressionValueType.BOOLEAN) {
            throw new IllegalArgumentException("NUMBER 方法必须使用 MethodInstruction");
        }
        this.method = method.booleanImplementation;
        this.argumentCount = method.argumentCount;
    }

    @Override
    public void execute(EvaluationContext context) {
        DoubleValueProvider values = context.values();
        double[] arguments = new double[argumentCount];
        for (int i = argumentCount - 1; i >= 0; i--) {
            arguments[i] = context.pop();
        }
        context.pushBoolean(method.applyBoolean(values, arguments));
    }
}
