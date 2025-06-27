package com.example.datastruture.sort;

// 冒泡排序，a 表示数组，n 表示数组大小
public class Sort {
    public void bubble(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j + 1]) { // 这行注释没用 改成 compare.(a[j],a[j+1])  > 1
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;// 代表有数据交换
                }
            }
            if (!flag) break; // 没有数据交换，提前退出
        }
    }

    /**
     * 插入排序
     * 分为两个区间已经排序区间，没有排序区间
     * 每次区未排序区间的第一个来进行往已排序区间的比较。找到合适的位置插入
     */
    public void insertionSort(int[] a, int n) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int x = i - 1;
            for (; x >= 0; x--) {
                if (temp < a[x]) {
                    a[x + 1] = a[x];
                } else {
                    break;
                }
            }
            a[x + 1] = temp;// 插入数据
        }
    }
}























