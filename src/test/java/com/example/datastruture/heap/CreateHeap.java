package com.example.datastruture.heap;

public class CreateHeap {
	public void buildHeap(int[] a) {
		int n = a.length - 1;
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
		// 这里为什么要无限 while 循环
		while (true) {
			int maxPos = i;
			if (i * 2 <= n && a[i * 2] > a[i]) maxPos = i * 2;
			if (i * 2 + 1 <= n && a[i * 2 + 1] > a[maxPos]) maxPos = i * 2 + 1;
			if (maxPos == i) {
				break;
			}
			swap(a, i, maxPos);
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

/* for i := 0; i <= 10; i-- {
		int maxIndex = i;
		if a[i] < a[i*2] {
				maxIndex = i*2;
		}
		if(i*2+1<= count && a[maxIndex] < a[i*2+1]) {
			maxIndex = i*2 +1;
		}
		if(maxIndex == i) {
			continue;
		}
		swap(a, i, maxIndex);
	}*/

}
