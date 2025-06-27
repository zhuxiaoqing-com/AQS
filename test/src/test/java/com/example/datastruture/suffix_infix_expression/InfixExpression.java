package com.example.datastruture.suffix_infix_expression;

/**
 * 中缀表达式
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 规则
 * 1、 如果遇到操作数，我们直接将其输出
 * 2、如果遇到操作符，则我们将其放入到栈中，遇到左括号时我们也将其放入栈中
 * 3、如果遇到一个右括号，则将栈元素弹出，将弹出的操作符输出直到遇到左括号为止。注意，左括号只弹出并不输出
 * 4、如果遇到任何其他的操作符，如 + - * / ( 等，从栈中弹出元素直到遇到更低优先级的元素(或者栈为空)为止。
 * 弹出完这些元素后，才将遇到的操作符压入到栈中。有一点需要注意，只有在遇到 ) 的情况下我们在弹出 (
 * 其他情况我们都不会弹出
 * <p>
 * 5、如果我们读到了输入的末尾，则将栈中的元素依次弹出
 */
public class InfixExpression {

    public InfixExpression() {
    }

    /**
     * 运算表达式
     *
     * @param operational 表达式
     * @return
     */
    public StringBuilder infixExpression(String operational) {
        char[] chars = operational.toCharArray();
        StringBuilder newExpression = new StringBuilder(chars.length);
        ArrayStack<Character> stack = new ArrayStack<>(chars.length);
        for (int i = 0; i < chars.length; i++) {
            processStack(chars[i], stack, newExpression);
        }
        // 输出结束了 直接将剩余的 操作符 全部弹出
        while (stack.peek() != null) {
            newExpression.append(stack.pop());
        }
        return newExpression;
    }

    private void processStack(char aChar, ArrayStack<Character> stack, StringBuilder newExpression) {
        // 如果是 newPriority == 5 也就是 ) 就弹出所有元素直到 ( 为止
        if (aChar == ')') {
            while (stack.peek() != '(') {
                newExpression.append(stack.pop());
            }
            // 将左 ( 弹出
            stack.pop();
            return;
        }

        // 如果是操作数直接将其输出
        if (isNumber(aChar)) {
            newExpression.append(aChar);
            return;
        }
        if (aChar == '(') {
            stack.push(aChar);
            return;
        }

        int newPriority = getPriority(aChar);
        // 如果优先级比 stack 里面的栈顶的元素小或者相同 的话就将栈顶的弹出，然后再装入
        Character s;
        /*
         1、如果没有元素了
         2、如果等于 '('
         3、如果里面的元素 比 要加入的元素 优先级高或者相等

         其实就是要保证栈顶的元素要比栈底的元素优先级高 除了 (
          */
        while ((s = stack.peek()) != null && !s.equals('(') && getPriority(s) >= newPriority) {
            Character pop = stack.pop();
            newExpression.append(pop);
        }
        stack.push(aChar);
    }


    /**
     * 获取优先级
     *
     * @param cha
     * @return
     */
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
