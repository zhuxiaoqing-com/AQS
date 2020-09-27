package com.example.TestArithmetic.collection.blackRedTree.work;

import java.util.NoSuchElementException;

public class RBTree<Key extends Comparable, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 查找
     */
    public Value find(Key key) {
        if (key == null) {
            new Exception("key cannot null!!!");
        }
        return find(root, key);
    }

    private Value find(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return find(node.left, key);
        else if (cmp > 0) return find(node.right, key);
        else return node.val;
    }
    /*

     */
/**
 * 修改 没有 update put 就是 update
 *//*

    public Value update(Key key, Value value) {
        if (key == null) {
            new Exception("key cannot null!!!");
        }
        return update(root, key, value);
    }

    private Value update(Node node, Key key, Value value) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return update(node.left, key, value);
        else if (cmp > 0) return update(node.right, key, value);
        else {
            Value oldValue = node.val;
            node.val = value;
            return oldValue;
        }
    }
*/

    /**
     * 增加
     */
    public void put(Key key, Value val) {
        if (key == null) {
            return;
        }
        root = put(root, key, val);
        // root 可能会变化 所以最终要确保 root 为黑色节点
        root.color = BLACK;
    }

    /**
     * 插入
     *
     * @param node
     * @param key
     * @param val
     * @return
     */
    public Node put(Node node, Key key, Value val) {
        if (node == null) {
            return new Node(key, val, RED, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
            // 走到这里已经不会进入递归了 所以会慢慢退出来
        else node.val = val;

        // 除了新加的节点其他节点都需要判断是否需要调整;
        if (isRed(node.right) && !isRed(node.left)) rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        // 最后维护数量 因为是从叶子结点过来的，所以最终的数量的正确的
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    /**
     * 因为只有三节点能旋转，所以 left 或 right 必定是 red 节点
     *
     * @param node
     * @return
     */
    private Node rotateLeft(Node node) {
        Node right = node.right;
        right.color = node.color;
        node.color = RED;

        node.right = right.left;
        right.left = node;

        /**
         *   0
         * 0   0
         *       0
         *
         *      0
         *    0   0
         *  0
         *
         *  所以 h 的总的 size 是不会变的
         */
        right.size = node.size;
        // 而 node 的值不确定所以需要通过 left 和 right 的 size 重新计算
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        left.color = node.color;
        node.color = RED;

        node.left = left.right;
        left.right = node;

        left.size = node.size;
        // 而 node 的值不确定所以需要通过 left 和 right 的 size 重新计算
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.left.color = !node.left.color;
    }

    private int size(Node h) {
        if (h == null) return 0;
        return h.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void deleteMin() {
        // 如果 root 的 left 和 right 都不为 red
        // 就将 root 变为 red
        /**
         * 为什么 root.left 不变为 red 呢 因为未知。left 变为 red 可能会导致 4-节点出现;
         */
        if (!isRed(root.left) && !isRed(root.right))
            // 如果 root 不是 3- 节点。就将其变为红色使其看起来就是 red 节点
            if (!isEmpty()) root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        // 如果 node.left 没有值了 就直接返回 null 这样 node 就会被删除了
        if (node.left == null) {
            return null;
        }
        // 如果不是 3- 节点,因为 node 一定是 红节点;所以这里满足
        // 如果这里不是root不是红色节点的话, 那么这里判断就不能确定一定是 3- 节点了
        /*
         这里 h.left = black  h.left.left = red  这样说明 h.left 和 h.left.left 是一个 3-节点
          只要  h.left.left=  red 就说明了 h.left 并不是根节点所以没事
            而 h.left 为 red 的话因为上一个递归已经帮我们处理过了 确保了 不会是 4- 节点以上的节点
          */
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        // 然后继续进行递归
        node.left = deleteMin(node.left);

        // 最后等递归完了的时候进行处理
        return balance(node);
    }

    private Node balance(Node h) {
        // 这里有没有 (!isRed(h.left)) 都可以正常运行
        /**
         * 这里 left 不需要这个条件，而 max 必须要转
         */
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.right) && isRed(h.left)) flipColors(h);
        // 获取 h.size = left + right + 本身(1)
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            if (!isEmpty()) root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;

    }

    private Node deleteMax(Node node) {
        // 如果是 3-节点就将其 红节点往又偏
        if (isRed(node.left)) node = rotateRight(node);
        if (node.right == null) {
            return null;
        }
        // node.right.left 因为每次会自动将 node.right.left 红节点，变为右节点的
        if (!isRed(node.right) && !isRed(node.right.left)) {
            node = moveRedRight(node);
        }
        node.right = deleteMax(node.right);
        return balance(node);
    }

    // ?BRBB   R 是父节点
    private Node moveRedRight(Node node) {
        // ?RBRB
        flipColors(node);
        // ?RBRB  1B 是父节点
        /**
         * RRBRB -> RBRRB -> BRBRB
         * 原本是 1B 为父节点，你只要将 2R 变为 父节点,
         * 2B 为 父节点 变为 1R 为父节点 然后变颜色
         */
        if (isRed(node.left.left)) {
            // RRBRB -> RBRRB 1B 为父
            node = rotateRight(node);
            // RBRRB -> BRBRB 转换成功 变为了一个 4- 节点
            flipColors(node);
        }
        return node;
    }


    /**
     * 将其变为 3-节点。或临时的 4- 节点
     * 如果是 5- 节点需要将其调整回来
     *
     * @param node
     * @return
     */
    private Node moveRedLeft(Node node) {
        // 颜色反转 为什么 root 要变颜色 这里直接反转颜色就相当于从福父亲节点那里借来了一个 红节点
        flipColors(node);
        /**
         * 反转以后要判断 node.right.left 是不是 red
         * 因为红黑树的规则只能 left 是红色节点，所以 只需要判断 node.right.left 是不是红色即可
         * node.left.left 在前面的方法已经判断过了
         *  我们要控制不能让 node 变为 4- 节点
         */
         /*
         if RBRR 是 5- 节点的话我们要将其变为 4- 节点 RBBB 要变成这样的
         RBRB 正好是一个三节点
         RBRR 只要吧 3R(第三个R) 变为 父节点，然后换颜色就好了 就变成了 RBRB
         RBRR -> RRBR -> RBRB
         为什么是 4- 节点 RBR 因为这样才能让后面的 2-节点可以从 父节点借;借来以后 父节点也还是 3- 节点
         删除也是直接删除就好了 还是平衡的
          */
        if (isRed(node.right.left)) {
            // 现将 right.left 位置变正
            node.right = rotateRight(node.right);
            // 然后将 3R 变为父节点 现在是 2B 是父节点
            node = rotateLeft(node);
            // 换颜色
            flipColors(node);
        }
        return node;
    }

    public boolean contains(Key key) {
        return find(key) != null;
    }

    /**
     * delete
     */
    public void delete(Key key) {
        if (!contains(key)) return;
        if (key == null) {
            throw new RuntimeException("key cannot null!!");
        }
        if (!isRed(root.left) && !isRed(root.left.left)) {
            if (!isEmpty()) root.color = RED;
        }
        root = delete(key, root);
        if (!isEmpty()) root.color = BLACK;
    }

    /**
     * 将可能要删除的键全部变为 3- 4- 节点
     *
     * @param key
     * @param node
     * @return
     */
    private Node delete(Key key, Node node) {
        /**
         * 所有通向要删除的那个节点的父亲节点必须为 3- 或 4- 节点这样才能够保证最后删除以后能从父节点那里借来节点
         */
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = delete(key, node.left);
        } else {
            if (isRed(node.left)) rotateRight(node);
            //找到了，并且在叶子结点可以直接删除，在向下查找的过程中，已经保证了结点不可能是2-结点
            //只需要检查右孩子即可，因为如果左孩子非空，右孩子空只可能是3-结点，然而3-结点的红链接已经右斜了
            /**
             * 这里是因为 如果 node.right 为空，而 node.left 不为空
             * 根据红黑树的特性 node.left 必须为 红节点吗。
             * 如果 node.left 还为 黑节点就不能满足 红黑树 从任意节点到叶子节点的所有黑节点个数一样吗==
             * 而 如果 node.left 为红色节点，前面的方法已经将我们右旋过了
             * 所以只需要判断 node.right == null 是否为 null 就可以判断是否是叶子节点了吗。
             */
            /**
             * cmp < 0
             *  node.left 和 node.left.left
             * 如果 node.left 就为叶子节点了 那么 node.left.left 就一定是 黑节点(不存在默认是黑节点)，
             * 所以 node.left 必须为 RED
             *
             * cmp > 0
             * node.right 和  node.right.left(黑节点)
             * node.right.right 一定是 RED
             *
             * min 保证了如果是根节点传送进来的 node 一定是红节点.
             *      因为 node.left 和 node.left.left
             *      如果 node.left 为叶子节点 那么 node.left.left == null 一定为黑节点。
             *      进行处理后 node.left 一定会变为 RED
             * Max 也保证了如果是根节点传送进来的 node 一定是红节点.
             *       因为 node.right 和 node.right.left
             *       如果 node.right 为叶子节点 那么 node.right.left == null 一定为黑节点。
             *       进行处理后 node.right 一定会变为 RED
             *
             *  然后就是 将 node.right 或 node.left 传送进来 就是下面的这个局面了
             */
            if (key.compareTo(node.key) == 0 && node.right == null) {
                return null;
            }
            if(!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }

            if(key.compareTo(node.key) == 0) {
                // 找到右边的最小值。
                Node min = min(node.right);
                // 将最小值的 key value 替换过来
                node.key = min.key;
                node.val = min.val;
                // 删除右子节点的最小值
                /**
                 * 为什么要 node.right 而不是 node
                 * 因为 node 在上面已经 调整过了.
                 */
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(key, node.right);
            }
        }
        // 进行从叶子节点开始修正
        return balance(node);
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node node) {
        if(node.left == null) {
            return node;
        }
        return min(node.left);
    }


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
}


/**
 * 强软弱虚 引用
 * 强引用 String s = new String("");
 * 软 内存溢出异常在抛出前，会尝试清理软引用，也就是说只有在内存不够用的时候才会被清理
 *  如果回收软引用以后还是没有足够的内存，才会抛出内存溢出异常
 * 弱 关联的对象只能生存到下一次垃圾收集发生之前
 * 虚 一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来取得一个对象的实例。
 *    关联虚引用的唯一一个目的就是能在这个对象被收集器回收时收到一个系统通知
 */



















