package com.example.datastruture.suffix_infix_expression;

import java.util.Arrays;

public class ArrayStack<K> {
    private Object[] stackArray;
    int count;

    public ArrayStack(int n) {
        stackArray = new Object[n];
    }

    /**
     * push 推 增加
     *
     * @param param
     * @return
     */
    public boolean push(K param) {
        if (stackArray.length == count) {
            return false;
        }
        stackArray[count] = param;
        count++;
        return true;
    }

    /**
     * 出栈  pop(取出)
     *
     * @return
     */
    public K pop() {
        if (count == 0) {
            return null;
        }
        return (K) stackArray[--count];
    }


    /**
     * peek 看一眼 偷看
     *
     * @return
     */
    public K peek() {
        if (count == 0) {
            return null;
        }
        return (K) stackArray[count - 1];
    }

    @Override
    public String toString() {
        return Arrays.toString(stackArray);
    }
}
