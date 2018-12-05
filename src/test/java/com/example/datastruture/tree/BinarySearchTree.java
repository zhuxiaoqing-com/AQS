package com.example.datastruture.tree;

/**
 * 二叉树
 */
public class BinarySearchTree {
    private Node tree;

    /**
     * 二叉树查找
     *
     * @param data
     * @return
     */
    public Node find(int data) {
        Node temp = tree;
        while (temp != null) {
            if (data < temp.data) {
                temp = temp.left;
            } else if (data > temp.data) {
                temp = temp.right;
            } else {
                return temp;
            }
        }
        return null;
    }

    public boolean insert(int data) {
        if (tree == null) {
            tree = new Node(data);
        }
        Node temp = tree;
        while (temp != null) {
            if (data < temp.data) {
                if (temp.left == null) {
                    temp.left = new Node(data);
                    return true;
                }
                temp = temp.left;
            } else if (data > temp.data) {
                if (temp.right == null) {
                    temp.right = new Node(data);
                    return true;
                }
                temp = temp.right;
            } else {
                // 相等就直接返回
                return false;
            }
        }
        return false;
    }

    /**
     * 有三种情况
     * 1、要删除的节点没有子节点，我们只需要直接将父节点中，指向要删除节点的指针置为 null;
     * 2、要删除的节点只有一个子节点(左子节点或右子节点)，我们只需要更新父节点中，只想要删除
     * 节点的指针，让它指向要删除节点的子节点就可以了。
     * <p>
     * 3、如果要删除的节点有两个子节点，这就比较复杂了。我们需要找到这个节点的右子树中的最小节点，
     * 把他替换到要删除的节点上，然后在删除这个最小节点。
     * 当然也可以左子节点的最大节点
     *
     * @param data
     * @return
     */
    public boolean delete(int data) {
        /**
         * 先查询 查询不到不删除，查询到了再删除
         */
        Node p = tree;
        Node pp = null;

        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) {
            return false;
        }

        if (p.left != null && p.right != null) {
            Node minP = p.right;
            Node minPP = p;
            // 找到了进行删除
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; // 将 p 中的数据替换为 右边的最小数据
            p = minP; // 将 p 替换为最小值 后面会帮我们删除
            pp = minPP; // pp 替换为 p 的父节点
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p 的子节点
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        if (pp == null) tree = child; // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;

        return false;
    }

    public void delete02(int data) {
        // 先查询
        Node temp = tree;
        // 记录节点的父节点
        Node parent = null;

        while (temp != null && temp.data != data) {
            parent = temp;
            if (data < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        // 没找到直接返回
        if (temp == null) {
            return;
        }

        if (temp.left != null && temp.right != null) {
            Node minTemp = temp.right;
            Node minParent = temp;
            while (minTemp.left != null) {
                minParent = minTemp;
                minTemp = minTemp.left;
            }
            temp.data = minTemp.data;
            // 然后将其转换为 要删除的节点为只有一个左节点或右节点
            temp = minTemp;
            parent = minParent;
        }

        Node child;
        // 必须先 left 因为上面需要删除  left
        if (temp.left != null) {
            child = temp.left;
        } else if (temp.right != null) {
            child = temp.right;
        } else {
            // 因为 Left 和 right 都没有所以直接将本节点删除就好了
            child = null;
        }
        // 删除的是根节点
        if(parent == null) {
            tree = child;
        } else if(parent.left == temp) {
            parent.left = child;
        } else {
            parent.right = child;
        }

    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
