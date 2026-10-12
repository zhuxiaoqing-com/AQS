package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.stack.DoubleStack;

/** 一次表达式执行所需的栈、数值模式和变量值提供器。 */
public final class EvaluationContext {
    private final DoubleStack stack;
    private final NumericMode numericMode;
    private final DoubleValueProvider values;

    public EvaluationContext(int stackCapacity,
                             NumericMode numericMode,
                             DoubleValueProvider values) {
        if (numericMode == null) {
            throw new IllegalArgumentException("数值模式不能为 null");
        }
        if (values == null) {
            throw new IllegalArgumentException("变量值提供器不能为 null");
        }
        this.stack = new DoubleStack(stackCapacity);
        this.numericMode = numericMode;
        this.values = values;
    }

    /** 所有数值在此处按当前模式转换后再入栈。 */
    public void push(double value) {
        switch (numericMode) {
            case INT:
                value = (int) value;
                break;
            case LONG:
                value = (long) value;
                break;
            case DOUBLE:
                break;
            default:
                throw new IllegalStateException("未知数值模式：" + numericMode);
        }
        stack.push(value);
    }

    /** 以语言层 BOOLEAN 写入布尔值，底层 DoubleStack 使用 0.0 / 1.0 编码。 */
    public void pushBoolean(boolean value) {
        push(value ? 1.0 : 0.0);
    }

    public double pop() {
        return stack.pop();
    }

    /** 读取编译期已保证为 BOOLEAN 的栈值。 */
    public boolean popBoolean() {
        return pop() != 0.0;
    }

    public double peek() {
        return stack.peek();
    }

    public int size() {
        return stack.size();
    }

    public NumericMode numericMode() {
        return numericMode;
    }

    public boolean isIntegerMode() {
        return numericMode == NumericMode.INT || numericMode == NumericMode.LONG;
    }

    public DoubleValueProvider values() {
        return values;
    }
}
