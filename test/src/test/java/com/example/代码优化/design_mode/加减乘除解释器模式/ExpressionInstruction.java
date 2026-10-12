package com.example.代码优化.design_mode.加减乘除解释器模式;

/** 表达式后缀指令，运行时依赖统一从执行上下文获取。 */
public interface ExpressionInstruction {
    void execute(EvaluationContext context);
}
