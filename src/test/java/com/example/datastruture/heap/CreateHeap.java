package com.example.datastruture.heap;

public class CreateHeap {
    public void buildHeap(int[] a) {
        int n = a.length;
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, i, n);
        }
    }

    /**
     * 要从根节点不断往下堆化
     *
     * @param a
     * @param i
     */
    private void heapify(int[] a, int i, int n) {
        while (true) {
            int maxPos = i;
            if (i * 2 >= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 >= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) {
                break;
            }
            i = maxPos;
        }
    }

    private void swap(int[] a, int i, int i1) {
        int temp = a[i];
        a[i] = a[i1];
        a[i1] = temp;
    }


    public void sort(int[] a) {
        buildHeap(a);
        int k = a.length;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }
}
