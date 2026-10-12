package com.example.代码优化.design_mode.加减乘除解释器模式;

/** 把表达式文本切分为带源码位置的 token，不负责语法和计算。 */
final class Lexer {
    private final String source;
    private int position;

    Lexer(String source) {
        if (source == null) {
            throw new ExpressionParseException("表达式不能为 null", 0);
        }
        this.source = source;
    }

    Token nextToken() {
        skipWhitespace();
        if (position >= source.length()) {
            return new Token(TokenType.EOF, "", position);
        }

        int tokenPosition = position;
        char current = source.charAt(position);
        if (Character.isDigit(current)) {
            return readNumber(tokenPosition);
        }
        if (Character.isLetter(current)) {
            return readIdentifier(tokenPosition);
        }

        position++;
        switch (current) {
            case '+': return token(TokenType.PLUS, "+", tokenPosition);
            case '-': return token(TokenType.MINUS, "-", tokenPosition);
            case '*': return token(TokenType.MULTIPLY, "*", tokenPosition);
            case '/': return token(TokenType.DIVIDE, "/", tokenPosition);
            case '%': return token(TokenType.MODULO, "%", tokenPosition);
            case '(': return token(TokenType.LEFT_PAREN, "(", tokenPosition);
            case ')': return token(TokenType.RIGHT_PAREN, ")", tokenPosition);
            case ',': return token(TokenType.COMMA, ",", tokenPosition);
            case '!':
                return match('=')
                        ? token(TokenType.NE, "!=", tokenPosition)
                        : token(TokenType.NOT, "!", tokenPosition);
            case '<':
                return match('=')
                        ? token(TokenType.LE, "<=", tokenPosition)
                        : token(TokenType.LT, "<", tokenPosition);
            case '>':
                return match('=')
                        ? token(TokenType.GE, ">=", tokenPosition)
                        : token(TokenType.GT, ">", tokenPosition);
            case '=':
                if (match('=')) {
                    return token(TokenType.EQ, "==", tokenPosition);
                }
                throw error("不支持单独的 =，相等运算请使用 ==", tokenPosition);
            case '&':
                if (match('&')) {
                    return token(TokenType.AND, "&&", tokenPosition);
                }
                throw error("不支持单独的 &，逻辑与请使用 &&", tokenPosition);
            case '|':
                if (match('|')) {
                    return token(TokenType.OR, "||", tokenPosition);
                }
                throw error("不支持单独的 |，逻辑或请使用 ||", tokenPosition);
            default:
                throw error("不支持的字符：" + current, tokenPosition);
        }
    }

    private Token readNumber(int tokenPosition) {
        int start = position;
        while (position < source.length() && Character.isDigit(source.charAt(position))) {
            position++;
        }
        if (position < source.length() && source.charAt(position) == '.') {
            position++;
            while (position < source.length() && Character.isDigit(source.charAt(position))) {
                position++;
            }
        }
        return token(TokenType.NUMBER, source.substring(start, position), tokenPosition);
    }

    private Token readIdentifier(int tokenPosition) {
        if (source.startsWith("true", position)
                && isIdentifierBoundary(position + 4)) {
            position += 4;
            return token(TokenType.TRUE, "true", tokenPosition);
        }
        if (source.startsWith("false", position)
                && isIdentifierBoundary(position + 5)) {
            position += 5;
            return token(TokenType.FALSE, "false", tokenPosition);
        }
        if (source.startsWith("m_", position)) {
            return readPrefixedIdentifier(tokenPosition, "m_", TokenType.FUNCTION,
                    "方法名不能为空");
        }
        if (source.startsWith("v_", position)) {
            return readPrefixedIdentifier(tokenPosition, "v_", TokenType.VARIABLE,
                    "变量前缀 v_ 后必须跟字母开头的名称");
        }
        throw error("标识符必须是 true、false、m_ 方法或 v_ 变量", tokenPosition);
    }

    private Token readPrefixedIdentifier(int tokenPosition,
                                         String prefix,
                                         TokenType type,
                                         String emptyNameMessage) {
        int start = position;
        position += prefix.length();
        if (position >= source.length() || !Character.isLetter(source.charAt(position))) {
            throw error(emptyNameMessage, tokenPosition);
        }
        position++;
        while (position < source.length() && Character.isLetterOrDigit(source.charAt(position))) {
            position++;
        }
        return token(type, source.substring(start, position), tokenPosition);
    }

    private boolean isIdentifierBoundary(int index) {
        return index >= source.length() || !Character.isLetterOrDigit(source.charAt(index));
    }

    private boolean match(char expected) {
        if (position >= source.length() || source.charAt(position) != expected) {
            return false;
        }
        position++;
        return true;
    }

    private void skipWhitespace() {
        while (position < source.length() && Character.isWhitespace(source.charAt(position))) {
            position++;
        }
    }

    private Token token(TokenType type, String text, int tokenPosition) {
        return new Token(type, text, tokenPosition);
    }

    private ExpressionParseException error(String message, int errorPosition) {
        return new ExpressionParseException(message, errorPosition);
    }

    enum TokenType {
        NUMBER,       // 数字字面量，例如 12、1.5；词法分析后由 Parser 构造成 NUMBER 节点。
        VARIABLE,    // v_ 前缀变量名；Parser 会查询变量类型，执行时再读取变量值。
        FUNCTION,    // m_ 前缀方法名；Parser 会检查调用括号、参数数量和参数类型。
        TRUE,        // true 布尔字面量；Parser 将其构造成值为真的 BOOLEAN 节点。
        FALSE,       // false 布尔字面量；Parser 将其构造成值为假的 BOOLEAN 节点。
        PLUS,        // 加号；Parser 根据当前位置将它解释为二元加法或一元正号。
        MINUS,       // 减号；Parser 根据当前位置将它解释为二元减法或一元负号。
        MULTIPLY,    // 乘号；Parser 将其识别为 NUMBER 乘法运算符。
        DIVIDE,      // 除号；Parser 将其识别为 NUMBER 除法运算符。
        MODULO,      // 百分号；Parser 将其识别为 NUMBER 取余运算符。
        LT,          // 小于号 <；Parser 将两个 NUMBER 操作数构造成 BOOLEAN 比较表达式。
        LE,          // 小于等于号 <=；Parser 将两个 NUMBER 操作数构造成 BOOLEAN 比较表达式。
        GT,          // 大于号 >；Parser 将两个 NUMBER 操作数构造成 BOOLEAN 比较表达式。
        GE,          // 大于等于号 >=；Parser 将两个 NUMBER 操作数构造成 BOOLEAN 比较表达式。
        EQ,          // 相等号 ==；Parser 要求两侧类型相同，结果为 BOOLEAN。
        NE,          // 不等号 !=；Parser 要求两侧类型相同，结果为 BOOLEAN。
        NOT,         // 逻辑非 !；Parser 要求操作数为 BOOLEAN，结果仍为 BOOLEAN。
        AND,         // 逻辑与 &&；Parser 构造 AND 节点，编译器生成短路跳转指令。
        OR,          // 逻辑或 ||；Parser 构造 OR 节点，编译器生成短路跳转指令。
        LEFT_PAREN,  // 左括号 (；用于表达式分组，或开始方法实参列表。
        RIGHT_PAREN, // 右括号 )；结束表达式分组或方法实参列表。
        COMMA,       // 逗号 ,；分隔方法调用中的相邻实参。
        EOF          // 输入结束标记；表示 Lexer 已扫描完表达式文本，不代表一个表达式值。
    }

    static final class Token {
        final TokenType type;
        final String text;
        final int position;

        Token(TokenType type, String text, int position) {
            this.type = type;
            this.text = text;
            this.position = position;
        }
    }
}
