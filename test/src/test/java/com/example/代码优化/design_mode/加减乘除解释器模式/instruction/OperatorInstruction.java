package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.EvaluationContext;
import com.example.代码优化.design_mode.加减乘除解释器模式.BinaryOperator;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.UnaryOperator;

public final class OperatorInstruction implements ExpressionInstruction {
    private final BinaryOperator binaryOperator;
    private final UnaryOperator unaryOperator;

    public OperatorInstruction(BinaryOperator operator) {
        this.binaryOperator = operator;
        this.unaryOperator = null;
    }

    public OperatorInstruction(UnaryOperator operator) {
        this.binaryOperator = null;
        this.unaryOperator = operator;
    }

    @Override
    public void execute(EvaluationContext context) {
        if (unaryOperator != null) {
            if (unaryOperator == UnaryOperator.NOT) {
                context.pushBoolean(!context.popBoolean());
                return;
            }
            double value = context.pop();
            switch (unaryOperator) {
                case PLUS:
                    context.push(value);
                    break;
                case MINUS:
                    context.push(-value);
                    break;
                default:
                    throw new IllegalStateException("未知一元运算符：" + unaryOperator);
            }
            return;
        }

        double right = context.pop();
        double left = context.pop();
        switch (binaryOperator) {
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
            case MODULO:
                validateDivisor(right);
                context.push(requireFinite(left % right));
                break;
            case LT:
                context.pushBoolean(left < right);
                break;
            case LE:
                context.pushBoolean(left <= right);
                break;
            case GT:
                context.pushBoolean(left > right);
                break;
            case GE:
                context.pushBoolean(left >= right);
                break;
            case EQ:
                context.pushBoolean(left == right);
                break;
            case NE:
                context.pushBoolean(left != right);
                break;
            case AND:
            case OR:
                throw new IllegalStateException("逻辑运算必须由跳转指令执行：" + binaryOperator);
            default:
                throw new IllegalStateException("未知运算符：" + binaryOperator);
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
