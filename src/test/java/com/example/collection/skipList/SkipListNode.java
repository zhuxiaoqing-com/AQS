package com.example.collection.skipList;

import java.util.Objects;

public class SkipListNode<T> {
    public int key;
    public T value;
    public SkipListNode<T> up, down, left, right;// 上下左右四个指针
    public static int HEAD_KEY = 0x80000000; // 负无穷
    public static int TAIL_KEY = 0x7FFFFFFF; // 正无穷

    public SkipListNode(int k, T v) {
        key = k;
        value = v;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SkipListNode<T> getUp() {
        return up;
    }

    public void setUp(SkipListNode<T> up) {
        this.up = up;
    }

    public SkipListNode<T> getDown() {
        return down;
    }

    public void setDown(SkipListNode<T> down) {
        this.down = down;
    }

    public SkipListNode<T> getLeft() {
        return left;
    }

    public void setLeft(SkipListNode<T> left) {
        this.left = left;
    }

    public SkipListNode<T> getRight() {
        return right;
    }

    public void setRight(SkipListNode<T> right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof SkipListNode<?>)) {
            return false;
        }
        SkipListNode<T> ent = (SkipListNode<T>)  o;
        return (ent.getKey() == key) && (ent.getValue() == value);
    }

    @Override
    public String toString() {
        return "key-value:"+key+"-"+value;
    }
}
