package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.EvaluationContext;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionParser;

public final class OperatorInstruction implements ExpressionInstruction {
    private final ExpressionParser.Operator operator;

    public OperatorInstruction(ExpressionParser.Operator operator) {
        // 保存已经确定类型和优先级的运算符。
        this.operator = operator;
    }

    @Override
    public void execute(EvaluationContext context) {
        if (operator.operandCount == 1) {
            double value = context.pop();
            context.push(operator == ExpressionParser.Operator.UNARY_MINUS ? -value : value);
            return;
        }

        double right = context.pop();
        double left = context.pop();
        switch (operator) {
            case ADD:
                context.push(requireFinite(left + right));
                break;
            case SUBTRACT:
                context.push(requireFinite(left - right));
                break;
            case MULTIPLY:
                context.push(requireFinite(left * right));
                break;
            case DIVIDE:
                validateDivisor(right);
                context.push(requireFinite(left / right));
                break;
            default:
                throw new IllegalStateException("未知运算符：" + operator.symbol);
        }
    }

    private static void validateDivisor(double value) {
        if (value == 0.0d) {
            throw new ArithmeticException("除数不能为零");
        }
    }

    private static double requireFinite(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new ArithmeticException("double 运算溢出");
        }
        return value;
    }
}
