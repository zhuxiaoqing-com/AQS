package com.example.代码优化.design_mode.加减乘除解释器模式;

import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.MethodInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.NumberInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.instruction.OperatorInstruction;
import com.example.代码优化.design_mode.加减乘除解释器模式.method.ExpressionMethodRegistry;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 调度场算法（Shunting-yard algorithm）。
 *
 * <p>把人容易阅读的中缀表达式转换成机器容易执行的后缀指令，
 * 通过运算符栈处理括号、优先级和结合性。</p>
 */
public final class ExpressionParser {
    private final String source;
    private final ExpressionMethodRegistry methods;
    private int position;

    ExpressionParser(String source, ExpressionMethodRegistry methods) {
        // null 没有可分析的字符位置，统一把错误定位到 0。
        if (source == null) {
            throw new ExpressionParseException("表达式不能为 null", 0);
        }
        this.source = source;
        this.methods = methods;
    }

    Expression parse() {
        // 空白表达式没有任何可执行内容，直接拒绝。
        if (source.trim().isEmpty()) {
            throw error("表达式不能为空", 0);
        }

        // instructions 保存后缀表达式指令，后续 evaluate 可以直接执行。
        List<ExpressionInstruction> instructions = new ArrayList<>();
        // LinkedHashSet 用于去重，同时保留变量首次出现的顺序。
        Set<String> variables = new LinkedHashSet<>();
        // 运算符栈暂存运算符和左括号，最终按照优先级生成后缀指令。
        Deque<OperatorEntry> operators = new ArrayDeque<>();
        // 每个左括号对应一个逗号计数，用于校验方法参数个数。
        Deque<Integer> argumentCounts = new ArrayDeque<>();
        // true 表示当前位置应该读取数字、变量或左括号，而不是二元运算符。
        boolean expectOperand = true;
        // 识别到方法名后，下一项必须是左括号。
        boolean expectMethodParenthesis = false;
        boolean hasToken = false;

        // 逐个读取 token，并按照调度场算法把 token 转成后缀指令。
        while (true) {
            Token token = nextToken();
            if (token.type == TokenType.END) {
                if (expectMethodParenthesis) {
                    throw error("方法缺少左括号", token.position);
                }
                // 已经读完整个表达式，结束词法分析循环。
                break;
            }
            if (expectMethodParenthesis && token.type != TokenType.LEFT_PARENTHESIS) {
                throw error("方法后必须跟左括号", token.position);
            }
            // 至少读取过一个 token，避免空表达式绕过后续校验。
            hasToken = true;

            switch (token.type) {
                case NUMBER:
                    // 数字保留原始文本，真正的类型转换交给 int、long 或 double 执行器。
                    if (!expectOperand) {
                        throw error("数字前缺少运算符", token.position);
                    }
                    instructions.add(new NumberInstruction(token.text));
                    expectOperand = false;
                    break;
                case VARIABLE:
                    // 变量只记录名称，真正的值在 evaluate 时通过 ValueProvider 读取。
                    if (!expectOperand) {
                        throw error("变量前缺少运算符", token.position);
                    }
                    instructions.add(new VariableInstruction(token.text));
                    variables.add(token.text);
                    expectOperand = false;
                    break;
                case FUNCTION:
                    ExpressionMethodRegistry.RegisteredMethod method = methods.find(token.text);
                    if (method == null) {
                        throw error("不支持的方法：" + token.text, token.position);
                    }
                    if (!expectOperand) {
                        throw error("方法前缺少运算符", token.position);
                    }
                    operators.push(new OperatorEntry(method, token.position));
                    expectMethodParenthesis = true;
                    break;
                case LEFT_PARENTHESIS:
                    // 左括号作为边界标记压入运算符栈，支持任意层级嵌套。
                    if (!expectOperand) {
                        throw error("左括号前缺少运算符", token.position);
                    }
                    operators.push(new OperatorEntry(Operator.LEFT_PARENTHESIS, token.position));
                    argumentCounts.push(0);
                    expectMethodParenthesis = false;
                    break;
                case RIGHT_PARENTHESIS:
                    // 右括号会弹出括号内的运算符，直到遇到对应的左括号。
                    if (expectOperand) {
                        throw error("右括号前缺少操作数", token.position);
                    }
                    popUntilLeftParenthesis(operators, argumentCounts, instructions, token.position);
                    break;
                case COMMA:
                    // 逗号只能出现在括号内，并且前后都必须有完整的方法参数。
                    if (expectOperand) {
                        throw error("逗号前缺少方法参数", token.position);
                    }
                    while (!operators.isEmpty()
                            && operators.peek().operator != Operator.LEFT_PARENTHESIS) {
                        instructions.add(toInstruction(operators.pop()));
                    }
                    if (operators.isEmpty()) {
                        throw error("逗号没有匹配的左括号", token.position);
                    }
                    argumentCounts.push(argumentCounts.pop() + 1);
                    expectOperand = true;
                    break;
                case OPERATOR:
                    // 根据当前位置区分二元运算符和一元正负号，再处理运算符优先级。
                    Operator operator = toOperator(token, expectOperand);
                    // 当前运算符入栈前，先把应该更早执行的运算符输出。
                    popHigherPriorityOperators(operators, instructions, operator);
                    operators.push(new OperatorEntry(operator, token.position));
                    expectOperand = true;
                    break;
                default:
                    throw error("无法识别的 token", token.position);
            }
        }

        // 只包含空白时不会进入上面的 token 分支。
        if (!hasToken) {
            throw error("表达式不能为空", 0);
        }
        // 结束时仍期待操作数，说明表达式最后是运算符或左括号。
        if (expectOperand) {
            throw error("表达式以运算符结尾", source.length());
        }

        // 清空剩余运算符；如果还有左括号，说明括号没有闭合。
        while (!operators.isEmpty()) {
            OperatorEntry entry = operators.pop();
            if (entry.operator == Operator.LEFT_PARENTHESIS) {
                throw error("左括号没有匹配的右括号", entry.position);
            }
            instructions.add(new OperatorInstruction(entry.operator));
        }
        return new Expression(instructions, variables);
    }

    /** 遇到右括号时，先把括号内尚未输出的运算符转成后缀指令。 */
    private void popUntilLeftParenthesis(Deque<OperatorEntry> operators,
                                          Deque<Integer> argumentCounts,
                                          List<ExpressionInstruction> instructions,
                                          int rightParenthesisPosition) {
        while (!operators.isEmpty() && operators.peek().operator != Operator.LEFT_PARENTHESIS) {
            // 括号内的运算符已经确定了计算范围，可以直接输出为后缀指令。
            instructions.add(toInstruction(operators.pop()));
        }
        if (operators.isEmpty()) {
            throw error("右括号没有匹配的左括号", rightParenthesisPosition);
        }
        // 丢弃匹配到的左括号本身，它不是计算指令。
        operators.pop();
        int commaCount = argumentCounts.pop();
        if (!operators.isEmpty() && operators.peek().method != null) {
            ExpressionMethodRegistry.RegisteredMethod method = operators.peek().method;
            if (commaCount + 1 != method.argumentCount) {
                throw error("方法参数个数错误，方法需要 " + method.argumentCount + " 个参数",
                        rightParenthesisPosition);
            }
            operators.pop();
            instructions.add(new MethodInstruction(method.implementation, method.argumentCount));
        } else if (commaCount != 0) {
            throw error("普通括号不能使用逗号", rightParenthesisPosition);
        }
    }

    /** 调度场算法的核心：当前运算符入栈前，弹出优先级更高的运算符。 */
    private void popHigherPriorityOperators(Deque<OperatorEntry> operators,
                                             List<ExpressionInstruction> instructions,
                                             Operator current) {
        while (!operators.isEmpty() && operators.peek().operator != Operator.LEFT_PARENTHESIS
                && (operators.peek().operator.precedence > current.precedence
                || (!current.rightAssociative
                && operators.peek().operator.precedence == current.precedence))) {
            // 左结合运算符遇到相同优先级也要先弹出；右结合运算符则保留。
            instructions.add(toInstruction(operators.pop()));
        }
    }

    private ExpressionInstruction toInstruction(OperatorEntry entry) {
        if (entry.method != null) {
            return new MethodInstruction(entry.method.implementation, entry.method.argumentCount);
        }
        return new OperatorInstruction(entry.operator);
    }

    private Operator toOperator(Token token, boolean expectOperand) {
        // 操作数位置上的 + 和 - 是一元运算符，例如 -x、+(a + b)。
        if (expectOperand) {
            if (token.text.charAt(0) == '+') {
                return Operator.UNARY_PLUS;
            }
            if (token.text.charAt(0) == '-') {
                return Operator.UNARY_MINUS;
            }
            throw error("此处需要操作数，不能使用运算符 " + token.text, token.position);
        }

        // 操作数之后的运算符都是二元运算符，需要两个栈内操作数。
        switch (token.text.charAt(0)) {
            case '+':
                return Operator.ADD;
            case '-':
                return Operator.SUBTRACT;
            case '*':
                return Operator.MULTIPLY;
            case '/':
                return Operator.DIVIDE;
            default:
                throw error("不支持的运算符：" + token.text, token.position);
        }
    }

    /** 从表达式中读取一个 token，词法分析不负责计算。 */
    private Token nextToken() {
        // 表达式允许在 token 之间出现任意空白字符。
        skipWhitespace();
        if (position >= source.length()) {
            // 用 END token 表示输入结束，而不是让调用方直接判断下标。
            return Token.end(position);
        }

        // 保存 token 起始位置，用于生成带位置的语法错误信息。
        int tokenPosition = position;
        char current = source.charAt(position);
        if (Character.isDigit(current)) {
            // 数字必须以数字字符开头，支持整数和普通小数，例如 12、1.5。
            return readNumber(tokenPosition);
        }
        // 方法和变量分别使用 m_、v_ 前缀，避免表达式中的名称含义不明确。
        if (Character.isLetter(current)) {
            if (isMethodPrefix()) {
                return readMethod(tokenPosition);
            }
            if (isVariablePrefix()) {
                return readVariable(tokenPosition);
            }
            throw error("标识符必须以 m_（方法）或 v_（变量）开头", tokenPosition);
        }

        // 非数字、变量的字符只占一个字符，先推进读取位置再分类。
        position++;
        // 表达式语法统一使用半角括号，例如 (a + (b + 2))。
        switch (current) {
            case '(':
                return Token.parenthesis(TokenType.LEFT_PARENTHESIS, tokenPosition);
            case ')':
                return Token.parenthesis(TokenType.RIGHT_PARENTHESIS, tokenPosition);
            case '+':
            case '-':
            case '*':
            case '/':
                return Token.operator(String.valueOf(current), tokenPosition);
            case ',':
                return Token.comma(tokenPosition);
            default:
                throw error("不支持的字符：" + current, tokenPosition);
        }
    }

    /** 读取一个普通数字，支持整数和小数，不支持科学计数法。 */
    private Token readNumber(int tokenPosition) {
        // 记录起点，最后通过 substring 取得完整数字文本。
        int start = position;
        // 先读取整数部分；因为 nextToken 已经确认首字符是数字，所以至少会读取一位。
        while (position < source.length() && Character.isDigit(source.charAt(position))) {
            position++;
        }
        if (position < source.length() && source.charAt(position) == '.') {
            // 小数点是可选的，小数点后的数字也是连续读取的。
            position++;
            while (position < source.length() && Character.isDigit(source.charAt(position))) {
                position++;
            }
        }
        // 保留数字文本，后续由选定的基础类型执行器负责转换。
        String text = source.substring(start, position);
        return Token.number(text, tokenPosition);
    }

    /** 判断当前位置是否是方法前缀 m_。 */
    private boolean isMethodPrefix() {
        return source.charAt(position) == 'm'
                && position + 1 < source.length()
                && source.charAt(position + 1) == '_';
    }

    private boolean isVariablePrefix() {
        return source.charAt(position) == 'v'
                && position + 1 < source.length()
                && source.charAt(position + 1) == '_';
    }

    /** 读取 m_ 开头的方法名。 */
    private Token readMethod(int tokenPosition) {
        int start = position;
        // 先消费固定的方法前缀 m_，方法名本身只允许字母和数字。
        position += 2;
        int methodNameStart = position;
        while (position < source.length()) {
            char current = source.charAt(position);
            if (!Character.isLetterOrDigit(current)) {
                // 当前字符属于下一个 token，不能在这里消费掉。
                break;
            }
            position++;
        }
        if (position == methodNameStart) {
            throw error("方法名不能为空", tokenPosition);
        }
        return Token.function(source.substring(start, position), tokenPosition);
    }

    /** 读取 v_ 开头的变量名；名称必须以字母开头，后续可包含字母和数字。 */
    private Token readVariable(int tokenPosition) {
        int start = position;
        position += 2;
        if (position >= source.length() || !Character.isLetter(source.charAt(position))) {
            throw error("变量前缀 v_ 后必须跟字母开头的名称", tokenPosition);
        }
        position++;
        while (position < source.length()) {
            char current = source.charAt(position);
            if (!Character.isLetterOrDigit(current)) {
                // 当前字符属于下一个 token，不能在这里消费掉。
                break;
            }
            position++;
        }
        return Token.variable(source.substring(start, position), tokenPosition);
    }

    private void skipWhitespace() {
        // 只移动 position，不产生 token；空格、换行、制表符都会被跳过。
        while (position < source.length() && Character.isWhitespace(source.charAt(position))) {
            position++;
        }
    }

    private ExpressionParseException error(String message, int errorPosition) {
        // 统一创建异常，保证所有解析错误都带有字符位置。
        return new ExpressionParseException(message, errorPosition);
    }

    public enum Operator {
        LEFT_PARENTHESIS("(", -1, false, 0),
        ADD("+", 1, false, 2),
        SUBTRACT("-", 1, false, 2),
        MULTIPLY("*", 2, false, 2),
        DIVIDE("/", 2, false, 2),
        UNARY_PLUS("+", 3, true, 1),
        UNARY_MINUS("-", 3, true, 1),
        FUNCTION("function", 4, false, 0);

        public final String symbol;              // 用于错误信息和调试。
        final int precedence;             // 数值越大，越优先计算。
        final boolean rightAssociative;   // 一元正负号是右结合的。
        public final int operandCount;            // 一元运算符为 1，二元运算符为 2。

        Operator(String symbol, int precedence, boolean rightAssociative, int operandCount) {
            // 运算符的规则集中定义，避免优先级散落在解析逻辑中。
            this.symbol = symbol;
            this.precedence = precedence;
            this.rightAssociative = rightAssociative;
            this.operandCount = operandCount;
        }

    }

    private static final class OperatorEntry {
        private final Operator operator;
        private final ExpressionMethodRegistry.RegisteredMethod method;
        private final int position;

        private OperatorEntry(Operator operator, int position) {
            // 保存运算符出现的位置，未闭合括号时可以准确提示错误位置。
            this.operator = operator;
            this.method = null;
            this.position = position;
        }

        private OperatorEntry(ExpressionMethodRegistry.RegisteredMethod method, int position) {
            this.operator = Operator.FUNCTION;
            this.method = method;
            this.position = position;
        }
    }

    private enum TokenType {
        NUMBER,             // 数字，例如 123、1.5。
        VARIABLE,           // 变量名，例如 x、damage、attack2。
        FUNCTION,           // 方法名，例如 m_random。
        OPERATOR,           // 运算符，例如 +、-、*、/。
        LEFT_PARENTHESIS,   // 半角左括号 (。
        RIGHT_PARENTHESIS,  // 半角右括号 )。
        COMMA,              // 半角逗号。
        END                 // 表达式输入结束，不代表实际计算内容。
    }

    private static final class Token {
        private final TokenType type;
        private final String text;
        private final int position;

        private Token(TokenType type, String text, int position) {
            // 一个 Token 只保存自身类型、文本和源字符串位置。
            this.type = type;
            this.text = text;
            this.position = position;
        }

        private static Token end(int position) {
            // END 没有文本和数值，只携带输入结束位置。
            return new Token(TokenType.END, "", position);
        }

        private static Token number(String text, int position) {
            // 数字 token 保存原始文本，避免在编译阶段绑定某一种数值类型。
            return new Token(TokenType.NUMBER, text, position);
        }

        private static Token variable(String name, int position) {
            // 变量 token 保存名称，运行时再通过名称查找值。
            return new Token(TokenType.VARIABLE, name, position);
        }

        private static Token function(String name, int position) {
            // 方法名与普通变量区分开，解析阶段才能要求它后面必须有左括号。
            return new Token(TokenType.FUNCTION, name, position);
        }

        private static Token operator(String operator, int position) {
            return new Token(TokenType.OPERATOR, operator, position);
        }

        private static Token parenthesis(TokenType type, int position) {
            return new Token(type, null, position);
        }

        private static Token comma(int position) {
            return new Token(TokenType.COMMA, null, position);
        }
    }
}
