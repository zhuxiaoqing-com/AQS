package com.example.tree.redBlackTree;

import org.junit.Test;

import javax.swing.tree.TreeNode;


class TreeNode1 {
    TreeNode1 parent;  // red-black tree links
    TreeNode1 left;
    TreeNode1 right;
    TreeNode1 prev;    // needed to unlink next upon deletion
    boolean red;
}

public class Rotate {
    TreeNode1 p = new TreeNode1();
    TreeNode1 r = new TreeNode1();
    TreeNode1 root = new TreeNode1();

    /**
     * left Rotate
     */
    @Test
    public void fun1() {
        // p left rotate
        TreeNode1 r = p.right;
        p.right = r.left;
        if(r.left != null) {
            r.parent = p;
        }

        r.parent = p.parent;
        if(p.parent == null) {
            root = r.parent;
        } else if(p.parent.left == p) {
            p.parent.left = r;
        } else {
            p.parent.right = r;
        }
        r.left = p;
        p.parent = r;
    }

    /**
     * left Rotate
     */
    @Test
    public void fun2() {
        TreeNode1 x = r.right;
        r.right = x.right;
        if(x.right.parent != null) {
            x.right.parent = r;
        }
        x.parent = r.parent;
        if(r.parent == null) {
            root = x;
        } else if (r.parent.left == r) {
            r.parent.left = x;
        } else {
            r.parent.right = x;
        }
        x.left = r;
        r.parent = x;
    }
    /**
     * left Rotate
     */
    @Test
    public void fun3() {
        TreeNode1 q = r.right;
        r.right = q.left;
        if(q.left != null) {
            q.left.parent = r;
        }

        q.parent = r.parent;
        if(r.parent == null) {
            root = q;
        } else if(r.parent.left == r) {
            r.parent.left = q;
        } else {
            r.parent.right = q;
        }

        r.parent = q;
        q.left = r;
    }

    /**
     * right Rotate
     */
    @Test
    public void fun4() {
        // r right Rotate
        TreeNode1 q = r.left;
        r.left = q.right;
        if(q.right != null) {
            q.right.parent = r;
        }

        q.parent = r.parent;
        if(r.parent == null) {
            root = q;
        } else if(r.parent.right == r) {
            r.parent.right = q;
        } else {
            r.parent.left = q;
        }

        q.right = r;
        r.parent = q;
    }
}

