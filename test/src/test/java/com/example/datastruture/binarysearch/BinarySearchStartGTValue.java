package com.example.datastruture.binarysearch;

/**
 * 查找第一个大于等于给定值的元素
 */
public class BinarySearchStartGTValue {
    public int bGTStartSearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] >= value) {
                // 第一个大于等于给定值的元素
                /**
                 * 那么就是前一个是 < value 的下一个就是第一个大于等于给定的值的元素
                 */
                if (mid == 0 || array[mid - 1] < value) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
