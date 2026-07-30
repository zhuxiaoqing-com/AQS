package com.example.代码优化.design_mode.加减乘除解释器模式;

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
        return new ExpressionParser(source).parse();
    }

    /**
     * 使用变量值计算表达式。
     *
     * @param values 变量值提供器
     * @return 计算结果
     * @throws IllegalArgumentException 缺少变量值
     * @throws ArithmeticException 除数为零
     */
    public double evaluate(ValueProvider values) {
        // null 提供器无法提供变量值，提前报错比执行到某个变量时再报错更清晰。
        if (values == null) {
            throw new IllegalArgumentException("变量值提供器不能为 null");
        }

        // 后缀指令按顺序执行，DoubleStack 的栈顶保存当前最新的操作数。
        // 指令数量是执行过程中栈深度的安全上限，用它作为初始容量避免扩容。
        DoubleStack stack = new DoubleStack(instructions.size());
        // 每次 evaluate 使用独立栈，因此同一个已编译表达式可以重复、并发计算。
        for (ExpressionInstruction instruction : instructions) {
            instruction.apply(stack, values);
        }
        // 合法表达式执行结束后必须只剩一个最终结果。
        if (stack.size() != 1) {
            throw new IllegalStateException("表达式执行失败：结果栈中有 " + stack.size() + " 个值");
        }
        return stack.pop();
    }

    /**
     * 返回表达式中出现的变量名，顺序与首次出现顺序一致。
     */
    public Set<String> getVariables() {
        // variables 已经是不可修改集合，调用方可以安全地读取它。
        return variables;
    }
}
