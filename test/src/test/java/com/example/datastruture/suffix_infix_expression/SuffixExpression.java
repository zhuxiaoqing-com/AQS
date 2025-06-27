package com.example.datastruture.suffix_infix_expression;

/**
 * 后缀表达式
 *
 * 1、遇到数字时，将数字压入堆栈
 * 2、遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算，并将结果入栈
 *
 * 重复上述过程直到表达式的最右端
 * 运算得出的值即为表达式的结果
 */
public class SuffixExpression {
    public long suffixExpression(String expression) {
        char[] chars = expression.toCharArray();
        ArrayStack<Character> stack = new ArrayStack<>(chars.length);
        for (int i = 0; i < chars.length; i++) {
            processStack(chars[i], stack);
        }
        return 0;
    }

    private void processStack(char aChar, ArrayStack<Character> stack) {
        int priority = getPriority(aChar);
        if(priority == -1) {
            stack.push(aChar);
            return;
        }

        Character aChar1 = stack.pop();
        Character aChar2 = stack.pop();
        //char s = count(aChar1, aChar2, aChar);


    }

    /*private char count(Character aChar1, Character aChar2, char operator) {
        int number1 = aChar1 - '0';
        int number2 = aChar2 - 48;

        switch(operator) {
            case '+':
                //return  number1 + number2;
            case '-':
                return 2;
            case '*':
            case '/':
                return 3;
        }

    }*/


    private int getPriority(char cha) {
        switch (cha) {
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
                return 3;
            default:
                return -1;
        }
    }

    private boolean isNumber(char aChar) {
        return aChar >= 48 && aChar <= 57;
    }
}
