package com.example.datastruture.skipList.arraystyle;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 数组形式的跳表
 * 跳表中存储的是正整数，并且存储的是不重复的
 */
public class SkipList {
    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;
    private Node head = new Node();// 带头链表
    private Random random = ThreadLocalRandom.current();

    public Node find(int value) {
        Node p = head;
        /**
         * 因为数组是 0 下标开始的
         */
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        // 需要更新的 层数的下标
        Node update[] = new Node[level];
        /**
         * 默认为 head
         */
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }
        Node p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            // 更新 update[i]
            update[i] = p;
        }
        for(int i = 0; i< update.length; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

    }

    /**
     * 随机 level 次，如果是奇数层数 + 1; 防止伪随机
     *
     * @return 返回 层数
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (random.nextInt(2) == 1) {
                level++;
            }
        }
        return level;
    }


    public class Node {
        private int data = -1;
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(" }");
            return builder.toString();
        }
    }
}
