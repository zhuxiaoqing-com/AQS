package com.example.datastruture.a_44_shortest_path_algorithm2;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/29 15:26
 * @Description:
 */
public class Heap<E> {
	private Object[] queue;
	private final Comparator<? super E> comparator;
	// 堆里面已经存储的元素
	private int count;
	// 堆最大可以存储的元素
	private int maxCount;

	public Heap(int capacity, Comparator<? super E> comparator) {
		this.queue = new Object[capacity];
		this.comparator = comparator;
	}

	public Heap(E[] queue, Comparator<? super E> comparator) {
		this.comparator = comparator;
		this.queue = queue;
		this.count = queue.length - 1;
		this.maxCount = queue.length - 1;

		buildHeap(queue, count);
	}

	private void buildHeap(E[] a, int count) {
		for (int i = count / 2; i >= 1; --i) {
			// 获取非叶子节点的左右节点 进行比较
			int left = i * 2;
			int right = i * 2 + 1;
			// 进行校正 怕 right 超出范围
			right = right >= count ? left : right;
			int max = comparator.compare(a[left], a[right]) >= 0 ? left : right;
			max = comparator.compare(a[i], a[max]) >= 0 ? i : max;
			// 进行交换
			if (max != i) {
				swap(a, i, max);
			}
		}
	}

	private void swap(Object[] a, int i, int maxPos) {
		Object temp = a[i];
		a[i] = a[maxPos];
		a[maxPos] = temp;
	}


	/**
	 * 往最后添加元素
	 * <p>
	 * 子节点和父节点比较就好了
	 * 不需要左右节点和父节点三个比较
	 * 因为插入之前堆就已经是好的了
	 * 比如 1->2 一开始两个节点
	 * 然后插入3 变为 1->2->3
	 * 你只需要比较 (1,3) 就好了; 因为之前就是堆化好了所以; 1肯定>2
	 *
	 * @param e
	 */
	public void addLast(E e) {
		if (count == maxCount) {
			System.out.println("堆已经最大了 没有实现扩充功能 所以插入失败");
			return;
		}
		count++;

		int curr = count;
		while (curr / 2 > 0) {
			if (comparator.compare((E) queue[curr], (E) queue[curr / 2]) <= 0) {
				break;
			}
			swap(queue, curr, curr / 2);
			curr = curr / 2;
		}
	}

	/**
	 * 删除头元素
	 *
	 * @return
	 */
	public E removeFirst() {
		Object returnValue = queue[1];
		queue[1] = queue[count];
		count--;

		return (E) returnValue;
	}

	private void heapify(E[] a, int n, int i) {
		while (true) {
			int posMax = i;
			int left = i * 2;
			int right = i * 2 + 1;
			// 进行校正 怕 right 超出范围
			right = right >= n ? left : right;
			int max = posMax;
			if (left < n && comparator.compare(a[left], a[i]) >= 0) {
				max = left;
			}
			if (right < n && comparator.compare(a[right], a[max]) >= 0) {
				max = right;
			}

			max = comparator.compare(a[i], a[max]) >= 0 ? i : max;

			// 已经堆化
			if (max == i) {
				break;
			}
			swap(a, i, max);
			i = posMax;
		}
	}


	@Test
	public void e() {
		PriorityQueue<Object> objects = new PriorityQueue<>();
		objects.add(new Object());
		objects.add(new Object());
		objects.add(new Object());
		objects.add(new Object());
	}
}
