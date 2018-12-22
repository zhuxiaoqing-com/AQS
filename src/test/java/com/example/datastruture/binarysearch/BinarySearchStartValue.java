package com.example.datastruture.binarysearch;

/**
 * 查找第一个值等于给定值的元素
 */
public class BinarySearchStartValue {
    public int bSearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            // >> 的优先级 低于 +
            int mid = low + ((high - low) >> 1);
            if (array[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low < array.length && array[low] == value) return low;
        else return -1;

    }
}
