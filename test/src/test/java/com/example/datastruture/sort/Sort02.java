package com.example.datastruture.sort;

// 冒泡排序，a 表示数组，n 表示数组大小
public class Sort02 {
    public void bubble(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            boolean flag = false;
            for (int y = 0; y < a.length - i - 1; i++) {
                if (a[y] > a[y - 1]) {
                    int temp = a[y];
                    a[y] = a[y - 1];
                    a[y - 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    /**
     * 插入排序
     * 分为两个区间已经排序区间，没有排序区间
     * 每次区未排序区间的第一个来进行往已排序区间的比较。找到合适的位置插入
     */
    public void insertionSort(int[] a, int n) {
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (value < a[j]) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;

        }
    }

    /**
     * 选择排序
     *
     * @param a
     * @param n
     */
    public void selectSort(int[] a, int n) {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;
            for (int j = i+1; j < a.length; j++) {
                if(a[i] > a[j]) {
                    index = j;
                }
            }
            int temp = a[i];
            a[i] = a[index];
            a[index] = temp;

        }
    }

}























