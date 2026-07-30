package com.example.代码优化.design_mode.加减乘除解释器模式;

import java.util.Set;

/**
 * 自定义表达式编译入口。
 *
 * <p>推荐在配置加载阶段调用 {@link #compile(String)}，运行时只调用
 * {@link CompiledExpression#evaluate(ValueProvider)}。</p>
 */
public final class SimpleExpression {
    private SimpleExpression() {
        // 工具类不允许实例化，表达式通过静态 compile 入口创建。
    }

    public static CompiledExpression compile(String source) {
        // 对外隐藏解析器和指令细节，只暴露已编译表达式。
        return new CompiledExpression(Expression.compile(source));
    }

    /** 已编译、可重复执行的表达式。 */
    public static final class CompiledExpression {
        private final Expression expression;

        private CompiledExpression(Expression expression) {
            // 编译阶段已经完成词法分析和优先级处理。
            this.expression = expression;
        }

        public double evaluate(ValueProvider values) {
            // 同一个 CompiledExpression 可以传入不同变量值提供器重复计算。
            return expression.evaluate(values);
        }

        public Set<String> getVariables() {
            // 返回编译阶段收集到的变量名，便于调用方准备值提供逻辑。
            return expression.getVariables();
        }
    }
}
