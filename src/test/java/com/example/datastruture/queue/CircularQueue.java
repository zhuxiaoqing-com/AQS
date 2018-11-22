package com.example.datastruture.queue;

import java.util.HashMap;

/**
 * 圆形队列
 */
public class CircularQueue {
    private String[] items;
    private int n;
    private int head;
    private int tail;

    // 申请一个大小为 capacity 的数组
    public CircularQueue() {
        this.n = 5;
    }

    /**
     * 入队
     *
     * @return
     */
    public boolean enqueue(String val) {
        // 队列满了
        if (((tail + 1) & (n - 1)) == head) {
            return false;
        }
        items[tail] = val;
        tail = (tail + 1) & (n - 1);
        return true;
    }

    /**
     * 出队
     */
    public String dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        head = (head + 1) & (n - 1);
        return ret;
    }
}
