package com.example.代码优化.design_mode.加减乘除解释器模式;

/** 加载 BOOLEAN 变量，并通过布尔接口写入执行栈。 */
final class BooleanVariableInstruction implements ExpressionInstruction {
    private final String name;

    BooleanVariableInstruction(String name) {
        this.name = name;
    }

    @Override
    public void execute(EvaluationContext context) {
        context.pushBoolean(context.values().getValue(name) != 0.0);
    }
}
