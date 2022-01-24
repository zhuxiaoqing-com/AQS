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

	List<List<Integer>> lists = new ArrayList<>();
	List<Integer> list = new ArrayList<>();

	void findAdd(int sum, int start, int tempSum, List<List<Integer>> lists, List<Integer> list) {
		if (tempSum == sum) {
			lists.add(new ArrayList<>(list));
			//System.out.println(list);
			return;
		}

		if (tempSum > sum) {
		/*	lists.add(new ArrayList<>(list));
			System.out.println(list);*/
			return;
		}

		for (int i = start; i > 0; i--) {
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

	/**
	 *
	 */
	@Test
	public void test01() {
		List<List<Integer>> add = add(4);

		for (List<Integer> list : add) {
			for (Integer integer : list) {
				System.out.print(integer);
				System.out.print(" + ");
			}
			System.out.println();
		}
	/*	TestUtil.testTime(() -> {
			lists.clear();
			list.clear();
		}, 1, "a");*/

	}



	/**
	 * 1272765400
	 * 1211120400
	 *
	 * 1378292100
	 * 1215608900
	 *
	 * 1893590400
	 */
	@Test
	public void test02() {
		TestUtil.testTime(() -> {
			fun1(62, 1);
			lists.clear();
			list.clear();
		}, 20, "a");
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
