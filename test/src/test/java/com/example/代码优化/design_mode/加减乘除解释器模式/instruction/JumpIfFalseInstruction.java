package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.EvaluationContext;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionInstruction;

/** 弹出 BOOLEAN；值为 false 时跳到回填目标。 */
public final class JumpIfFalseInstruction implements ExpressionInstruction {
    private int target;

    public JumpIfFalseInstruction(int target) {
        this.target = target;
    }

    public void patchTarget(int target) {
        this.target = target;
    }

    @Override
    public void execute(EvaluationContext context) {
        throw new IllegalStateException("条件跳转指令必须由表达式执行器提供程序计数器");
    }

    @Override
    public int execute(EvaluationContext context, int programCounter) {
        return context.popBoolean() ? programCounter + 1 : target;
    }
}
