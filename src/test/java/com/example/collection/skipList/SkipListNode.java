package com.example.collection.skipList;

public class SkipListNode<T> {
    public int key;
    public T value;
    public SkipListNode<T> down, right;// 上下左右四个指针

    public SkipListNode(int key, SkipListNode<T> down) {
        this.key = key;
        this.down = down;
    }

    public SkipListNode(int key, SkipListNode<T> right, SkipListNode<T> down) {
        this.key = key;
        this.right = right;
        this.down = down;
    }

    public SkipListNode(int key, T value, SkipListNode<T> right, SkipListNode<T> down) {
        this.key = key;
        this.value = value;
        this.down = down;
        this.right = right;
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

    public SkipListNode<T> getDown() {
        return down;
    }

    public void setDown(SkipListNode<T> down) {
        this.down = down;
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
        SkipListNode<T> ent = (SkipListNode<T>) o;
        return (ent.getKey() == key) && (ent.getValue() == value);
    }

    @Override
    public String toString() {
        return "key-value:" + key + "-" + value;
    }
}
