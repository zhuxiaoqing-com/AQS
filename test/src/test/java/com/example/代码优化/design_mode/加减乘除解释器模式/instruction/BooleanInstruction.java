package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.EvaluationContext;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionInstruction;

/** 把布尔字面量压栈；数值编码由执行上下文负责。 */
public final class BooleanInstruction implements ExpressionInstruction {
    private final boolean value;

    public BooleanInstruction(boolean value) {
        this.value = value;
    }

    @Override
    public void execute(EvaluationContext context) {
        context.pushBoolean(value);
    }
}
