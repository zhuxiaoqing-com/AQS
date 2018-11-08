package com.example.collection.blackRedTree;

import java.util.NoSuchElementException;

public class RedBlackBST2<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        // 保留父节点的颜色
        x.color = h.color;
        // 直接变为 red 因为一定是 red
        h.color = RED;
        h.right = x.left;
        x.left = h;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        x.color = h.color;
        h.color = RED;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    private void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            if (!isEmpty()) root.color = RED;
        }
        deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node h) {
        /**
         * 删除最大键的过程与删除最小键的过程类似，但有一点需要注意，树中红色链接都是左斜的。
         * 如果最大键在3-结点，不能直接删除，rotateRight(h)处理一下。我们可以在自上向下的过程中将根结点右子树的红链接右斜，
         * 然后递归返回时自下向上的修正即可。
         */
        if (isRed(h.left))
            h = rotateLeft(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.left = deleteMax(h.right);
        return balance(h);
    }

    private Node moveRedLeft(Node h) {
        // 将 h.left 和 h.right 变为 一个 3- 节点
        flipColors(h);
        // 如果 h.right 原本就是 3- 节点 则
        if (isRed(h.right.left)) {
            // 调整下位置 使红黑树更好调节 3- 节点
            rotateRight(h.right);
            rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        // 将 h.left 和 h.right 变为 一个 3- 节点
        flipColors(h);
        // 如果 h.right 原本就是 3- 节点 则
        if (isRed(h.left.left)) {
            // 调整下位置 使红黑树更好调节 3- 节点
            rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        // assert (h != null);
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        return h;
    }

    public boolean contains(Key key) {
        return false;//get(key) != null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else return min(x.left);
    }

    // delete the key-value pair with the given key rooted at h

    /**
     * 判断是大于还是小于
     * 小于 准备左边的值为
     * @param h
     * @param key
     * @return
     */
    private Node delete(Node h, Key key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0) {
            // 小于这个值就将其变为 3- 节点
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            // 就将当前值变为 3- 节点
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                // 获取 right 的最小值
                // 本来 right 的最小值是 key 但是 key 被删除以后就需要获取 right 的最小值来填充
                // key-right 是一个区间 key 最小 right 最大
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else h.right = delete(h.right, key);
        }
        return balance(h);
    }

}
/**
 * 这里比deleteMin多了一步操作，因为右子节点从父节点中获得节点的时候，我们需要将左边节点给于到右边节点，如果我们不移动的话，会破坏树的平衡
 *     5,6
 * 1,2     9       对于所展示的这个红黑树，如果不把5从左边移到右边的话，我们会直接删除9，这样会导致树的不平衡，
 * 因为红节点总是在左边的，我们进行删除操作的时候，直接将结点给予，只需要改变颜色即可，不需要,移动
 * 对于红黑树而言，6是黑结点，再删除的时候，是不需要移动的，我们移动的是5这样的红结点
 */

/**
 * 删除最大键的过程与删除最小键的过程类似，但有一点需要注意，树中红色链接都是左斜的。而我们最终会将最大的值所在的节点变为
 * 3- 节点，然后删除。因为 树中红色链接都是左斜的，所以我们需要右斜来确保。删除的最大键都是红色节点
 * 这里的右斜是为了上一个节点.因为经过处理上一个节点一定是 3- 节点。而 树中红色链接都是左斜的 所以我们需要右斜
 *
 * 如果本来就是 3- 节点,那么就需要右转,让 红色变为右边，好删除
 */
