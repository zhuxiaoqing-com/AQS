package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;
import com.example.代码优化.design_mode.加减乘除解释器模式.stack.DoubleStack;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 表达式解析器的 main 测试入口。
 *
 * <p>不依赖 JUnit，直接运行 main 方法即可执行全部用例；任意用例失败都会抛出
 * AssertionError，方便在 IDE 或命令行中直接发现问题。</p>
 */
public final class ExpressionMainTest {
    private static int passedCount;

    private ExpressionMainTest() {
        // 测试类只通过 main 运行，不需要创建实例。
    }

    public static void main(String[] args) {
        // 基础四则运算和运算符优先级。
        run("整数加法", ExpressionMainTest::testIntegerAddition);
        run("四则运算优先级", ExpressionMainTest::testOperatorPrecedence);
        run("括号改变优先级", ExpressionMainTest::testParenthesesPriority);
        run("普通小数", ExpressionMainTest::testDecimal);
        run("整数结尾小数点", ExpressionMainTest::testTrailingDecimalPoint);

        // 括号、一元运算符和空白字符。
        run("嵌套半角括号", ExpressionMainTest::testNestedHalfWidthParentheses);
        run("一元正负号", ExpressionMainTest::testUnaryOperators);
        run("连续一元负号", ExpressionMainTest::testRepeatedUnaryMinus);
        run("表达式中的空白", ExpressionMainTest::testWhitespace);

        // 变量提供器、变量名收集和已编译表达式重复执行。
        run("ValueProvider 取值", ExpressionMainTest::testValueProvider);
        run("int、long、double 基础类型", ExpressionMainTest::testPrimitiveNumberTypes);
        run("INT、LONG 和 DOUBLE 入栈语义", ExpressionMainTest::testNumericModePush);
        run("变量名收集", ExpressionMainTest::testVariableNames);
        run("重复执行同一个表达式", ExpressionMainTest::testRepeatedEvaluation);
        run("v_ 前缀变量名", ExpressionMainTest::testLetterVariable);
        run("中文变量名", ExpressionMainTest::testChineseVariable);
        run("m_random 方法", ExpressionMainTest::testRandomFunction);
        run("注册自定义方法", ExpressionMainTest::testRegisteredMethod);

        // 比较、相等、布尔类型与短路控制流。
        run("取模运算", ExpressionMainTest::testModulo);
        run("比较和相等运算", ExpressionMainTest::testComparisonsAndEquality);
        run("布尔逻辑和优先级", ExpressionMainTest::testBooleanLogic);
        run("BOOLEAN 参数传入自定义方法", ExpressionMainTest::testBooleanMethodArgument);
        run("变量和方法的静态类型", ExpressionMainTest::testTypedVariablesAndMethods);
        run("类型错误在编译期拒绝", ExpressionMainTest::testTypeErrors);
        run("逻辑运算短路", ExpressionMainTest::testShortCircuit);
        run("按表达式类型选择求值 API", ExpressionMainTest::testTypedEvaluationApi);

        // 无效输入和运行时错误。
        run("空表达式", ExpressionMainTest::testBlankExpression);
        run("null 表达式", ExpressionMainTest::testNullExpression);
        run("表达式以运算符结尾", ExpressionMainTest::testTrailingOperator);
        run("操作数之间缺少运算符", ExpressionMainTest::testMissingOperator);
        run("运算符缺少操作数", ExpressionMainTest::testOperatorMissingOperand);
        run("左括号没有闭合", ExpressionMainTest::testMissingRightParenthesis);
        run("右括号没有匹配", ExpressionMainTest::testMissingLeftParenthesis);
        run("空括号", ExpressionMainTest::testEmptyParentheses);
        run("非法小数格式", ExpressionMainTest::testMalformedDecimal);
        run("不支持的字符", ExpressionMainTest::testUnsupportedCharacter);
        run("三目运算暂不支持", ExpressionMainTest::testUnsupportedTernary);
        run("不支持科学计数法", ExpressionMainTest::testScientificNotation);
        run("不支持 .5 格式", ExpressionMainTest::testLeadingDecimalPoint);
        run("语法错误位置", ExpressionMainTest::testParseErrorPosition);
        run("null ValueProvider", ExpressionMainTest::testNullValueProvider);
        run("缺少变量值", ExpressionMainTest::testMissingVariable);
        run("除数为零", ExpressionMainTest::testDivisionByZero);
        run("变量集合不可修改", ExpressionMainTest::testVariableSetReadOnly);

        // 原生 double 栈本身的基本行为和扩容行为。
        run("DoubleStack 后进先出", ExpressionMainTest::testDoubleStackLifo);
        run("DoubleStack 自动扩容", ExpressionMainTest::testDoubleStackResize);
        run("DoubleStack 空栈", ExpressionMainTest::testDoubleStackEmpty);

        System.out.println("全部测试通过，共 " + passedCount + " 个用例。");
    }

    private static void testIntegerAddition() {
        assertDouble(5.0, evaluate("2 + 3", values()), "整数加法结果错误");
    }

    private static void testOperatorPrecedence() {
        assertDouble(14.0, evaluate("2 + 3 * 4", values()), "乘法应先于加法");
        assertDouble(8.0, evaluate("10 - 6 / 3", values()), "除法应先于减法");
    }

    private static void testParenthesesPriority() {
        assertDouble(20.0, evaluate("(2 + 3) * 4", values()), "括号优先级错误");
    }

    private static void testDecimal() {
        assertDouble(3.75, evaluate("1.5 + 2.25", values()), "小数计算错误");
    }

    private static void testTrailingDecimalPoint() {
        assertDouble(3.0, evaluate("1. + 2", values()), "整数结尾小数点解析错误");
    }

    private static void testNestedHalfWidthParentheses() {
        assertDouble(6.0, evaluate("(v_a + (v_b + 2))", values("v_a", 1.0, "v_b", 3.0)),
                "嵌套半角括号计算错误");
    }

    private static void testUnaryOperators() {
        assertDouble(-6.0, evaluate("-2 * 3", values()), "一元负号优先级错误");
        assertDouble(2.0, evaluate("+2", values()), "一元正号计算错误");
        assertDouble(-3.0, evaluate("-(1 + 2)", values()), "括号前一元负号错误");
        assertDouble(-6.0, evaluate("2 * -3", values()), "乘法后的负号错误");
    }

    private static void testRepeatedUnaryMinus() {
        assertDouble(2.0, evaluate("--2", values()), "连续一元负号计算错误");
    }

    private static void testWhitespace() {
        assertDouble(7.0, evaluate(" 1 +\t2 *\n3 ", values()), "空白处理错误");
    }

    private static void testValueProvider() {
        ValueProvider provider = name -> {
            if ("v_x".equals(name)) {
                return 10.0;
            }
            if ("v_y".equals(name)) {
                return 5.0;
            }
            if ("v_d".equals(name)) {
                return 20.0;
            }
            return null;
        };
        double result = SimpleExpression.compile("(v_x + 2 * 2) + v_y + (v_d * 3 / 10)")
                .evaluate(provider);
        assertDouble(25.0, result, "ValueProvider 计算结果错误");
    }

    private static void testPrimitiveNumberTypes() {
        SimpleExpression.CompiledExpression expression = SimpleExpression.compile("5 / 2");
        if (expression.evaluateInt(name -> 0) != 2) {
            throw new AssertionError("int 除法结果错误");
        }
        if (expression.evaluateLong(name -> 0L) != 2L) {
            throw new AssertionError("long 除法结果错误");
        }
        assertDouble(2.5, expression.evaluateDouble(name -> 0.0),
                "double 除法结果错误");

        if (SimpleExpression.compile("m_random(5, 5)").evaluateInt(name -> 0) != 5) {
            throw new AssertionError("int m_random 结果错误");
        }
        if (SimpleExpression.compile("m_random(5, 5)").evaluateLong(name -> 0L) != 5L) {
            throw new AssertionError("long m_random 结果错误");
        }
        assertDouble(5.0,
                SimpleExpression.compile("m_random(5, 5)").evaluateDouble(name -> 0.0),
                "double m_random 结果错误");

        SimpleExpression.CompiledExpression decimal = SimpleExpression.compile("5.9");
        if (decimal.evaluateInt(name -> 0) != 5) {
            throw new AssertionError("小数转换 int 结果错误");
        }
        if (decimal.evaluateLong(name -> 0L) != 5L) {
            throw new AssertionError("小数转换 long 结果错误");
        }
        assertDouble(5.9, decimal.evaluateDouble(name -> 0.0),
                "小数保留 double 结果错误");

        SimpleExpression.CompiledExpression overflow =
                SimpleExpression.compile("9999999999999999999999999999999999999999999999999");
        if (overflow.evaluateInt(name -> 0) != Integer.MAX_VALUE) {
            throw new AssertionError("超出 int 范围时应遵循 Java 转换规则");
        }
        if (overflow.evaluateLong(name -> 0L) != Long.MAX_VALUE) {
            throw new AssertionError("超出 long 范围时应遵循 Java 转换规则");
        }

        if (SimpleExpression.compile("2147483647 + 1").evaluateInt(name -> 0)
                != Integer.MAX_VALUE) {
            throw new AssertionError("INT 入栈应遵循 double 转 int 的 Java 转换规则");
        }
        if (SimpleExpression.compile("9223372036854775807 + 1").evaluateLong(name -> 0L)
                != Long.MAX_VALUE) {
            throw new AssertionError("long 兼容接口应遵循 double 转 long 的 Java 转换规则");
        }
        if (SimpleExpression.compile("v_x / -1").evaluateInt(name -> Integer.MIN_VALUE)
                != Integer.MAX_VALUE) {
            throw new AssertionError("INT 模式应在除法结果入栈时转换为 int");
        }
    }

    private static void testNumericModePush() {
        if (SimpleExpression.compile("5 / 2").evaluateInt(name -> 0) != 2) {
            throw new AssertionError("INT 的 5 / 2 结果错误");
        }
        assertDouble(2.5, SimpleExpression.compile("5 / 2").evaluateDouble(name -> 0.0),
                "DOUBLE 的 5 / 2 结果错误");

        if (SimpleExpression.compile("5 / 2 * 2").evaluateInt(name -> 0) != 4) {
            throw new AssertionError("INT 应在除法结果重新入栈时舍去小数");
        }
        if (SimpleExpression.compile("5 / 2 * 2").evaluateLong(name -> 0L) != 4L) {
            throw new AssertionError("long 入口应在每一步整数结果入栈时舍去小数");
        }
        if (SimpleExpression.compile("2147483647 + 2 - 1").evaluateLong(name -> 0L)
                != 2147483648L) {
            throw new AssertionError("LONG 模式应保留超出 int 范围但仍在 long 范围内的中间值");
        }
        assertDouble(5.0,
                SimpleExpression.compile("5 / 2 * 2").evaluateDouble(name -> 0.0),
                "DOUBLE 的连续除乘结果错误");

        if (SimpleExpression.compile("2147483647 + 2 - 1").evaluateInt(name -> 0)
                != Integer.MAX_VALUE - 1) {
            throw new AssertionError("INT 模式应在每一步入栈时转换为 int");
        }

        if (SimpleExpression.compile("5.9 * 2").evaluateInt(name -> 0) != 10) {
            throw new AssertionError("INT 的小数字面量应在入栈时转换");
        }
        assertDouble(11.8, SimpleExpression.compile("5.9 * 2").evaluateDouble(name -> 0.0),
                "DOUBLE 的小数乘法结果错误");

        if (SimpleExpression.compile("10 / 3 * 3").evaluateInt(name -> 0) != 9) {
            throw new AssertionError("INT 的每步除法结果应在入栈时转换");
        }
        assertDouble(10.0,
                SimpleExpression.compile("10 / 3 * 3").evaluateDouble(name -> 0.0),
                "DOUBLE 的连续除乘结果错误");

        if (SimpleExpression.compile("(5.9 + 2.8) * 2.5").evaluateInt(name -> 0) != 14) {
            throw new AssertionError("INT 的多个计算步骤应各自在入栈时转换");
        }
        assertDouble(21.75,
                SimpleExpression.compile("(5.9 + 2.8) * 2.5").evaluateDouble(name -> 0.0),
                "DOUBLE 的括号小数计算结果错误");

        EvaluationContext intContext = new EvaluationContext(
                1, NumericMode.INT, name -> 5.9);
        new VariableInstruction("v_a").execute(intContext);
        assertDouble(5.0, intContext.pop(), "INT 变量值没有经过上下文转换");

        EvaluationContext doubleContext = new EvaluationContext(
                1, NumericMode.DOUBLE, name -> 5.9);
        new VariableInstruction("v_a").execute(doubleContext);
        assertDouble(5.9, doubleContext.pop(), "DOUBLE 变量值不应丢失小数");

        ExpressionMethodRegistry methods = new ExpressionMethodRegistry()
                .register("m_fraction", 1, (values, arguments) -> arguments[0] + 0.9);
        SimpleExpression.CompiledExpression methodExpression =
                SimpleExpression.compile("m_fraction(5.9) * 2", methods);
        if (methodExpression.evaluateInt(name -> 0) != 10) {
            throw new AssertionError("INT 方法返回值应通过上下文转换后入栈");
        }
        assertDouble(13.6, methodExpression.evaluateDouble(name -> 0.0),
                "DOUBLE 方法返回值不应丢失小数");

        SimpleExpression.CompiledExpression variableExpression =
                SimpleExpression.compile("v_a / v_b * v_b");
        if (variableExpression.evaluateInt(name -> "v_a".equals(name) ? 5 : 2) != 4) {
            throw new AssertionError("INT 变量参与连续除乘的结果错误");
        }
        assertDouble(5.9,
                variableExpression.evaluateDouble(name -> "v_a".equals(name) ? 5.9 : 2.8),
                "DOUBLE 变量参与连续除乘的结果错误");
    }

    private static void testVariableNames() {
        SimpleExpression.CompiledExpression expression =
                SimpleExpression.compile("v_x + v_y + v_x + v_damage2");
        assertEquals("[v_x, v_y, v_damage2]", expression.getVariables().toString(),
                "变量名或变量顺序错误");
    }

    private static void testRepeatedEvaluation() {
        final double[] values = {1.0, 2.0};
        SimpleExpression.CompiledExpression expression =
                SimpleExpression.compile("v_x * 2 + v_y");
        ValueProvider provider = name -> "v_x".equals(name) ? values[0] : values[1];

        assertDouble(4.0, expression.evaluate(provider), "第一次计算错误");
        values[0] = 10.0;
        values[1] = 5.0;
        assertDouble(25.0, expression.evaluate(provider), "重复计算错误");
    }

    private static void testLetterVariable() {
        assertDouble(12.0, evaluate("v_attack2 + 2", values("v_attack2", 10.0)),
                "字母开头变量解析错误");
    }

    private static void testChineseVariable() {
        assertDouble(12.0, evaluate("v_伤害 + 2", values("v_伤害", 10.0)),
                "中文变量解析错误");
    }

    private static void testRandomFunction() {
        double result = evaluate("m_random(10, 20)", values());
        if (result < 10.0 || result >= 20.0) {
            throw new AssertionError("m_random 结果超出范围：" + result);
        }
        assertDouble(5.0, evaluate("m_random(5, 5)", values()),
                "m_random 上下界相等时应返回该值");
        assertDouble(12.0, evaluate("m_random(v_value + 1, v_value + 1)", values("v_value", 11.0)),
                "m_random 参数应支持表达式");
        assertThrows(IllegalArgumentException.class,
                () -> evaluate("m_random(2, 1)", values()), "m_random 的 min 大于 max 应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("m_random(1)"), "m_random 参数个数错误应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("random(1, 2)"), "没有 m_ 前缀的方法应报错");
    }

    private static void testRegisteredMethod() {
        ExpressionMethodRegistry methods = new ExpressionMethodRegistry()
                .register("m_square", 1,
                        (values, arguments) -> arguments[0] * arguments[0]);

        SimpleExpression.CompiledExpression expression =
                SimpleExpression.compile("m_square(3) + m_square(2)", methods);
        if (expression.evaluateInt(name -> 0) != 13) {
            throw new AssertionError("注册的 int 方法执行结果错误");
        }
        assertDouble(13.0, expression.evaluateDouble(name -> 0.0),
                "注册的 double 方法执行结果错误");
    }

    private static void testModulo() {
        assertDouble(1.0, evaluate("10 % 3", values()), "取模结果错误");
        assertDouble(-1.0, evaluate("-10 % 3", values()), "取模符号应遵循 Java double 语义");
        assertThrows(ArithmeticException.class,
                () -> evaluate("10 % 0", values()), "模数为零应报错");
    }

    private static void testComparisonsAndEquality() {
        assertBoolean(true, SimpleExpression.compile("1 < 2").evaluateBoolean(name -> 0.0), "< 错误");
        assertBoolean(true, SimpleExpression.compile("1 <= 1").evaluateBoolean(name -> 0.0), "<= 错误");
        assertBoolean(true, SimpleExpression.compile("2 > 1").evaluateBoolean(name -> 0.0), "> 错误");
        assertBoolean(true, SimpleExpression.compile("2 >= 2").evaluateBoolean(name -> 0.0), ">= 错误");
        assertBoolean(true, SimpleExpression.compile("1 == 1").evaluateBoolean(name -> 0.0), "数值 == 错误");
        assertBoolean(true, SimpleExpression.compile("1 != 2").evaluateBoolean(name -> 0.0), "数值 != 错误");
        assertBoolean(true, SimpleExpression.compile("true == true").evaluateBoolean(name -> 0.0), "布尔 == 错误");
        assertBoolean(true, SimpleExpression.compile("true != false").evaluateBoolean(name -> 0.0), "布尔 != 错误");
        assertBoolean(false, SimpleExpression.compile("0.1 + 0.2 == 0.3")
                .evaluateBoolean(name -> 0.0), "== 应遵循 Java 原始 double 语义");
    }

    private static void testBooleanLogic() {
        assertBoolean(true, SimpleExpression.compile("true && true").evaluateBoolean(name -> 0.0), "true && true 错误");
        assertBoolean(false, SimpleExpression.compile("true && false").evaluateBoolean(name -> 0.0), "true && false 错误");
        assertBoolean(true, SimpleExpression.compile("false || true").evaluateBoolean(name -> 0.0), "false || true 错误");
        assertBoolean(false, SimpleExpression.compile("false || false").evaluateBoolean(name -> 0.0), "false || false 错误");
        assertBoolean(false, SimpleExpression.compile("!true").evaluateBoolean(name -> 0.0), "!true 错误");
        assertBoolean(true, SimpleExpression.compile("!!true").evaluateBoolean(name -> 0.0), "!!true 错误");
        assertBoolean(true, SimpleExpression.compile("1 + 2 > 2 && 5 < 10")
                .evaluateBoolean(name -> 0.0), "混合表达式优先级错误");
        assertBoolean(true, SimpleExpression.compile("!(1 > 2) && (1 > 2 || 3 < 4)")
                .evaluateBoolean(name -> 0.0), "括号和逻辑优先级错误");
        assertBoolean(true, SimpleExpression.compile("true || false && false")
                .evaluateBoolean(name -> 0.0), "&& 应先于 ||");

        EvaluationContext context = new EvaluationContext(
                1, NumericMode.DOUBLE, name -> 0.0);
        context.pushBoolean(true);
        assertDouble(1.0, context.pop(), "pushBoolean 应在 double 栈写入 1.0");
        context.pushBoolean(false);
        assertBoolean(false, context.popBoolean(), "popBoolean 应读取 false");
    }

    private static void testTypedVariablesAndMethods() {
        Map<String, ExpressionValueType> variableTypes = new LinkedHashMap<>();
        variableTypes.put("v_attack", ExpressionValueType.NUMBER);
        variableTypes.put("v_dead", ExpressionValueType.BOOLEAN);
        variableTypes.put("v_hp", ExpressionValueType.NUMBER);
        ExpressionMethodRegistry methods = new ExpressionMethodRegistry()
                .registerBoolean("m_isDead",
                        (values, arguments) -> arguments[0] == 1.0,
                        ExpressionValueType.NUMBER)
                .registerBoolean("m_isFalse",
                        (values, arguments) -> arguments[0] == 0.0,
                        ExpressionValueType.BOOLEAN);

        SimpleExpression.CompiledExpression expression = SimpleExpression.compile(
                "v_attack * 2 > 100 && !v_dead && v_hp > 0",
                methods,
                variableTypes::get);
        assertBoolean(true, expression.evaluateBoolean(name -> {
            switch (name) {
                case "v_attack": return 60.0;
                case "v_dead": return 0.0;
                case "v_hp": return 10.0;
                default: throw new IllegalArgumentException("未知变量：" + name);
            }
        }), "类型化变量表达式结果错误");

        SimpleExpression.CompiledExpression methodExpression = SimpleExpression.compile(
                "m_isDead(1) && v_hp > 0", methods, variableTypes::get);
        assertBoolean(true, methodExpression.evaluateBoolean(name -> 10.0), "BOOLEAN 方法返回类型错误");

        SimpleExpression.CompiledExpression booleanArgument = SimpleExpression.compile(
                "m_isFalse(v_dead)", methods, variableTypes::get);
        assertBoolean(true, booleanArgument.evaluateBoolean(name -> 0.0),
                "BOOLEAN 方法参数类型校验或执行错误");
    }

    private static void testBooleanMethodArgument() {
        ExpressionMethodRegistry methods = new ExpressionMethodRegistry()
                .registerBoolean("m_canAttack",
                        (values, arguments) -> arguments[0] == 1.0
                                && arguments[1] > 0.0
                                && arguments[2] == 1.0,
                        ExpressionValueType.BOOLEAN,
                        ExpressionValueType.NUMBER,
                        ExpressionValueType.BOOLEAN);

        assertBoolean(true,
                SimpleExpression.compile("m_canAttack(true, 100, true)", methods)
                        .evaluateBoolean(name -> 0.0),
                "BOOLEAN、NUMBER、BOOLEAN 参数应按声明顺序传入方法");
        assertBoolean(false,
                SimpleExpression.compile("m_canAttack(false, 100, true)", methods)
                        .evaluateBoolean(name -> 0.0),
                "false 应以 0.0 传入方法");
        assertBoolean(false,
                SimpleExpression.compile("m_canAttack(true, 0, true)", methods)
                        .evaluateBoolean(name -> 0.0),
                "NUMBER 参数应保持在第二个位置");
    }

    private static void testTypeErrors() {
        ExpressionMethodRegistry methods = new ExpressionMethodRegistry()
                .register("m_number", 1, (values, arguments) -> arguments[0])
                .registerBoolean("m_boolean",
                        (values, arguments) -> true, ExpressionValueType.NUMBER);
        assertThrows(ExpressionParseException.class, () -> SimpleExpression.compile("true + 1"),
                "BOOLEAN 不能参与加法");
        assertThrows(ExpressionParseException.class, () -> SimpleExpression.compile("10 && 20"),
                "NUMBER 不能参与逻辑与");
        assertThrows(ExpressionParseException.class, () -> SimpleExpression.compile("!100"),
                "NUMBER 不能用于逻辑非");
        assertThrows(ExpressionParseException.class, () -> SimpleExpression.compile("true > false"),
                "BOOLEAN 不能参与大小比较");
        assertThrows(ExpressionParseException.class, () -> SimpleExpression.compile("1 == true"),
                "NUMBER 和 BOOLEAN 不能比较相等");
        assertThrows(ExpressionParseException.class, () -> SimpleExpression.compile("m_number(true)", methods),
                "方法参数类型应在编译期校验");
        assertThrows(ExpressionParseException.class, () -> SimpleExpression.compile("m_boolean(1) + 2", methods),
                "BOOLEAN 方法返回值不能参与加法");

        Map<String, ExpressionValueType> types = new LinkedHashMap<>();
        types.put("v_dead", ExpressionValueType.BOOLEAN);
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("v_dead + 100", types::get),
                "BOOLEAN 变量不能参与加法");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("v_unknown + 1", types::get),
                "缺少变量类型应在编译期报错");
    }

    private static void testShortCircuit() {
        final int[] calls = {0};
        ExpressionMethodRegistry methods = new ExpressionMethodRegistry()
                .registerBoolean("m_throw", (values, arguments) -> {
                    calls[0]++;
                    throw new IllegalStateException("方法不应执行");
                });

        assertBoolean(false, SimpleExpression.compile("false && m_throw()", methods)
                .evaluateBoolean(name -> 0.0), "false && 右侧应短路");
        assertBoolean(true, SimpleExpression.compile("true || m_throw()", methods)
                .evaluateBoolean(name -> 0.0), "true || 右侧应短路");
        assertBoolean(false, SimpleExpression.compile("0 != 0 && 100 / 0 > 1")
                .evaluateBoolean(name -> 0.0), "&& 短路应跳过除零表达式");
        assertBoolean(true, SimpleExpression.compile("1 == 1 || 100 / 0 > 1")
                .evaluateBoolean(name -> 0.0), "|| 短路应跳过除零表达式");
        assertEquals("0", String.valueOf(calls[0]), "短路时不应调用右侧方法");
    }

    private static void testTypedEvaluationApi() {
        SimpleExpression.CompiledExpression number = SimpleExpression.compile("1 + 2");
        SimpleExpression.CompiledExpression bool = SimpleExpression.compile("1 < 2");
        assertDouble(3.0, number.evaluateNumber(name -> 0.0), "evaluateNumber 结果错误");
        if (number.getType() != ExpressionValueType.NUMBER
                || bool.getType() != ExpressionValueType.BOOLEAN) {
            throw new AssertionError("表达式返回类型错误");
        }
        assertThrows(IllegalStateException.class, () -> number.evaluateBoolean(name -> 0.0),
                "NUMBER 表达式不能调用 evaluateBoolean");
        assertThrows(IllegalStateException.class, () -> bool.evaluateNumber(name -> 0.0),
                "BOOLEAN 表达式不能调用 evaluateNumber");
    }

    private static void testBlankExpression() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile(""), "空表达式应报错");
    }

    private static void testNullExpression() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile(null), "null 表达式应报错");
    }

    private static void testTrailingOperator() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("v_x +"), "结尾运算符应报错");
    }

    private static void testMissingOperator() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("1 2"), "操作数之间缺少运算符应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("v_a(v_b)"), "变量和左括号之间缺少运算符应报错");
    }

    private static void testOperatorMissingOperand() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("*1"), "表达式开头的乘号应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("1 + * 2"), "连续二元运算符应报错");
    }

    private static void testMissingRightParenthesis() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("(1 + 2"), "左括号没有闭合应报错");
    }

    private static void testMissingLeftParenthesis() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("1 + 2)"), "右括号没有匹配应报错");
    }

    private static void testEmptyParentheses() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("()"), "空括号应报错");
    }

    private static void testMalformedDecimal() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("1..2"), "连续小数点应报错");
    }

    private static void testUnsupportedCharacter() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("1 @ 2"), "不支持的字符应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("（1 + 2）"), "全角括号应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("_attack + 2"), "下划线变量应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("attack + 2"), "无 v_ 前缀变量应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("v_ + 2"), "变量前缀后缺少名称应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("true && 1"), "单独的逻辑类型不匹配应编译失败");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("true & false"), "单独的 & 应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("true | false"), "单独的 | 应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("1 = 1"), "单独的 = 应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("&& true"), "表达式开头的 && 应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("true ||"), "结尾的 || 应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("1 <"), "结尾的比较运算符应报错");
    }

    private static void testUnsupportedTernary() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("v_a ? v_b : v_c"), "三目运算暂不支持");
    }

    private static void testScientificNotation() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("2.5e-2"), "科学计数法应报错");
    }

    private static void testLeadingDecimalPoint() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile(".5"), ".5 格式应报错");
    }

    private static void testParseErrorPosition() {
        try {
            SimpleExpression.compile("1 +");
            throw new AssertionError("语法错误应抛出异常");
        } catch (ExpressionParseException exception) {
            assertEquals("3", String.valueOf(exception.getPosition()), "错误位置记录错误");
        }
    }

    private static void testNullValueProvider() {
        SimpleExpression.CompiledExpression expression = SimpleExpression.compile("1 + 2");
        assertThrows(IllegalArgumentException.class,
                () -> expression.evaluate(null), "null ValueProvider 应报错");
    }

    private static void testMissingVariable() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluate("v_x + 1", values()), "缺少变量值应报错");
    }

    private static void testDivisionByZero() {
        assertThrows(ArithmeticException.class,
                () -> evaluate("1 / 0", values()), "除零应报错");
    }

    private static void testVariableSetReadOnly() {
        SimpleExpression.CompiledExpression expression = SimpleExpression.compile("v_x + v_y");
        assertThrows(UnsupportedOperationException.class,
                () -> expression.getVariables().add("v_z"), "变量集合不应该允许修改");
    }

    private static void testDoubleStackLifo() {
        DoubleStack stack = new DoubleStack(1);
        stack.push(1.0);
        stack.push(2.0);
        assertDouble(2.0, stack.pop(), "栈顶元素错误");
        assertDouble(1.0, stack.pop(), "后进先出顺序错误");
        assertEquals("0", String.valueOf(stack.size()), "栈清空后大小错误");
    }

    private static void testDoubleStackResize() {
        DoubleStack stack = new DoubleStack(1);
        for (int i = 0; i < 32; i++) {
            // 连续压入超过初始容量，验证原生栈可以自动扩容。
            stack.push(i);
        }
        for (int i = 31; i >= 0; i--) {
            assertDouble(i, stack.pop(), "自动扩容后数据顺序错误");
        }
    }

    private static void testDoubleStackEmpty() {
        DoubleStack stack = new DoubleStack(1);
        assertThrows(IllegalStateException.class, stack::pop, "空栈弹出应报错");
        assertEquals("0", String.valueOf(stack.size()), "空栈大小错误");
    }

    private static double evaluate(String source, Map<String, Double> values) {
        // 测试辅助方法：把 Map 适配成 ValueProvider，生产代码仍然不需要 Map。
        ValueProvider provider = values::get;
        return SimpleExpression.compile(source).evaluate(provider);
    }

    private static Map<String, Double> values(Object... entries) {
        Map<String, Double> values = new LinkedHashMap<>();
        for (int i = 0; i < entries.length; i += 2) {
            values.put((String) entries[i], ((Number) entries[i + 1]).doubleValue());
        }
        return values;
    }

    private static Map<String, Double> values() {
        return new LinkedHashMap<>();
    }

    private static void run(String name, Runnable test) {
        try {
            test.run();
            passedCount++;
            System.out.println("[PASS] " + name);
        } catch (Throwable cause) {
            AssertionError error = new AssertionError("[FAIL] " + name);
            error.initCause(cause);
            throw error;
        }
    }

    private static void assertDouble(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.000000001d) {
            throw new AssertionError(message + "，期望：" + expected + "，实际：" + actual);
        }
    }

    private static void assertBoolean(boolean expected, boolean actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + "，期望：" + expected + "，实际：" + actual);
        }
    }

    private static void assertEquals(String expected, String actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + "，期望：" + expected + "，实际：" + actual);
        }
    }

    private static void assertThrows(Class<? extends Throwable> expectedType,
                                     Runnable action,
                                     String message) {
        try {
            action.run();
        } catch (Throwable actual) {
            if (expectedType.isInstance(actual)) {
                return;
            }
            throw new AssertionError(message + "，异常类型错误：" + actual.getClass().getName());
        }
        throw new AssertionError(message + "，没有抛出 " + expectedType.getSimpleName());
    }
}
