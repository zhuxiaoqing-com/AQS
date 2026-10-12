package com.example.代码优化.design_mode.加减乘除解释器模式.method;

import com.example.代码优化.design_mode.加减乘除解释器模式.ExpressionValueType;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/** 表达式方法注册表。注册完成后可安全地供多个线程编译表达式。 */
public final class ExpressionMethodRegistry {
    private final Map<String, RegisteredMethod> methods = new ConcurrentHashMap<>();

    /** 注册参数和返回类型均为 NUMBER 的方法。 */
    public ExpressionMethodRegistry register(String name,
                                              int argumentCount,
                                              ExpressionMethod implementation) {
        if (argumentCount < 0) {
            throw new IllegalArgumentException("方法参数个数不能小于 0");
        }
        ExpressionValueType[] parameterTypes = new ExpressionValueType[argumentCount];
        Arrays.fill(parameterTypes, ExpressionValueType.NUMBER);
        return register(name, parameterTypes, implementation);
    }

    /** 注册 NUMBER 返回值和显式参数类型的方法。 */
    public ExpressionMethodRegistry register(String name,
                                              ExpressionValueType[] parameterTypes,
                                              ExpressionMethod implementation) {
        return registerInternal(name, parameterTypes, ExpressionValueType.NUMBER,
                implementation, null);
    }

    /** 注册返回 Java boolean 的方法，参数类型可包含 NUMBER 和 BOOLEAN。 */
    public ExpressionMethodRegistry registerBoolean(String name,
                                                     ExpressionValueType[] parameterTypes,
                                                     BooleanExpressionMethod implementation) {
        return registerInternal(name, parameterTypes, ExpressionValueType.BOOLEAN,
                null, implementation);
    }

    /** 便于注册返回 Java boolean 的方法。 */
    public ExpressionMethodRegistry registerBoolean(String name,
                                                     BooleanExpressionMethod implementation,
                                                     ExpressionValueType... parameterTypes) {
        return registerBoolean(name, parameterTypes, implementation);
    }

    private ExpressionMethodRegistry registerInternal(String name,
                                                       ExpressionValueType[] parameterTypes,
                                                       ExpressionValueType returnType,
                                                       ExpressionMethod implementation,
                                                       BooleanExpressionMethod booleanImplementation) {
        validateName(name);
        Objects.requireNonNull(parameterTypes, "方法参数类型不能为 null");
        ExpressionValueType[] signature = parameterTypes.clone();
        for (ExpressionValueType parameterType : signature) {
            Objects.requireNonNull(parameterType, "方法参数类型不能为 null");
        }
        if (returnType == ExpressionValueType.NUMBER) {
            Objects.requireNonNull(implementation, "数值方法实现不能为 null");
        } else {
            Objects.requireNonNull(booleanImplementation, "布尔方法实现不能为 null");
        }

        RegisteredMethod previous = methods.putIfAbsent(name,
                new RegisteredMethod(signature, returnType, implementation, booleanImplementation));
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
        public final ExpressionValueType returnType;
        public final ExpressionMethod implementation;
        public final BooleanExpressionMethod booleanImplementation;
        private final ExpressionValueType[] parameterTypes;

        RegisteredMethod(ExpressionValueType[] parameterTypes,
                         ExpressionValueType returnType,
                         ExpressionMethod implementation,
                         BooleanExpressionMethod booleanImplementation) {
            this.argumentCount = parameterTypes.length;
            this.parameterTypes = parameterTypes;
            this.returnType = returnType;
            this.implementation = implementation;
            this.booleanImplementation = booleanImplementation;
        }

        public ExpressionValueType getParameterType(int index) {
            return parameterTypes[index];
        }

        public ExpressionValueType[] getParameterTypes() {
            return parameterTypes.clone();
        }
    }
}
