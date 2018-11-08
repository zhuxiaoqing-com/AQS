package com.example.collection.blackRedTree;

/**
 * 颜色表示
 * 方便起见，因为每个节点都只会有一条指向自己的链接(从它的父节点指向它), 我们将链接的颜色保存在节点的 Node 数据类型的
 * 布尔变量 color 中。如果指向它的链接是红色的，那么该变量为 true,黑色则为 false. 我们约定空链接为黑色。
 */
// 将  黑--红 或者  红--红 反正就是子节点是红色的 链接认为是红链接
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private enum Color {
        RED,
        BLACK
    }

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;
        Color color;

        Node(Key key, Value value, int N, Color color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }

        public String toString() {
            return "{key: " + key + ", Val: " + value + "}" + "  {Color: " + (color == Color.RED ? "RED" : "BLACK") + "}";
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        if (x.color == Color.RED) return true;
        else return false;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key) {
        if (root == null)
            return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0)
            return get(root.left, key);
        else if (cmp > 0)
            return get(root.right, key);
        else
            return root.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        //注意根节点颜色的区分
        root.color = Color.BLACK;
    }

    public Node put(Node node, Key key, Value value) {
        //根节点初始化是black
        if (node == null) return new Node(key, value, 1, Color.RED);
        // 有新元素时
        int cmp = key.compareTo(node.key);
        // 每插入一个节点，就对它进行着色
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0)
            node.right = put(node.right, key, value);

        if (!isRed(node.left) && isRed(node.right)) node = rotateLeft1(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight1(node);
        if (isRed(node.left) && isRed(node.right)) flipColorsPut(node);
        return node;
    }

    /**
     * 因为每个节点都只会有一条指向自己的链接(从它的父节点指向它), 我们将链接的颜色保存在节点的 Node 数据类型的
     * 布尔变量 color 中
     * <p>
     * 因为每个节点都只会有一条指向自己的链接(从它的父节点指向它)，我们讲链接的颜色保存在节点的 Node 的 color 成员变量中
     */
    private Node rotateLeft1(Node h) {
        Node node = h.right;
        // 因为不知道之前指向 h 的链接的颜色，所有只能继承 h 的 color
        node.color = h.color;
        // 因为旋转了 所以指向 node 的链接也就变成了指向 h 的链接，它需要继承链接的颜色。
        // 只有 红色需要旋转 所以直接赋予红色
        h.color = Color.RED;
        h.right = node.left;
        node.left = h;
        return node;
    }

    private Node rotateRight1(Node h) {
        Node node = h.left;
        node.color = h.color;
        h.color = Color.RED;

        node.left = h.right;
        h.right = node;
        return node;
    }

    private void flipColorsPut(Node h) {
        h.color = Color.RED;
        h.left.color = Color.BLACK;
        h.right.color = Color.BLACK;
    }

    /**
     * 反着来 将 三个 2- 节点变成一个 4- 节点
     *
     * @param h
     */
    private void flipColorsDel(Node h) {
        h.color = Color.BLACK;
        h.right.color = Color.RED;
        h.left.color = Color.RED;
    }

    private Node moveRedLeft(Node h) {
        // 假设节点 h 为红色，h.left 和 h.left.left 都是黑色
        // 将 h.left 或者 h.left 的子节点之一变红
        flipColorsDel(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight1(h.right);
            h = rotateLeft1(h);
        }
        return h;
    }

    public void deleteMin() {
        //  如果根节点是 2- 节点就将其变为 3- 节点。
        // 根节点变为红色不理解 root 为红，不就代表着链接这个根节点的那条线是红，可是根节点没有父节点了
        // 应该是特指 root 是 3- 节点;
        if (!isRed(root.left) && !isRed(root.right))
            root.color = Color.RED;
        root = deleteMin(root);
        if (true) root.color = Color.BLACK;
    }

    private Node deleteMin(Node h) {
        // 如果 root.left 没有值说明,就一个 root , root 被删除以后就返回 null
        // 这里将 min 删除了; 因为 h.left == null 相当于找到了 min h;
        // 然后直接返回 null 赋予 父节点的.left
        if (h.left == null) {
            return null;
        }
        // 只要到了这里 h 必定是 3- 节点
        // h.left h.left.left 都为黑 说明 h 是 2- 节点。
        // h.left 为 red 说明和 h 是 3- 节点
        // h.left.left 为 red 说明 h.left 是 3- 节点
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node balance(Node h) {
        if (isRed(h.right))
            h = rotateLeft1(h);
        if (!isRed(h.left) && isRed(h.right)) h = rotateLeft1(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight1(h);
        if (isRed(h.left) && isRed(h.right)) flipColorsPut(h);
        return h;
    }

}

/**
 * 在第二轮热身中我们要学习 2-3 树中删除最小键的操作。我们注意到从树底部的 3- 节点中删除
 * 一个键是很简单的，但 2- 节点则不然。从 2- 节点中删除一个键会留下一个空节点，一般我们会将
 * 它替换为一个空链接，但这样会破坏树的完美平衡性。所以我们需要这样做：为了保证我们不会删除一个 2-
 * 节点，我们沿着左链接向下进行变换，确保当前节点不是 2- 节点(可能是 3- 节点，也可能是临时的 4- 节点)。
 * 首先，根节点可能有两种情况。
 * 如果根是 2- 节点且它的两个子节点都是 2- 节点，我们可以直接将这三个节点变为
 * 一个 4- 节点;
 * 否则我们需要保证根节点的左子节点不是 2- 节点,如有必要可以从它的右侧的兄弟节点 " 借过来 "一个键过来。
 * 在沿着左链接向下的过程中保证以下两种情况之一成立
 *   -- 如果当前节点的左子节点不是 2- 节点，完成
 *   -- 如果当前节点的左子节点是 2- 节点,而它的亲兄弟节点不是 2- 节点，将左子节点的兄弟节点中的一个键移动到左子节点。
 *   -- 如果当前节点的左子节点和他的亲兄弟节点都是 2- 节点，将左子节点、父节点中的最小键和左子节点最近的兄弟节点合并为一个
 *          4- 节点，使父节点由 3- 节点变为 2- 节点或者由 4- 节点变为 3- 节点。
 *
 *  在遍历的过程中执行这个过程，最后能够得到一个含有最小键的 3- 节点或者 4- 节点。然后我们就可以直接从中将其删除，
 *  将 3- 节点变为 2- 节点或者由 4- 节点变为 3- 节点。
 */
    /**
     * 删除操作
     * <p>
     * 在查找路径上进行和删除最小键相同的变换同样可以保证在查找过程中任意当前节点均不是 2- 节点。
     * 如果被查找的键在树的底部我们可以直接删除它。
     * 如果不在，我们需要将它和它的后续节点交换，就和二叉查找树一样。
     * 因为当前节点必然不是 2- 节点，问题就已经转化为在一颗根节点不是 2- 结点的自述中删除最小的键，
     * 我们可以在这棵子树中使用前文所述的算法。和以前一样，删除之后我们需要向上回溯并分解余下的 4- 节点。
     */


