package com.youxi.match.util;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/24 11:55
 * @Description:
 */
public class Utils {

	List<List<Integer>> lists = new ArrayList<>();
	List<Integer> list = new ArrayList<>();

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

	@Test
	public void test01() {
		fun1(4, 1);
		for (List<Integer> list : lists) {
			for (Integer integer : list) {
				System.out.print(integer);
				System.out.print(" + ");
			}
			System.out.println();
		}
	}


	@Test
	public void test02() {
		fun2(0,4);
		System.out.println(number);

		for (List<Integer> list : lists) {
			for (Integer integer : list) {
				System.out.print(integer);
				System.out.print(" + ");
			}
			System.out.println();
		}
	}

	int[] a = new int[30];//保存分解的状况

	int example;
	int number;

	void fun2(int sum, int k) {
		if (sum == example) {
			number++;
			return;
		}
		for (int i = k; i >= 1; i--) {
			sum += i;
			if (sum <= example) {
				fun2(sum, i);
			}
			sum -= i;
		}
	}


	void fun3(int n, int s) {
		if (n == 0) {
			lists.add(new ArrayList<>(list));
			return;
		}

		fun3(n - s, n - s);
	}


}
