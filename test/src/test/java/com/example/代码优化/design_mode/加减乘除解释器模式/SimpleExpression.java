package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethod;
import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.DoubleValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.IntValueProvider;
import com.example.代码优化.design_mode.加减乘除解释器模式.provider.LongValueProvider;

import java.util.Set;

/**
 * 自定义表达式编译入口。
 *
 * <p>推荐在配置加载阶段调用 {@link #compile(String)}，运行时根据目标基础类型调用
 * {@link CompiledExpression#evaluateInt(IntValueProvider)}、
 * {@link CompiledExpression#evaluateLong(LongValueProvider)} 或
 * {@link CompiledExpression#evaluateDouble(DoubleValueProvider)}。</p>
 */
public final class SimpleExpression {
    private static final ExpressionMethodRegistry DEFAULT_METHODS =
            ExpressionMethodRegistry.defaultRegistry();

    private SimpleExpression() {
        // 工具类不允许实例化，表达式通过静态 compile 入口创建。
    }

    public static CompiledExpression compile(String source) {
        // 对外隐藏解析器和指令细节，只暴露已编译表达式。
        return compile(source, DEFAULT_METHODS);
    }

    /**
     * 使用指定的方法注册表编译表达式，适合为不同业务隔离方法集合。
     */
    public static CompiledExpression compile(String source, ExpressionMethodRegistry methods) {
        return new CompiledExpression(Expression.compile(source, methods));
    }

    /**
     * 向默认注册表添加方法；之后通过 {@link #compile(String)} 编译的表达式都可以使用它。
     */
    public static void registerMethod(String name,
                                      int argumentCount,
                                      ExpressionMethod implementation) {
        DEFAULT_METHODS.register(name, argumentCount, implementation);
    }

    /** 已编译、可重复执行的表达式。 */
    public static final class CompiledExpression {
        private final Expression expression;

        private CompiledExpression(Expression expression) {
            // 编译阶段已经完成词法分析和优先级处理。
            this.expression = expression;
        }

        public int evaluateInt(IntValueProvider values) {
            // 同一个 CompiledExpression 可以使用 int 基础类型重复计算。
            return expression.evaluateInt(values);
        }

        public long evaluateLong(LongValueProvider values) {
            // 同一个 CompiledExpression 可以使用 long 基础类型重复计算。
            return expression.evaluateLong(values);
        }

        public double evaluateDouble(DoubleValueProvider values) {
            // 同一个 CompiledExpression 可以使用 double 基础类型重复计算。
            return expression.evaluateDouble(values);
        }

        public double evaluate(ValueProvider values) {
            // 保留原有 double 入口，已有代码仍然可以继续使用。
            return expression.evaluate(values);
        }

        public Set<String> getVariables() {
            // 返回编译阶段收集到的变量名，便于调用方准备值提供逻辑。
            return expression.getVariables();
        }
    }
}
