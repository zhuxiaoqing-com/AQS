package com.example.代码优化.design_mode.加减乘除解释器模式;

/**
 * 表达式变量值提供器。
 *
 * <p>表达式执行时按变量名读取值，不需要为每次计算创建 Map。</p>
 */
@FunctionalInterface
public interface ValueProvider {
    /**
     * 根据变量名返回数值；变量名包含表达式中的 {@code v_} 前缀。
     *
     * @param name 变量名，例如 {@code v_damage}
     * @return 变量值；返回 {@code null} 表示没有这个变量值
     */
    Double getValue(String name);
}
