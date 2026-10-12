package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** 递归下降语法分析器；只构造 AST 并在构造节点时完成静态类型检查。 */
public final class ExpressionParser {
    private final Lexer lexer;
    private final ExpressionMethodRegistry methods;
    private final VariableTypeProvider variableTypes;
    private final Set<String> variables = new LinkedHashSet<>();
    private Lexer.Token current;

    ExpressionParser(String source,
                     ExpressionMethodRegistry methods,
                     VariableTypeProvider variableTypes) {
        if (methods == null) {
            throw new IllegalArgumentException("方法注册表不能为 null");
        }
        if (variableTypes == null) {
            throw new IllegalArgumentException("变量类型提供器不能为 null");
        }
        this.lexer = new Lexer(source);
        this.methods = methods;
        this.variableTypes = variableTypes;
        this.current = lexer.nextToken();
    }

    ExpressionNode parse() {
        if (current.type == Lexer.TokenType.EOF) {
            throw error("表达式不能为空", current.position);
        }
        ExpressionNode expression = parseExpression();
        if (current.type != Lexer.TokenType.EOF) {
            throw error("表达式后存在多余内容：" + current.text, current.position);
        }
        return expression;
    }

    Set<String> getVariables() {
        return variables;
    }

    /**
     * 表达式入口。递归调用层级越深，运算符优先级越高：
     * || -> && -> == != -> < <= > >= -> + - -> * / % -> 一元运算 -> 基本项。
     * 因此 parseOr 是最低优先级层，parsePrimary 负责括号、字面量、变量和方法调用。
     */
    private ExpressionNode parseExpression() {
        return parseOr();
    }

    /** or -> and ("||" and)* */
    private ExpressionNode parseOr() {
        ExpressionNode expression = parseAnd();
        while (match(Lexer.TokenType.OR)) {
            Lexer.Token operator = previous;
            expression = makeBinary(expression, BinaryOperator.OR, parseAnd(), operator);
        }
        return expression;
    }

    /** and -> equality ("&&" equality)* */
    private ExpressionNode parseAnd() {
        ExpressionNode expression = parseEquality();
        while (match(Lexer.TokenType.AND)) {
            Lexer.Token operator = previous;
            expression = makeBinary(expression, BinaryOperator.AND, parseEquality(), operator);
        }
        return expression;
    }

    /** equality -> comparison (("==" | "!=") comparison)* */
    private ExpressionNode parseEquality() {
        ExpressionNode expression = parseComparison();
        while (match(Lexer.TokenType.EQ, Lexer.TokenType.NE)) {
            Lexer.Token operator = previous;
            BinaryOperator binaryOperator = operator.type == Lexer.TokenType.EQ
                    ? BinaryOperator.EQ : BinaryOperator.NE;
            expression = makeBinary(expression, binaryOperator, parseComparison(), operator);
        }
        return expression;
    }

    /** comparison -> additive (("<" | "<=" | ">" | ">=") additive)* */
    private ExpressionNode parseComparison() {
        ExpressionNode expression = parseAdditive();
        while (match(Lexer.TokenType.LT, Lexer.TokenType.LE,
                Lexer.TokenType.GT, Lexer.TokenType.GE)) {
            Lexer.Token operator = previous;
            BinaryOperator binaryOperator;
            switch (operator.type) {
                case LT: binaryOperator = BinaryOperator.LT; break;
                case LE: binaryOperator = BinaryOperator.LE; break;
                case GT: binaryOperator = BinaryOperator.GT; break;
                case GE: binaryOperator = BinaryOperator.GE; break;
                default: throw new IllegalStateException("无法映射比较运算符");
            }
            expression = makeBinary(expression, binaryOperator, parseAdditive(), operator);
        }
        return expression;
    }

    /** additive -> multiplicative (("+" | "-") multiplicative)* */
    private ExpressionNode parseAdditive() {
        ExpressionNode expression = parseMultiplicative();
        while (match(Lexer.TokenType.PLUS, Lexer.TokenType.MINUS)) {
            Lexer.Token operator = previous;
            BinaryOperator binaryOperator = operator.type == Lexer.TokenType.PLUS
                    ? BinaryOperator.ADD : BinaryOperator.SUBTRACT;
            expression = makeBinary(expression, binaryOperator, parseMultiplicative(), operator);
        }
        return expression;
    }

    /** multiplicative -> unary (("*" | "/" | "%") unary)* */
    private ExpressionNode parseMultiplicative() {
        ExpressionNode expression = parseUnary();
        while (match(Lexer.TokenType.MULTIPLY,
                Lexer.TokenType.DIVIDE, Lexer.TokenType.MODULO)) {
            Lexer.Token operator = previous;
            BinaryOperator binaryOperator;
            switch (operator.type) {
                case MULTIPLY: binaryOperator = BinaryOperator.MULTIPLY; break;
                case DIVIDE: binaryOperator = BinaryOperator.DIVIDE; break;
                case MODULO: binaryOperator = BinaryOperator.MODULO; break;
                default: throw new IllegalStateException("无法映射算术运算符");
            }
            expression = makeBinary(expression, binaryOperator, parseUnary(), operator);
        }
        return expression;
    }

    /** unary -> ("!" | "+" | "-") unary | primary */
    private ExpressionNode parseUnary() {
        if (match(Lexer.TokenType.NOT, Lexer.TokenType.PLUS, Lexer.TokenType.MINUS)) {
            Lexer.Token operator = previous;
            UnaryOperator unaryOperator;
            ExpressionValueType requiredType;
            switch (operator.type) {
                case NOT:
                    unaryOperator = UnaryOperator.NOT;
                    requiredType = ExpressionValueType.BOOLEAN;
                    break;
                case PLUS:
                    unaryOperator = UnaryOperator.PLUS;
                    requiredType = ExpressionValueType.NUMBER;
                    break;
                case MINUS:
                    unaryOperator = UnaryOperator.MINUS;
                    requiredType = ExpressionValueType.NUMBER;
                    break;
                default: throw new IllegalStateException("无法映射一元运算符");
            }
            ExpressionNode operand = parseUnary();
            if (operand.getType() != requiredType) {
                throw error("运算符 " + unaryOperator.symbol + " 操作数要求 " + requiredType
                        + "，实际为 " + operand.getType(), operator.position);
            }
            return new UnaryNode(unaryOperator, operand, requiredType);
        }
        return parsePrimary();
    }

    private ExpressionNode parsePrimary() {
        if (match(Lexer.TokenType.NUMBER)) {
            Lexer.Token number = previous;
            try {
                double value = Double.parseDouble(number.text);
                if (Double.isNaN(value) || Double.isInfinite(value)) {
                    throw error("数字超出 double 范围：" + number.text, number.position);
                }
                return new LiteralNode(value, ExpressionValueType.NUMBER);
            } catch (NumberFormatException exception) {
                throw error("无效数字：" + number.text, number.position);
            }
        }
        if (match(Lexer.TokenType.TRUE)) {
            return new LiteralNode(1.0, ExpressionValueType.BOOLEAN);
        }
        if (match(Lexer.TokenType.FALSE)) {
            return new LiteralNode(0.0, ExpressionValueType.BOOLEAN);
        }
        if (match(Lexer.TokenType.VARIABLE)) {
            Lexer.Token variable = previous;
            ExpressionValueType type = variableTypes.getType(variable.text);
            if (type == null) {
                throw error("没有为变量提供类型：" + variable.text, variable.position);
            }
            variables.add(variable.text);
            return new VariableNode(variable.text, type);
        }
        if (match(Lexer.TokenType.FUNCTION)) {
            return parseMethodCall(previous);
        }
        if (match(Lexer.TokenType.LEFT_PAREN)) {
            Lexer.Token leftParenthesis = previous;
            if (check(Lexer.TokenType.RIGHT_PAREN)) {
                throw error("括号中缺少表达式", current.position);
            }
            ExpressionNode expression = parseExpression();
            consume(Lexer.TokenType.RIGHT_PAREN, "左括号没有匹配的右括号", leftParenthesis.position);
            return expression;
        }
        throw error("此处需要数字、布尔值、变量、方法或左括号", current.position);
    }

    private ExpressionNode parseMethodCall(Lexer.Token methodName) {
        ExpressionMethodRegistry.RegisteredMethod method = methods.find(methodName.text);
        if (method == null) {
            throw error("不支持的方法：" + methodName.text, methodName.position);
        }
        consume(Lexer.TokenType.LEFT_PAREN, "方法后必须跟左括号", methodName.position);

        List<ExpressionNode> arguments = new ArrayList<>();
        if (!check(Lexer.TokenType.RIGHT_PAREN)) {
            do {
                arguments.add(parseExpression());
            } while (match(Lexer.TokenType.COMMA));
        }
        consume(Lexer.TokenType.RIGHT_PAREN, "方法缺少右括号", methodName.position);

        if (arguments.size() != method.argumentCount) {
            throw error("方法 " + methodName.text + " 需要 " + method.argumentCount
                    + " 个参数，实际为 " + arguments.size(), methodName.position);
        }
        for (int i = 0; i < arguments.size(); i++) {
            ExpressionValueType expected = method.getParameterType(i);
            ExpressionValueType actual = arguments.get(i).getType();
            if (actual != expected) {
                throw error(methodName.text + " 第 " + (i + 1) + " 个参数要求 "
                        + expected + "，实际为 " + actual, methodName.position);
            }
        }
        return new MethodCallNode(method, arguments);
    }

    private BinaryNode makeBinary(ExpressionNode left,
                                  BinaryOperator operator,
                                  ExpressionNode right,
                                  Lexer.Token token) {
        ExpressionValueType resultType;
        switch (operator) {
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDE:
            case MODULO:
                requireType(left, ExpressionValueType.NUMBER, operator, "左", token.position);
                requireType(right, ExpressionValueType.NUMBER, operator, "右", token.position);
                resultType = ExpressionValueType.NUMBER;
                break;
            case LT:
            case LE:
            case GT:
            case GE:
                requireType(left, ExpressionValueType.NUMBER, operator, "左", token.position);
                requireType(right, ExpressionValueType.NUMBER, operator, "右", token.position);
                resultType = ExpressionValueType.BOOLEAN;
                break;
            case EQ:
            case NE:
                if (left.getType() != right.getType()) {
                    throw error("运算符 " + operator.symbol + " 两侧类型必须相同，左侧为 "
                            + left.getType() + "，右侧为 " + right.getType(), token.position);
                }
                resultType = ExpressionValueType.BOOLEAN;
                break;
            case AND:
            case OR:
                requireType(left, ExpressionValueType.BOOLEAN, operator, "左", token.position);
                requireType(right, ExpressionValueType.BOOLEAN, operator, "右", token.position);
                resultType = ExpressionValueType.BOOLEAN;
                break;
            default:
                throw new IllegalStateException("未知运算符：" + operator);
        }
        return new BinaryNode(left, operator, right, resultType);
    }

    private void requireType(ExpressionNode node,
                             ExpressionValueType expected,
                             BinaryOperator operator,
                             String side,
                             int position) {
        if (node.getType() != expected) {
            throw error("运算符 " + operator.symbol + " " + side + "操作数要求 " + expected
                    + "，实际为 " + node.getType(), position);
        }
    }

    private boolean match(Lexer.TokenType... types) {
        for (Lexer.TokenType type : types) {
            if (check(type)) {
                previous = current;
                current = lexer.nextToken();
                return true;
            }
        }
        return false;
    }

    private Lexer.Token previous;

    private boolean check(Lexer.TokenType type) {
        return current.type == type;
    }

    private void consume(Lexer.TokenType type, String message, int errorPosition) {
        if (check(type)) {
            current = lexer.nextToken();
            return;
        }
        throw error(message, errorPosition);
    }

    private ExpressionParseException error(String message, int position) {
        return new ExpressionParseException(message, position);
    }

}
