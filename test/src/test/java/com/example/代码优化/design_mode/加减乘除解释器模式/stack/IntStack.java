package com.example.代码优化.design_mode.加减乘除解释器模式.stack;

/** 基于循环数组的原生 int 栈。 */
public final class IntStack {
    private int[] elements;
    private int head;
    private int tail;

    public IntStack(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("初始容量不能小于 0");
        }
        int capacity = 2;
        while (capacity <= initialCapacity) {
            capacity <<= 1;
        }
        elements = new int[capacity];
    }

    public void push(int value) {
        elements[tail] = value;
        tail = (tail + 1) & (elements.length - 1);
        if (head == tail) {
            doubleCapacity();
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        tail = (tail - 1) & (elements.length - 1);
        return elements[tail];
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int lastIndex = (tail - 1) & (elements.length - 1);
        return elements[lastIndex];
    }

    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void clear() {
        head = 0;
        tail = 0;
    }

    private void doubleCapacity() {
        int oldLength = elements.length;
        int[] newElements = new int[oldLength << 1];
        int rightLength = oldLength - head;
        System.arraycopy(elements, head, newElements, 0, rightLength);
        System.arraycopy(elements, 0, newElements, rightLength, head);
        elements = newElements;
        head = 0;
        tail = oldLength;
    }
}
