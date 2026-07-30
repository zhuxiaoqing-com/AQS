package com.example.代码优化.design_mode.加减乘除解释器模式;

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
        run("嵌套全角括号", ExpressionMainTest::testNestedFullWidthParentheses);
        run("半角全角括号混合", ExpressionMainTest::testMixedParentheses);
        run("一元正负号", ExpressionMainTest::testUnaryOperators);
        run("连续一元负号", ExpressionMainTest::testRepeatedUnaryMinus);
        run("表达式中的空白", ExpressionMainTest::testWhitespace);

        // 变量提供器、变量名收集和已编译表达式重复执行。
        run("ValueProvider 取值", ExpressionMainTest::testValueProvider);
        run("变量名收集", ExpressionMainTest::testVariableNames);
        run("重复执行同一个表达式", ExpressionMainTest::testRepeatedEvaluation);
        run("下划线变量名", ExpressionMainTest::testUnderscoreVariable);
        run("中文变量名", ExpressionMainTest::testChineseVariable);

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
        assertDouble(6.0, evaluate("(a + (b + 2))", values("a", 1.0, "b", 3.0)),
                "嵌套半角括号计算错误");
    }

    private static void testNestedFullWidthParentheses() {
        assertDouble(6.0, evaluate("（a + （b + 2））", values("a", 1.0, "b", 3.0)),
                "嵌套全角括号计算错误");
    }

    private static void testMixedParentheses() {
        assertDouble(6.0, evaluate("(a + （b + 2）)", values("a", 1.0, "b", 3.0)),
                "混合括号计算错误");
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
            if ("x".equals(name)) {
                return 10.0;
            }
            if ("y".equals(name)) {
                return 5.0;
            }
            if ("d".equals(name)) {
                return 20.0;
            }
            return null;
        };
        double result = SimpleExpression.compile("(x + 2 * 2) + y + (d * 3 / 10)")
                .evaluate(provider);
        assertDouble(25.0, result, "ValueProvider 计算结果错误");
    }

    private static void testVariableNames() {
        SimpleExpression.CompiledExpression expression =
                SimpleExpression.compile("x + y + x + _damage");
        assertEquals("[x, y, _damage]", expression.getVariables().toString(),
                "变量名或变量顺序错误");
    }

    private static void testRepeatedEvaluation() {
        final double[] values = {1.0, 2.0};
        SimpleExpression.CompiledExpression expression =
                SimpleExpression.compile("x * 2 + y");
        ValueProvider provider = name -> "x".equals(name) ? values[0] : values[1];

        assertDouble(4.0, expression.evaluate(provider), "第一次计算错误");
        values[0] = 10.0;
        values[1] = 5.0;
        assertDouble(25.0, expression.evaluate(provider), "重复计算错误");
    }

    private static void testUnderscoreVariable() {
        assertDouble(12.0, evaluate("_attack + 2", values("_attack", 10.0)),
                "下划线变量解析错误");
    }

    private static void testChineseVariable() {
        assertDouble(12.0, evaluate("伤害 + 2", values("伤害", 10.0)),
                "中文变量解析错误");
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
                () -> SimpleExpression.compile("x +"), "结尾运算符应报错");
    }

    private static void testMissingOperator() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("1 2"), "操作数之间缺少运算符应报错");
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("a(b)"), "变量和左括号之间缺少运算符应报错");
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
    }

    private static void testUnsupportedTernary() {
        assertThrows(ExpressionParseException.class,
                () -> SimpleExpression.compile("a ? b : c"), "三目运算暂不支持");
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
                () -> evaluate("x + 1", values()), "缺少变量值应报错");
    }

    private static void testDivisionByZero() {
        assertThrows(ArithmeticException.class,
                () -> evaluate("1 / 0", values()), "除零应报错");
    }

    private static void testVariableSetReadOnly() {
        SimpleExpression.CompiledExpression expression = SimpleExpression.compile("x + y");
        assertThrows(UnsupportedOperationException.class,
                () -> expression.getVariables().add("z"), "变量集合不应该允许修改");
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
