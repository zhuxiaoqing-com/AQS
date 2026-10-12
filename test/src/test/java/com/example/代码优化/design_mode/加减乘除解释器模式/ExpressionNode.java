package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;

import java.util.List;

/** 仅供编译阶段使用的类型化 AST 节点。 */
interface ExpressionNode {
    ExpressionValueType getType();
}

/** 字面量节点保存表达式中的常量值和类型，编译时会直接生成对应的常量指令。 */
final class LiteralNode implements ExpressionNode {
    final double value;
    final ExpressionValueType type;

    LiteralNode(double value, ExpressionValueType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public ExpressionValueType getType() {
        return type;
    }
}

/** 变量节点保存变量名及解析阶段确定的类型，编译时据此生成变量读取指令。 */
final class VariableNode implements ExpressionNode {
    final String name;
    final ExpressionValueType type;

    VariableNode(String name, ExpressionValueType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ExpressionValueType getType() {
        return type;
    }
}

/** 一元运算节点保存一个运算符和它唯一的操作数，以及运算结果类型。 */
final class UnaryNode implements ExpressionNode {
    final UnaryOperator operator;
    final ExpressionNode operand;
    final ExpressionValueType type;

    UnaryNode(UnaryOperator operator, ExpressionNode operand, ExpressionValueType type) {
        this.operator = operator;
        this.operand = operand;
        this.type = type;
    }

    @Override
    public ExpressionValueType getType() {
        return type;
    }
}

/** 二元运算节点保存左右子表达式、运算符和结果类型；AND/OR 的短路行为由编译器生成跳转指令实现。 */
final class BinaryNode implements ExpressionNode {
    final ExpressionNode left;
    final BinaryOperator operator;
    final ExpressionNode right;
    final ExpressionValueType type;

    BinaryNode(ExpressionNode left,
               BinaryOperator operator,
               ExpressionNode right,
               ExpressionValueType type) {
        this.left = left;
        this.operator = operator;
        this.right = right;
        this.type = type;
    }

    @Override
    public ExpressionValueType getType() {
        return type;
    }
}

/** 方法调用节点保存已解析的方法和参数子表达式，返回类型取自方法注册信息。 */
final class MethodCallNode implements ExpressionNode {
    final ExpressionMethodRegistry.RegisteredMethod method;
    final List<ExpressionNode> arguments;
    final ExpressionValueType type;

    MethodCallNode(ExpressionMethodRegistry.RegisteredMethod method,
                   List<ExpressionNode> arguments) {
        this.method = method;
        this.arguments = arguments;
        this.type = method.returnType;
    }

    @Override
    public ExpressionValueType getType() {
        return type;
    }
}
