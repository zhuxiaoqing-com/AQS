package com.example.datastruture.divideandrule;

/**
 * 分治函数
 * 求出一组数据的有序对个数或者逆序对个数
 */
public class CountReversed {
    /**
     * 逆序的个数
     */
    private int num = 0;

    public int count(int[] array) {
        num = 0;
        mergeSortCounting(array, 0, array.length - 1);
        return num;
    }

    /**
     * @param array 数组
     * @param start 开始下标
     * @param end   结束下标
     */
    private void mergeSortCounting(int[] array, int start, int end) {
        if (start >= end) return;
        int center = (start + end) / 2;
        mergeSortCounting(array, start, center);
        mergeSortCounting(array, center + 1, end);
        merge(array, start, center, end);
    }

    private void merge(int[] array, int start, int center, int end) {
        int begin = start;
        int center1 = center + 1;
        int k = 0;
        int[] temp = new int[end - start + 1];

        while (begin <= center && center1 <= end) {
            if (array[begin] <= array[center1]) {
                /**
                 * 为什么这里要这样子  看图 看图 看图！！！
                 * 因为只要有一个逆序了，那后面正顺序的都和那前面一个逆序的形成了  顺序的。
                 */
                num += (center1 - center - 1);
                temp[k] = array[begin];
                begin++;
            } else {
                temp[k] = array[center1];
                center1++;
            }
            k++;
        }

        while (begin <= center) {
            num += (center1 - center - 1);
            temp[k++] = array[begin++];
        }
        while (center1 <= end) {
            temp[k++] = array[center1++];
        }
        for (int i = 0; i < end - start; i++) {
            array[start + i] = temp[i];
        }
    }
}
