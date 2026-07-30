package com.example.代码优化.design_mode.加减乘除解释器模式;

/** 表达式后缀指令。每条指令只负责一次栈操作。 */
interface ExpressionInstruction {
    // 所有指令都共享同一个操作数栈；ValueProvider 只在变量指令中使用。
    void apply(DoubleStack stack, ValueProvider values);
}

final class NumberInstruction implements ExpressionInstruction {
    private final double value;

    NumberInstruction(double value) {
        // 常量在编译阶段就完成字符串解析。
        this.value = value;
    }

    @Override
    public void apply(DoubleStack stack, ValueProvider values) {
        // 常量在编译阶段已经转成 double，执行时只需压入操作数栈。
        stack.push(value);
    }
}

final class VariableInstruction implements ExpressionInstruction {
    private final String name;

    VariableInstruction(String name) {
        // 只保存变量名，不保存某一次 evaluate 的变量值。
        this.name = name;
    }

    @Override
    public void apply(DoubleStack stack, ValueProvider values) {
        // 变量值属于本次计算的数据，因此每次执行时按名称向 ValueProvider 查询。
        Number value = values.getValue(name);
        if (value == null) {
            // 缺少变量值是调用方数据问题，不能默认为 0。
            throw new IllegalArgumentException("没有为变量提供值：" + name);
        }
        stack.push(value.doubleValue());
    }
}

final class OperatorInstruction implements ExpressionInstruction {
    private final ExpressionParser.Operator operator;

    OperatorInstruction(ExpressionParser.Operator operator) {
        // 保存已经确定类型和优先级的运算符。
        this.operator = operator;
    }

    @Override
    public void apply(DoubleStack stack, ValueProvider values) {
        // 后缀表达式执行规则：先弹出右操作数，再弹出左操作数。
        if (operator.operandCount == 1) {
            // 一元运算符只消费一个操作数，结果再次压回栈顶。
            double value = pop(stack);
            stack.push(operator == ExpressionParser.Operator.UNARY_MINUS ? -value : value);
            return;
        }

        double right = pop(stack);
        double left = pop(stack);
        // 二元运算符必须保持 left operator right 的顺序，尤其是减法和除法。
        switch (operator) {
            case ADD:
                stack.push(left + right);
                break;
            case SUBTRACT:
                stack.push(left - right);
                break;
            case MULTIPLY:
                stack.push(left * right);
                break;
            case DIVIDE:
                // Java double 默认会得到 Infinity，这里主动报错暴露公式问题。
                if (right == 0.0d) {
                    throw new ArithmeticException("除数不能为零");
                }
                stack.push(left / right);
                break;
            default:
                throw new IllegalStateException("未知运算符：" + operator.symbol);
        }
    }

    private static double pop(DoubleStack stack) {
        // DoubleStack 统一负责空栈校验和出栈，避免每个运算符重复实现。
        return stack.pop();
    }
}
