package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.EvaluationContext;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionInstruction;

/** 无条件跳转；目标只在编译器回填期间可变。 */
public final class JumpInstruction implements ExpressionInstruction {
    private int target;

    public JumpInstruction(int target) {
        this.target = target;
    }

    public void patchTarget(int target) {
        this.target = target;
    }

    @Override
    public void execute(EvaluationContext context) {
        throw new IllegalStateException("跳转指令必须由表达式执行器提供程序计数器");
    }

    @Override
    public int execute(EvaluationContext context, int programCounter) {
        return target;
    }
}
