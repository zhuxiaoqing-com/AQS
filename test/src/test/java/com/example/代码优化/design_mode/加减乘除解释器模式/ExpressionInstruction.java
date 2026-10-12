package com.example.代码优化.design_mode.加减乘除解释器模式;

/** 表达式虚拟机指令；普通指令顺序执行，跳转指令返回新的程序计数器。 */
public interface ExpressionInstruction {
    void execute(EvaluationContext context);

    /** 普通指令默认执行后前进一条；控制流指令覆盖此方法。 */
    default int execute(EvaluationContext context, int programCounter) {
        execute(context);
        return programCounter + 1;
    }
}
