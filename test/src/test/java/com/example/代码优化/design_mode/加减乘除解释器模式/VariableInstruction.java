package com.example.代码优化.design_mode.加减乘除解释器模式;

final class VariableInstruction implements ExpressionInstruction {
    private final String name;

    VariableInstruction(String name) {
        // 只保存变量名，不保存某一次 evaluate 的变量值。
        this.name = name;
    }

    @Override
    public void execute(EvaluationContext context) {
        context.push(context.values().getValue(name));
    }
}
