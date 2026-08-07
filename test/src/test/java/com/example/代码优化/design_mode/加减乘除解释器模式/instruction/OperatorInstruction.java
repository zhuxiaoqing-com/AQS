package com.example.代码优化.design_mode.加减乘除解释器模式.instruction;

import com.example.代码优化.design_mode.加减乘除解释器模式.stack.DoubleStack;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionParser;

public final class OperatorInstruction implements ExpressionInstruction {
    private final ExpressionParser.Operator operator;

    public OperatorInstruction(ExpressionParser.Operator operator) {
        // 保存已经确定类型和优先级的运算符。
        this.operator = operator;
    }

    @Override
    public void applyDouble(DoubleStack stack, DoubleValueProvider values) {
        DoubleStack.NumericType numericType = stack.getNumericType();
        if (operator.operandCount == 1) {
            double value = stack.pop();
            switch (numericType) {
                case INT:
                    int intValue = toInt(value);
                    if (operator == ExpressionParser.Operator.UNARY_MINUS
                            && intValue == Integer.MIN_VALUE) {
                        throw new ArithmeticException("int 运算溢出");
                    }
                    stack.push(operator == ExpressionParser.Operator.UNARY_MINUS ? -intValue : intValue);
                    break;
                case LONG:
                    long longValue = toLong(value);
                    if (operator == ExpressionParser.Operator.UNARY_MINUS
                            && longValue == Long.MIN_VALUE) {
                        throw new ArithmeticException("long 运算溢出");
                    }
                    stack.push(operator == ExpressionParser.Operator.UNARY_MINUS ? -longValue : longValue);
                    break;
                case DOUBLE:
                    stack.push(operator == ExpressionParser.Operator.UNARY_MINUS ? -value : value);
                    break;
                default:
                    throw new IllegalStateException("未知数值类型：" + numericType);
            }
            return;
        }

        double right = stack.pop();
        double left = stack.pop();
        switch (numericType) {
            case INT:
                int intRight = toInt(right);
                int intLeft = toInt(left);
                switch (operator) {
                    case ADD:
                        stack.push(Math.addExact(intLeft, intRight));
                        break;
                    case SUBTRACT:
                        stack.push(Math.subtractExact(intLeft, intRight));
                        break;
                    case MULTIPLY:
                        stack.push(Math.multiplyExact(intLeft, intRight));
                        break;
                    case DIVIDE:
                        validateDivisor(intRight);
                        validateDivisionOverflow(intLeft, intRight);
                        stack.push(intLeft / intRight);
                        break;
                    default:
                        throw new IllegalStateException("未知运算符：" + operator.symbol);
                }
                break;
            case LONG:
                long longRight = toLong(right);
                long longLeft = toLong(left);
                switch (operator) {
                    case ADD:
                        stack.push(Math.addExact(longLeft, longRight));
                        break;
                    case SUBTRACT:
                        stack.push(Math.subtractExact(longLeft, longRight));
                        break;
                    case MULTIPLY:
                        stack.push(Math.multiplyExact(longLeft, longRight));
                        break;
                    case DIVIDE:
                        validateDivisor(longRight);
                        validateDivisionOverflow(longLeft, longRight);
                        stack.push(longLeft / longRight);
                        break;
                    default:
                        throw new IllegalStateException("未知运算符：" + operator.symbol);
                }
                break;
            case DOUBLE:
                switch (operator) {
                    case ADD:
                        stack.push(requireFinite(left + right));
                        break;
                    case SUBTRACT:
                        stack.push(requireFinite(left - right));
                        break;
                    case MULTIPLY:
                        stack.push(requireFinite(left * right));
                        break;
                    case DIVIDE:
                        validateDivisor(right);
                        stack.push(requireFinite(left / right));
                        break;
                    default:
                        throw new IllegalStateException("未知运算符：" + operator.symbol);
                }
                break;
            default:
                throw new IllegalStateException("未知数值类型：" + numericType);
        }
    }

    private static int toInt(double value) {
        requireFinite(value);
        return (int) value;
    }

    private static long toLong(double value) {
        requireFinite(value);
        return (long) value;
    }

    private static void validateDivisor(int value) {
        if (value == 0) {
            throw new ArithmeticException("除数不能为零");
        }
    }

    private static void validateDivisor(long value) {
        if (value == 0L) {
            throw new ArithmeticException("除数不能为零");
        }
    }

    private static void validateDivisor(double value) {
        if (value == 0.0d) {
            throw new ArithmeticException("除数不能为零");
        }
    }

    private static void validateDivisionOverflow(int left, int right) {
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new ArithmeticException("int 运算溢出");
        }
    }

    private static void validateDivisionOverflow(long left, long right) {
        if (left == Long.MIN_VALUE && right == -1L) {
            throw new ArithmeticException("long 运算溢出");
        }
    }

    private static double requireFinite(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new ArithmeticException("double 运算溢出");
        }
        return value;
    }
}
