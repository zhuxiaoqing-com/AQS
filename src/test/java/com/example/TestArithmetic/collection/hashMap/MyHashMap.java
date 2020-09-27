package com.example.TestArithmetic.collection.hashMap;

public class MyHashMap {
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    static final class TreeNode<K, V> extends MyHashMap.Node<K, V> {
        MyHashMap.TreeNode<K, V> parent;  // red-black tree links
        MyHashMap.TreeNode<K, V> left;
        MyHashMap.TreeNode<K, V> right;
        MyHashMap.TreeNode<K, V> prev;    // needed to unlink next upon deletion
        boolean red;

        TreeNode(int hash, K key, V val, MyHashMap.Node<K, V> next) {
            super(hash, key, val, next);
        }

       /* final MyHashMap.TreeNode<K, V> find(int h, Object k, Class<?> kc) {
            MyHashMap.TreeNode<K, V> p = this;
            do {
                *//**
                 * ph：parent Hash
                 * dir:
                 * pk: parent Key
                 *//*
                int ph, dir;
                K pk;
                TreeNode<K, V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h)
                    p = p.left;
                else if (ph < h)
                    p = p.right;
                else if ((pk = p.key) == k || (k != null && k.equals(p.key)))
                    return p;
                else if(pl == null)
                    p = pr;
                else if(pr == null)
                    p = pl;
                else if(kc != null)
            } while (p != null);
        }*/

    }

}
