package com.example.datastruture.queue;

import org.junit.Test;

import java.util.HashMap;

public class ArrayQueue {
    String[] items;
    int n;
    int head; // 代表将要弹出的下标
    int tail; // 代表将要存入的数据的下标

    public ArrayQueue() {
        int n = 5;
        items = new String[n];
        this.n = n;
    }

    // 入队
    public boolean enqueue(String item) {
        // 数据搬移
        if (tail == n) {
            // 队列已满
            if (head == 0) {
                return false;
            }
            reLocation();
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果 head == tail 说明队列中没有数据
        if (head == tail) {
            return null;
        }
        String temp = items[head];
        head++;
        return temp;
    }

    // 数据搬移
    public void reLocation() {
        for (int i = head; i < tail; i++) {
            items[i - head] = items[i];
        }
        tail -= head;
        head = 0;
    }

    @Test
    public void fun1() {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("e");
        queue.enqueue("e");
        queue.enqueue("e");
        queue.enqueue("e");

    }
}

