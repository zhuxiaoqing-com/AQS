package com.youxi.match.util;

import com.example.demo4.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/24 11:55
 * @Description:
 */
public class Utils2 {

	int forCount;
	List<List<Integer>> lists = new ArrayList<>();
	List<Integer> list = new ArrayList<>();

	void findAdd(int sum, int start, int tempSum, List<List<Integer>> lists, List<Integer> list) {
		if (tempSum == sum) {
			lists.add(new ArrayList<>(list));
			return;
		}

		if (tempSum > sum) {
			return;
		}

		for (int i = start; i > 0; i--) {
			forCount++;
			list.add(i);
			findAdd(sum, i, tempSum + i, lists, list);
			list.remove(list.size() - 1);
		}
	}
	List<List<Integer>> add(int sum) {
		int tempSum = 0;
		List<Integer> v = new ArrayList<>();
		List<List<Integer>> lists = new ArrayList<>();
		findAdd(sum, sum, tempSum, lists, v);
		return lists;
	}

	void findAdd1(int sum, int start, int tempSum, List<List<Integer>> lists, List<Integer> list) {
		if (tempSum == sum) {
			lists.add(new ArrayList<>(list));
			return;
		}

		if (tempSum > sum) {
			return;
		}

		int min = Math.min(sum - tempSum, start);
		if(tempSum != 0) {
			int a = 0;
		}
		for (int i = min; i > 0; i--) {
			forCount++;
			list.add(i);
			findAdd1(sum, i, tempSum + i, lists, list);
			list.remove(list.size() - 1);
		}
	}

	List<List<Integer>> add1(int sum) {
		int tempSum = 0;
		List<Integer> v = new ArrayList<>();
		List<List<Integer>> lists = new ArrayList<>();
		findAdd1(sum, sum, tempSum, lists, v);
		return lists;
	}


	/**
	 *
	 */
	@Test
	public void test01() {
		List<List<Integer>> add = add(5);
		print(add);
	}

	@Test
	public void test03() {
		int sum = 50;
		int maxNum = 1;
		TestUtil.testTime(() -> {
			add1(sum);
		}, maxNum, "a1");

		System.out.println(forCount);
		forCount=0;
		TestUtil.testTime(() -> {
			add(sum);
		}, maxNum, "a");
		System.out.println(forCount);

	}

	@Test
	public void test04() {
		int count = 40;
		for (int i = 0; i < count; i++) {
			List<List<Integer>> add = add(i);
			List<List<Integer>> add1 = add1(i);
			if(!equals(add,add1)) {
				System.out.println("error + "+count);
			}
		}
	}

	private void print(List<List<Integer>> add) {
		for (List<Integer> list : add) {
			for (Integer integer : list) {
				System.out.print(integer);
				System.out.print(" + ");
			}
			System.out.println();
		}
	}


	private boolean equals(List<List<Integer>> add, List<List<Integer>> add1) {
		if(add.size() != add1.size()) {
			return false;
		}
		for (int i = 0; i < add.size(); i++) {
			List<Integer> list = add.get(i);
			List<Integer> list1 = add1.get(i);
			if(list.size() != list1.size()) {
				return false;
			}
			for (int i1 = 0; i1 < list.size(); i1++) {
				Integer integer = list.get(i1);
				Integer integer2 = list1.get(i1);
				if (!integer.equals(integer2)) {
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * 1272765400
	 * 1211120400
	 * <p>
	 * 1378292100
	 * 1215608900
	 * <p>
	 * 1893590400
	 */
	@Test
	public void test02() {
		TestUtil.testTime(() -> {
			fun1(10, 1);
			lists.clear();
			list.clear();
		}, 50, "a");
	/*	for (List<Integer> list : lists) {
			for (Integer integer : list) {
				System.out.print(integer);
				System.out.print(" + ");
			}
			System.out.println();
		}*/
	}

	void fun1(int val, int s) {
		if (val == 0) {
			lists.add(new ArrayList<>(list));
			return;
		}
		for (int i = s; i <= val; i++) {
			list.add(i);
			fun1(val - i, i);
			list.remove(list.size() - 1);
		}

	}
}
