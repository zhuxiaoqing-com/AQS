package com.example.代码优化.design_mode.加减乘除解释器模式.stack;

/**
 * 基于循环数组的原生 double 栈，设计思路参考 ArrayDeque。
 *
 * <p>head 指向栈中第一个元素，tail 指向栈尾元素的下一个位置。
 * 栈只从 tail 入栈和出栈，因此 push、pop、peek 都是 O(1) 操作。</p>
 *
 * <p>数组容量始终是 2 的幂，数组下标通过 {@code & (length - 1)} 回绕，
 * 避免取模运算，也避免使用 {@code Double} 装箱。</p>
 */
public final class DoubleStack {
    /** 指定同一套 double 栈在执行时采用哪一种数值语义。 */
    public enum NumericType {
        INT,
        LONG,
        DOUBLE
    }

    /** 循环数组；保留一个空位置用于区分空栈和满栈。 */
    private double[] elements;
    /** 栈首元素下标。 */
    private int head;
    /** 栈尾下一个可写位置。 */
    private int tail;
    private final NumericType numericType;

    public DoubleStack(int initialCapacity) {
        this(initialCapacity, NumericType.DOUBLE);
    }

    public DoubleStack(int initialCapacity, NumericType numericType) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("初始容量不能小于 0");
        }
        if (numericType == null) {
            throw new IllegalArgumentException("数值类型不能为 null");
        }
        this.numericType = numericType;

        // 至少保留一个空位置，所以数组长度必须大于实际容量。
        int capacity = 2;
        while (capacity <= initialCapacity) {
            capacity <<= 1;
        }
        elements = new double[capacity];
    }

    public NumericType getNumericType() {
        return numericType;
    }

    /** 把数值压入栈尾。 */
    public void push(double value) {
        // tail 指向下一个可写位置，写入后再向后移动。
        elements[tail] = value;
        tail = (tail + 1) & (elements.length - 1);

        // tail 追上 head 表示数组已满，需要扩容并重新整理顺序。
        if (head == tail) {
            doubleCapacity();
        }
    }

    /** 弹出并返回栈尾数值。 */
    public double pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        // tail 指向尾部下一个位置，所以先向前回退再读取元素。
        tail = (tail - 1) & (elements.length - 1);
        return elements[tail];
    }

    /** 返回栈尾数值但不移除。 */
    public double peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        int lastIndex = (tail - 1) & (elements.length - 1);
        return elements[lastIndex];
    }

    /** 返回栈中元素数量。 */
    public int size() {
        // head 和 tail 可能发生回绕，使用掩码得到环形距离。
        return (tail - head) & (elements.length - 1);
    }

    /** 判断栈是否为空。 */
    public boolean isEmpty() {
        return head == tail;
    }

    /** 清空栈；原生数值数组不需要逐个位置置 null。 */
    public void clear() {
        head = 0;
        tail = 0;
    }

    /** 将循环数组扩容为原来的两倍，并把逻辑顺序整理到数组头部。 */
    private void doubleCapacity() {
        int oldLength = elements.length;
        double[] newElements = new double[oldLength << 1];

        // head 右侧是一段，数组开头到 tail 是另一段，需要分两次复制。
        int rightLength = oldLength - head;
        System.arraycopy(elements, head, newElements, 0, rightLength);
        System.arraycopy(elements, 0, newElements, rightLength, head);

        elements = newElements;
        head = 0;
        // 扩容后旧数组已经满了，逻辑数据长度正好等于 oldLength。
        tail = oldLength;
    }
}
