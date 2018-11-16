package com.example.collection.skipList;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 跳跃表的节点，包括 key-value 和上下左右4个节点
 */
public class SkipList<T> {
    private SkipListNode<T> head, tail;
    private int nodes; // 节点总数
    private int listLevel; // 层数
    private static final double PROBABILITY = 0.5;

    public SkipList() {
        clear();
    }

    /**
     * 清空跳跃表
     */
    public void clear() {
        head = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
        tail = new SkipListNode<>(SkipListNode.TAIL_KEY, null);
        horizontalLink(head, tail);
        listLevel = 0;
        nodes = 0;
    }

    public boolean isEmpty() {
        return nodes == 0;
    }

    public int size() {
        return nodes;
    }

    /**
     * 在最下面一层，找到要插入的位置前面的那个 key
     */
    private SkipListNode<T> findNode(int key) {
        SkipListNode<T> n = this.head;
        while (true) {
            while (n.key != SkipListNode.TAIL_KEY && n.right.key <= key) {
                n = n.right;
            }
            if (n.down != null) {
                n = n.down;
            } else {
                break;
            }
        }
        return n;
    }

    /**
     * 查找是否存储 Key, 存在则返回该节点，否则返回 Null
     */
    public SkipListNode<T> search(int key) {
        SkipListNode<T> node = findNode(key);
        if(node.getKey() == key) {
            return node;
        }else {
            return null;
        }
    }

    /**
     * 水平双向链接
     */
    private void horizontalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.right = node2;
        node2.left = node1;
    }

    /**
     * 向跳跃表中添加元素 key-value
     */
    public void put(int k, T v) {
        // 尝试查找链表里面是否有该数据
        // 没有的话那么返回的就是即将要插入的数据的 left
        // 因为 findNode 是找到其 <= key 的值
        SkipListNode<T> p = findNode(k);
        if(p.getKey() == k) {
            p.value = v;
            return;
        }
        SkipListNode<T> q = new SkipListNode<>(k, v);
        backLink(p, q);
        int currentLevel = 0; // 当前所在的层数是 0
        // 抛硬币
        while(ThreadLocalRandom.current().nextDouble() < PROBABILITY) {
            // 如果超出了高度，需要重新建一个顶层
            if(currentLevel >= listLevel) {
                listLevel++;
                SkipListNode<T> p1 = new SkipListNode<>(SkipListNode.HEAD_KEY, null);
                SkipListNode<T> p2 = new SkipListNode<>(SkipListNode.TAIL_KEY, null);
                horizontalLink(p1, p2);
                verticalLink(p1, head);
                verticalLink(p2, tail);
                head = p1;
                tail = p2;
            }
            // 将 q 移动到上一层
            // 找到 q 的上一层的 Left
            while (p.up == null) {
                p = p.left;
            }
            p = p.up;

            SkipListNode<T> e = new SkipListNode<>(k, null);// 只保存 Key 就可以了
            backLink(p, e); // 将 e 插入到 p 的后面
            verticalLink(e, q); // 将 e 和 q 上下链接
            q = e;
            currentLevel ++;
        }
        // 总 nodes 加 1
        nodes++;

    }

    private void verticalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.down = node2;
        node2.up = node1;
    }

    // 在 node1 后面插入 node2
    private void backLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node2.left = node1;
        node2.right = node1.right;
        node1.right.left = node2;
        node1.right = node2;
    }

    /**
     * 打印出原始数据
     */
    @Override
    public String toString() {
        if(isEmpty()) {
            return "跳跃表为空！";
        }
        StringBuilder builder = new StringBuilder();
        SkipListNode<T> p = this.head;
        while (p.down != null) {
            p = p.down;
        }
        while (p.left != null) {
            p = p.left;
        }
        // 排除 public static int HEAD_KEY = 0x80000000; // 负无穷
        if (p.right != null) {
            p = p.right;
        }
        // 排除 public static int TAIL_KEY = 0x7FFFFFFF; // 正无穷
        while (p.right != null) {
            builder.append(p);
            builder.append("\n");
            p = p.right;
        }
        return builder.toString();
    }
}











