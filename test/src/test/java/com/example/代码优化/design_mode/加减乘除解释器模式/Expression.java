package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.IntValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.LongValueProvider;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/** 已编译的类型化表达式；执行时只使用原生 double 栈和指令数组。 */
final class Expression {
    private final ExpressionInstruction[] instructions;
    private final Set<String> variables;
    private final ExpressionValueType type;

    Expression(ExpressionInstruction[] instructions,
               Set<String> variables,
               ExpressionValueType type) {
        this.instructions = instructions.clone();
        this.variables = Collections.unmodifiableSet(new LinkedHashSet<>(variables));
        this.type = type;
    }

    public static Expression compile(String source) {
        return compile(source, ExpressionMethodRegistry.defaultRegistry(),
                name -> ExpressionValueType.NUMBER);
    }

    static Expression compile(String source, ExpressionMethodRegistry methods) {
        return compile(source, methods, name -> ExpressionValueType.NUMBER);
    }

    static Expression compile(String source,
                              ExpressionMethodRegistry methods,
                              VariableTypeProvider variableTypes) {
        if (methods == null) {
            throw new IllegalArgumentException("方法注册表不能为 null");
        }
        if (variableTypes == null) {
            throw new IllegalArgumentException("变量类型提供器不能为 null");
        }
        ExpressionParser parser = new ExpressionParser(source, methods, variableTypes);
        ExpressionNode root = parser.parse();
        ExpressionInstruction[] instructions = new ExpressionCompiler().compile(root);
        return new Expression(instructions, parser.getVariables(), root.getType());
    }

    /** 使用 int 基础类型执行数值表达式。 */
    public int evaluateInt(IntValueProvider values) {
        requireType(ExpressionValueType.NUMBER, "evaluateInt");
        requireValueProvider(values);
        return (int) evaluate(name -> values.getValue(name), NumericMode.INT);
    }

    /** 使用 long 入栈语义执行；double 底层无法保证超大 long 的精度。 */
    public long evaluateLong(LongValueProvider values) {
        requireType(ExpressionValueType.NUMBER, "evaluateLong");
        requireValueProvider(values);
        return (long) evaluate(name -> values.getValue(name), NumericMode.LONG);
    }

    /** 执行 NUMBER 表达式。 */
    public double evaluateNumber(DoubleValueProvider values) {
        requireType(ExpressionValueType.NUMBER, "evaluateNumber");
        requireValueProvider(values);
        return evaluate(values, NumericMode.DOUBLE);
    }

    /** 保留原有 double 入口。 */
    public double evaluateDouble(DoubleValueProvider values) {
        return evaluateNumber(values);
    }

    /** 执行 BOOLEAN 表达式；BOOLEAN 在栈中使用 0.0 和 1.0。 */
    public boolean evaluateBoolean(DoubleValueProvider values) {
        requireType(ExpressionValueType.BOOLEAN, "evaluateBoolean");
        requireValueProvider(values);
        return evaluate(values, NumericMode.DOUBLE) != 0.0;
    }

    private double evaluate(DoubleValueProvider values, NumericMode numericMode) {
        EvaluationContext context = new EvaluationContext(instructions.length, numericMode, values);
        int programCounter = 0;
        while (programCounter < instructions.length) {
            programCounter = instructions[programCounter].execute(context, programCounter);
        }
        requireSingleResult(context.size());
        return context.pop();
    }

    /** 兼容原有可空 boxed ValueProvider 数值入口。 */
    public double evaluate(ValueProvider values) {
        requireType(ExpressionValueType.NUMBER, "evaluate");
        requireValueProvider(values);
        return evaluateNumber(name -> {
            Double value = values.getValue(name);
            if (value == null) {
                throw new IllegalArgumentException("没有为变量提供值：" + name);
            }
            return value;
        });
    }

    public ExpressionValueType getType() {
        return type;
    }

    private void requireType(ExpressionValueType expected, String methodName) {
        if (type != expected) {
            throw new IllegalStateException(methodName + " 需要 " + expected + " 表达式，实际为 " + type);
        }
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

    /** 返回变量首次出现顺序对应的不可修改集合。 */
    public Set<String> getVariables() {
        return variables;
    }
}
