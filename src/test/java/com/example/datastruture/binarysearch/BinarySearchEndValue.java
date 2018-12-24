package com.example.datastruture.binarysearch;

/**
 * 查找最后一个值等于给定值的元素
 */
public class BinarySearchEndValue {
    public int endBSearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (high >= 0 && array[high] == value) {
            return high;
        } else {
            return -1;
        }
    }

    public int endBSearch02(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == array.length - 1 || array[mid + 1] != value) {
                    return mid;
                } else {
                    // 不是的话肯定在 [mid + 1, high] 里面
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
