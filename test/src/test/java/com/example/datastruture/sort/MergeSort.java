package com.example.datastruture.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 */
public class MergeSort {
    /**
     * 公式推导
     * merge_sort(p...r) = merge_sort(0, q) + merge_sort(q+1, r);
     */
    public void mergeSort(int[] array) {
        if (array.length == 0) {
            return;
        }
        int[] tempArray = new int[array.length];
        mergeSort(array, 0, array.length - 1, tempArray);
    }

    /**
     * 可以获取的下标
     *
     * @param array
     * @param start     开始下标
     * @param end       结束下标
     * @param tempArray
     */
    private void mergeSort(int[] array, int start, int end, int[] tempArray) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid, tempArray);
            mergeSort(array, mid + 1, end, tempArray);
            // 进行合并
            mergeArray(array, start, mid, end, tempArray);
        }
    }

    // 将有二个有序数列a[first...mid]和a[mid...last]合并。
    private void mergeArray(int[] array, int start, int mid, int end, int[] tempArray) {
        int first = start, center1 = mid;
        int last = end, center2 = mid + 1;
        int index = start;
        while (first <= center1 && center2 <= last) {
            // 这里 <= 就可以让这个排序变为稳定排序
            /**
             * 稳定排序 如果有原数组有相同的值，那么排序后相同的值的位置没有发生变化，就可以称为
             * 这个排序为稳定排序。
             */
            if (array[first] <= array[center2]) {
                tempArray[index] = array[first];
                first++;
            } else {
                tempArray[index] = array[center2];
                center2++;
            }
            index++;
        }
        // 查看哪个里面有剩余的数据
        /**
         * 默认 第一个数组
         * 然后验证第二个数组看下是不是正确
         */
        int reduceFirst = first;
        int reduceEnd = center1;

        if (center2 <= last) {
            reduceFirst = center2;
            reduceEnd = last;
        }

        // 将剩余的数据复制进去
        while (reduceFirst <= reduceEnd) {
            tempArray[index++] = array[reduceFirst++];
        }

        // 将 tempArray 数组拷贝回原有数组
        for (int i = start; i <= end; i++) {
            array[i] = tempArray[i];
        }
    }

    @Test
    public void test01() {
        int[] x = new int[]{2,5,3,77,32,1};
        MergeSort sort = new MergeSort();
        //sort.mergeSort();
        mergeSort(x);
        System.out.println(Arrays.toString(x));
    }

}
