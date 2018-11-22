package com.example.datastruture.queue;

import org.testng.annotations.Test;

/**
 * 链表队列
 */
public class LinkedQueue<E> {
    LinkedNode<E> head;
    LinkedNode<E> tail;
    int size;
    int maxSize;

    static class LinkedNode<E> {
        LinkedNode next;
        E val;

        public LinkedNode(E val) {
            this.val = val;
        }
    }

    public LinkedQueue() {
        this.maxSize = 5;
    }

    /**
     * 入队
     *
     * @return
     */
    public boolean enqueue(E val) {
        if (size == maxSize) {
            return false;
        }
        if (size == 0) {
            head = tail = new LinkedNode(val);
        } else {
            tail = tail.next = new LinkedNode(val);
        }
        size++;
        return true;
    }

    /**
     * 出队
     */
    public E dequeue() {
        if (size == 0) {
            return null;
        }
        E temp = head.val;
        head = head.next;
        size--;
        return temp;
    }

    @Test
    public void fun1() {
        LinkedQueue<String> queue = new LinkedQueue();
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
