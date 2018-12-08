package com.example.datastruture.heap;

public class Heap {
    private int[] a; // 数组，从下标 1 开始存储
    private int n; // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    // capacity  容量 能力
    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) { // 自下往上堆化
            swap(a, i, i / 2);
            i = i / 2;
        }
    }

    /**
     * 删除堆顶
     */
    public void removeMax() {
        if (count == 0) return;
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    private void heapify(int[] a, int count, int i) {
        int temp;
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            /**
             * 如果和要交换的位置相等就是 没有要交换的位置
             * 既然没有那么就已经平衡了
             */
            if(maxPos == i) break;
            i = maxPos;
        }
    }


    private void swap(int[] a, int i, int i1) {
        int temp = a[i];
        a[i] = a[i1];
        a[i1] = temp;
    }

}
