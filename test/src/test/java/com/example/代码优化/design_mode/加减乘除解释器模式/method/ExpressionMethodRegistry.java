package com.example.代码优化.design_mode.加减乘除解释器模式.method;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/** 表达式方法注册表。注册完成后可安全地供多个线程编译表达式。 */
public final class ExpressionMethodRegistry {
    private final Map<String, RegisteredMethod> methods = new ConcurrentHashMap<>();

    /**
     * 注册一个表达式方法。
     *
     * @param name 方法名，例如 {@code m_random}
     * @param argumentCount 方法参数个数
     * @param implementation 方法实现
     * @return 当前注册表，便于连续注册
     */
    public ExpressionMethodRegistry register(String name,
                                              int argumentCount,
                                              ExpressionMethod implementation) {
        validateName(name);
        if (argumentCount <= 0) {
            throw new IllegalArgumentException("方法参数个数必须大于 0");
        }
        Objects.requireNonNull(implementation, "方法实现不能为 null");
        RegisteredMethod previous = methods.putIfAbsent(
                name, new RegisteredMethod(argumentCount, implementation));
        if (previous != null) {
            throw new IllegalStateException("方法已经注册：" + name);
        }
        return this;
    }

    public RegisteredMethod find(String name) {
        return methods.get(name);
    }

    private static void validateName(String name) {
        if (name == null || !name.startsWith("m_") || name.length() == 2) {
            throw new IllegalArgumentException("方法名必须以 m_ 开头且不能为空");
        }
        for (int i = 2; i < name.length(); i++) {
            if (!Character.isLetterOrDigit(name.charAt(i))) {
                throw new IllegalArgumentException("方法名只能包含字母和数字：" + name);
            }
        }
    }

    /** 创建包含内置方法的注册表，调用方可以继续注册自己的方法。 */
    public static ExpressionMethodRegistry withDefaults() {
        return new ExpressionMethodRegistry()
                .register("m_random", 2, new RandomInstruction());
    }

    public static ExpressionMethodRegistry defaultRegistry() {
        return withDefaults();
    }

    public static final class RegisteredMethod {
        public final int argumentCount;
        public final ExpressionMethod implementation;

        RegisteredMethod(int argumentCount, ExpressionMethod implementation) {
            this.argumentCount = argumentCount;
            this.implementation = implementation;
        }
    }
}
