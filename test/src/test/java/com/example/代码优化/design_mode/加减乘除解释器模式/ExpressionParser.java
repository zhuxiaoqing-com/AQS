package com.example.代码优化.design_mode.加减乘除解释器模式;

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
final class ExpressionParser {
    private final String source;
    private int position;

    ExpressionParser(String source) {
        // null 没有可分析的字符位置，统一把错误定位到 0。
        if (source == null) {
            throw new ExpressionParseException("表达式不能为 null", 0);
        }
        this.source = source;
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
        // true 表示当前位置应该读取数字、变量或左括号，而不是二元运算符。
        boolean expectOperand = true;
        boolean hasToken = false;

        // 逐个读取 token，并按照调度场算法把 token 转成后缀指令。
        while (true) {
            Token token = nextToken();
            if (token.type == TokenType.END) {
                // 已经读完整个表达式，结束词法分析循环。
                break;
            }
            // 至少读取过一个 token，避免空表达式绕过后续校验。
            hasToken = true;

            switch (token.type) {
                case NUMBER:
                    // 数字直接变成一条入栈指令；计算时不需要再次解析文本。
                    if (!expectOperand) {
                        throw error("数字前缺少运算符", token.position);
                    }
                    instructions.add(new NumberInstruction(token.number));
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
                case LEFT_PARENTHESIS:
                    // 左括号作为边界标记压入运算符栈，支持任意层级嵌套。
                    if (!expectOperand) {
                        throw error("左括号前缺少运算符", token.position);
                    }
                    operators.push(new OperatorEntry(Operator.LEFT_PARENTHESIS, token.position));
                    break;
                case RIGHT_PARENTHESIS:
                    // 右括号会弹出括号内的运算符，直到遇到对应的左括号。
                    if (expectOperand) {
                        throw error("右括号前缺少操作数", token.position);
                    }
                    popUntilLeftParenthesis(operators, instructions, token.position);
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
                                          List<ExpressionInstruction> instructions,
                                          int rightParenthesisPosition) {
        while (!operators.isEmpty() && operators.peek().operator != Operator.LEFT_PARENTHESIS) {
            // 括号内的运算符已经确定了计算范围，可以直接输出为后缀指令。
            instructions.add(new OperatorInstruction(operators.pop().operator));
        }
        if (operators.isEmpty()) {
            throw error("右括号没有匹配的左括号", rightParenthesisPosition);
        }
        // 丢弃匹配到的左括号本身，它不是计算指令。
        operators.pop();
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
            instructions.add(new OperatorInstruction(operators.pop().operator));
        }
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
        // 下划线允许作为变量名首字符，例如 _damage、player_id。
        // 后续字符还会在 readVariable 中继续接收字母、数字和下划线。
        if (Character.isLetter(current) || current == '_') {
            return readVariable(tokenPosition);
        }

        // 非数字、变量的字符只占一个字符，先推进读取位置再分类。
        position++;
        // 半角和全角括号统一为同一种 token，因此可以嵌套或混合使用：
        // (a + (b + 2))、（a + （b + 2））、(a + （b + 2）)。
        switch (current) {
            case '(':
            case '（':
                return Token.parenthesis(TokenType.LEFT_PARENTHESIS, tokenPosition);
            case ')':
            case '）':
                return Token.parenthesis(TokenType.RIGHT_PARENTHESIS, tokenPosition);
            case '+':
            case '-':
            case '*':
            case '/':
                return Token.operator(String.valueOf(current), tokenPosition);
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
        // 统一交给 Double 进行字符串到数值的转换。
        String text = source.substring(start, position);
        try {
            double number = Double.parseDouble(text);
            // 配置中的数字如果超出 double 范围，直接报错而不是静默变成 Infinity。
            if (Double.isInfinite(number) || Double.isNaN(number)) {
                throw error("数字超出 double 范围：" + text, tokenPosition);
            }
            return Token.number(number, tokenPosition);
        } catch (NumberFormatException exception) {
            throw error("数字格式错误：" + text, tokenPosition);
        }
    }

    /** 读取变量名；变量名首字符已在 nextToken 中校验过。 */
    private Token readVariable(int tokenPosition) {
        // 先消费首字符，再继续读取变量名的剩余字符。
        int start = position++;
        while (position < source.length()) {
            char current = source.charAt(position);
            if (!Character.isLetterOrDigit(current) && current != '_') {
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

    enum Operator {
        LEFT_PARENTHESIS("(", -1, false, 0),
        ADD("+", 1, false, 2),
        SUBTRACT("-", 1, false, 2),
        MULTIPLY("*", 2, false, 2),
        DIVIDE("/", 2, false, 2),
        UNARY_PLUS("+", 3, true, 1),
        UNARY_MINUS("-", 3, true, 1);

        final String symbol;              // 用于错误信息和调试。
        final int precedence;             // 数值越大，越优先计算。
        final boolean rightAssociative;   // 一元正负号是右结合的。
        final int operandCount;            // 一元运算符为 1，二元运算符为 2。

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
        private final int position;

        private OperatorEntry(Operator operator, int position) {
            // 保存运算符出现的位置，未闭合括号时可以准确提示错误位置。
            this.operator = operator;
            this.position = position;
        }
    }

    private enum TokenType {
        NUMBER,             // 数字，例如 123、1.5。
        VARIABLE,           // 变量名，例如 x、damage、_attack。
        OPERATOR,           // 运算符，例如 +、-、*、/。
        LEFT_PARENTHESIS,   // 左括号，支持半角 ( 和全角 （。
        RIGHT_PARENTHESIS,  // 右括号，支持半角 ) 和全角 ）。
        END                 // 表达式输入结束，不代表实际计算内容。
    }

    private static final class Token {
        private final TokenType type;
        private final String text;
        private final double number;
        private final int position;

        private Token(TokenType type, String text, double number, int position) {
            // 一个 Token 只保存自身类型、文本/数值和源字符串位置。
            this.type = type;
            this.text = text;
            this.number = number;
            this.position = position;
        }

        private static Token end(int position) {
            // END 没有文本和数值，只携带输入结束位置。
            return new Token(TokenType.END, "", 0.0d, position);
        }

        private static Token number(double number, int position) {
            // 数字 token 直接保存 double，避免执行阶段再次解析。
            return new Token(TokenType.NUMBER, null, number, position);
        }

        private static Token variable(String name, int position) {
            // 变量 token 保存名称，运行时再通过名称查找值。
            return new Token(TokenType.VARIABLE, name, 0.0d, position);
        }

        private static Token operator(String operator, int position) {
            return new Token(TokenType.OPERATOR, operator, 0.0d, position);
        }

        private static Token parenthesis(TokenType type, int position) {
            return new Token(type, null, 0.0d, position);
        }
    }
}
