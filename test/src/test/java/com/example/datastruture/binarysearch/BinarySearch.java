package com.example.datastruture.binarysearch;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

// 二分查找的递归实现
	public int bsearch(int[] a, int n, int val) {
		return bsearchInternally(a, 0, n - 1, val);
	}

	private int bsearchInternally(int[] a, int low, int high, int value) {
		if (low > high) return -1;

		int mid = low + ((high - low) >> 1);
		if (a[mid] == value) {
			return mid;
		} else if (a[mid] < value) {
			return bsearchInternally(a, mid + 1, high, value);
		} else {
			return bsearchInternally(a, low, mid - 1, value);
		}
	}


	int bsearchInternally1(int[] list, int n, int n1, int key) {
		int low = 0;
		int high = list.length - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			int cmp = list[mid] - key;

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1);  // key not found
	}


	int bsearchInternally11(List<Long> list, Map<Long, Double> dataMap, double score, Comparator<Double> comparator) {
		int low = 0;
		int high = list.size() - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			double cmp = comparator.compare(dataMap.get(list.get(mid)), score);
			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else {
				for (int i = mid; i < list.size(); i++) {

					if (comparator.compare(dataMap.get(list.get(i)), score) > 0) {
						return i;
					}
				}
				return mid + 1; // key found
			}
		}
		return low;  // key not found
	}
}
