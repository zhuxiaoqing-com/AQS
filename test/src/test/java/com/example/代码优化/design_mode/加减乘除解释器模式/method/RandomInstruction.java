package com.example.代码优化.design_mode.加减乘除解释器模式.method;

import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomInstruction implements ExpressionMethod {
    public RandomInstruction() {
    }

    @Override
    public double applyDouble(DoubleValueProvider values, double... arguments) {
        double min = arguments[0];
        double max = arguments[1];
        if (Double.isNaN(min) || Double.isNaN(max)
                || Double.isInfinite(min) || Double.isInfinite(max)) {
            throw new IllegalArgumentException("m_random 的参数必须是有限数值");
        }
        validateBounds(min, max);
        return min == max ? min : ThreadLocalRandom.current().nextDouble(min, max);
    }

    private static void validateBounds(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("m_random 的 min 不能大于 max");
        }
    }
}
