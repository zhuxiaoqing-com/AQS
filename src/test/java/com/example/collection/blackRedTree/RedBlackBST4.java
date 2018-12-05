package com.example.collection.blackRedTree;

import com.example.snowflakeIdWorker.BitMap;

import java.util.NoSuchElementException;

/**
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST4<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;     // color of parent link

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


    /**
     * 为什么h.color 一定是红的，因为旋转看起来是红黑树节点调整位置; 对于 3- 节点来说是没有任何变化的
     * 而红黑树的 红色节点代表着 3- 节点
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        x.color = h.color;
        h.color = RED;
        h.right = x.left;
        x.left = h;

        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        x.color = h.color;
        h.color = RED;
        h.left = x.right;
        x.right = h;

        h.size = size(h.left) + size(h.right) + 1;
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
        // 为了保证每一个穿进去的节点都是 3- 节点
        if (!isRed(root.left) && !isRed(root.right))
            if (!isEmpty()) root.color = RED;
        deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) return null;
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMax() {
        // 为了保证每一个穿进去的节点都是 3- 节点
        if (!isRed(root.left) && !isRed(root.right))
            if (!isEmpty()) root.color = RED;
        deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) rotateRight(h);
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left)) {
            moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node h) {
        if (h.left == null) return h;
        return min(h.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node h) {
        if (h.right == null) return h;
        return min(h.right);

    }
    // delete the key-value pair with the given key rooted at h

    /**
     * 判断是大于还是小于
     * 小于 准备左边的值为
     *
     * @param
     * @param key
     * @return
     */
    public void delete(Key key) {
        if (!contains(key)) return;
        if (!isRed(root.left) && !isRed(root.right))
            if (!isEmpty()) root.color = RED;
        delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) h = rotateRight(h);
            //找到了，并且在叶子结点可以直接删除，在向下查找的过程中，已经保证了结点不可能是2-结点
            //只需要检查右孩子即可，因为如果左孩子非空，右孩子空只可能是3-结点，然而3-结点的红链接已经右斜了
            if (cmp == 0 && h.right == null) return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node min = min(h.right);
                h.key = min.key;
                h.val = min.val;
                h.right = deleteMin(h.right);
            } else {
                delete(h.right, key);
            }
        }
        return balance(h);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /*  private Value get(Node x, Key key) {
          while (x != null) {
              int cmp = key.compareTo(x.key);
              if      (cmp < 0) x = x.left;
              else if (cmp > 0) x = x.right;
              else              return x.val;
          }
          return null;
      }*/
    private Value get(Node node, Key key) {
        // 如果找不到了就返回 null
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.val;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            // 插入数据
            return new Node(key, val, true, 1);

        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val; //这种情况是不需要做任何变换的

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public int size() {
        return size(root);
    }

    private int size(Node h) {
        if (h == null) return 0;
        return h.size;
    }
}
/**
 * 这里比deleteMin多了一步操作，因为右子节点从父节点中获得节点的时候，我们需要将左边节点给于到右边节点，如果我们不移动的话，会破坏树的平衡
 * 5,6
 * 1,2     9       对于所展示的这个红黑树，如果不把5从左边移到右边的话，我们会直接删除9，这样会导致树的不平衡，
 * 因为红节点总是在左边的，我们进行删除操作的时候，直接将结点给予，只需要改变颜色即可，不需要,移动
 * 对于红黑树而言，6是黑结点，再删除的时候，是不需要移动的，我们移动的是5这样的红结点
 * <p>
 * 删除最大键的过程与删除最小键的过程类似，但有一点需要注意，树中红色链接都是左斜的。而我们最终会将最大的值所在的节点变为
 * 3- 节点，然后删除。因为 树中红色链接都是左斜的，所以我们需要右斜来确保。删除的最大键都是红色节点
 * 这里的右斜是为了上一个节点.因为经过处理上一个节点一定是 3- 节点。而 树中红色链接都是左斜的 所以我们需要右斜
 * <p>
 * 如果本来就是 3- 节点,那么就需要右转,让 红色变为右边，好删除
 * <p>
 *
 */
