package com.example.datastruture.suffix_infix_expression;

import org.junit.Test;

public class Test01 {

    @Test
    public void test01() {
        ArrayStack<Integer> stack = new ArrayStack<>(4);
        for (int i = 1; i <= 4; i++) {
            stack.push(i);
        }
        System.out.println(stack.toString());
        while (true) {
            Integer pop = stack.pop();
            if (pop == null) {
                break;
            }
            System.out.println(pop);
        }
        System.out.println(stack.toString());
    }

    /**
     * abc*+de*f+g*+
     * abc*+de*f+g*+
     * abc*+(de*+f)g*+
     */
    @Test
    public void test02() {
        InfixExpression infixExpression = new InfixExpression();
        String s = "a+b*c+(d*e+f)*g";
        StringBuilder builder = infixExpression.infixExpression(s);
        System.out.println(builder);
    }

    @Test
    public void test03() {
        System.out.println((int)'3');
    }
}
