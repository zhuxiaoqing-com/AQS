package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.stack.DoubleStack;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.IntValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.LongValueProvider;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 已编译的表达式。
 *
 * <p>表达式只在 {@link #compile(String)} 时解析一次，之后可以安全地重复计算。
 * 实例本身是不可变的，运行时状态只保存在每次 evaluate 调用的局部栈中。</p>
 */
final class Expression {
    private final List<ExpressionInstruction> instructions;
    private final Set<String> variables;

    Expression(List<ExpressionInstruction> instructions, Set<String> variables) {
        // 编译完成后不再允许外部修改指令和变量集合，保证表达式实例不可变。
        this.instructions = Collections.unmodifiableList(instructions);
        this.variables = Collections.unmodifiableSet(new LinkedHashSet<>(variables));
    }

    /**
     * 编译表达式。
     *
     * @param source 表达式，例如 {@code (x + 2 * 2) + y + (d * 3 / 10)}
     * @return 可重复执行的已编译表达式
     * @throws ExpressionParseException 表达式语法错误
     */
    public static Expression compile(String source) {
        // 解析和优先级计算全部放在编译阶段，避免每次 evaluate 重复解析字符串。
        return compile(source, ExpressionMethodRegistry.defaultRegistry());
    }

    static Expression compile(String source, ExpressionMethodRegistry methods) {
        if (methods == null) {
            throw new IllegalArgumentException("方法注册表不能为 null");
        }
        // 解析阶段只绑定方法描述，实际计算仍然发生在 evaluate 阶段。
        return new ExpressionParser(source, methods).parse();
    }

    /** 使用 int 基础类型执行表达式。 */
    public int evaluateInt(IntValueProvider values) {
        requireValueProvider(values);
        return (int) evaluateDouble(name -> values.getValue(name), DoubleStack.NumericType.INT);
    }

    /** 使用 long 基础类型执行表达式。 */
    public long evaluateLong(LongValueProvider values) {
        requireValueProvider(values);
        return (long) evaluateDouble(name -> values.getValue(name), DoubleStack.NumericType.LONG);
    }

    /** 使用 double 基础类型执行表达式。 */
    public double evaluateDouble(DoubleValueProvider values) {
        requireValueProvider(values);
        return evaluateDouble(values, DoubleStack.NumericType.DOUBLE);
    }

    private double evaluateDouble(DoubleValueProvider values, DoubleStack.NumericType numericType) {
        DoubleStack stack = new DoubleStack(instructions.size(), numericType);
        for (ExpressionInstruction instruction : instructions) {
            instruction.applyDouble(stack, values);
        }
        requireSingleResult(stack.size());
        return stack.pop();
    }

    /** 保留原有 double 变量提供器入口，避免已有调用方必须立即改名。 */
    public double evaluate(ValueProvider values) {
        requireValueProvider(values);
        return evaluateDouble(name -> {
            Double value = values.getValue(name);
            if (value == null) {
                throw new IllegalArgumentException("没有为变量提供值：" + name);
            }
            return value;
        });
    }

    private static void requireValueProvider(Object values) {
        if (values == null) {
            throw new IllegalArgumentException("变量值提供器不能为 null");
        }
    }

    private static void requireSingleResult(int size) {
        if (size != 1) {
            throw new IllegalStateException("表达式执行失败：结果栈中有 " + size + " 个值");
        }
    }

    /**
     * 返回表达式中出现的变量名，顺序与首次出现顺序一致。
     */
    public Set<String> getVariables() {
        // variables 已经是不可修改集合，调用方可以安全地读取它。
        return variables;
    }
}
