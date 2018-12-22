package com.example.datastruture.binarysearch;


public class BinarySearch {

    public int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            // (low + high) /2
            int mid = low + (high - low) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 递归版实现
     */
    public int recursionBinarySearch(int[] array, int value) {
        return doRecursionBinarySearch(array, 0, array.length - 1, value);
    }

    private int doRecursionBinarySearch(int[] array, int low, int high, int value) {
        if (high < low) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (array[mid] == value) {
            return mid;
        } else if (array[mid] > value) {
            return doRecursionBinarySearch(array, 0, mid - 1, value);
        } else {
            return doRecursionBinarySearch(array, mid + 1, high, value);
        }
    }
}
