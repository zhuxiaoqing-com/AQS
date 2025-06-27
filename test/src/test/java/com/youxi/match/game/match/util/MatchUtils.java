package com.youxi.match.game.match.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/24 11:55
 * @Description:
 */
public class MatchUtils {


	/**
	 * 正整数 sum 所有可能的和式的组合（如； 4=1+1+1+1、 1+1+2、 1+3、2+2、4）
	 * @param maxNum 最大可以拿来组合的数;
	 */
	public static List<List<Integer>> finAdd(int sum, int maxNum) {
		int tempSum = 0;
		List<Integer> v = new ArrayList<>();
		List<List<Integer>> lists = new ArrayList<>();
		findAdd(sum, maxNum, tempSum, lists, v);
		return lists;
	}

	 private static void findAdd(int sum, int start, int tempSum, List<List<Integer>> lists, List<Integer> list) {
		if (tempSum == sum) {
			lists.add(new ArrayList<>(list));
			return;
		}

		if (tempSum > sum) {
			return;
		}

		for (int i = start; i > 0; i--) {
			list.add(i);
			findAdd(sum, i, tempSum + i, lists, list);
			list.remove(list.size() - 1);
		}
	}


	public static void main(String[] args) {
	/*	int count = 1000;

		long startNano = System.nanoTime();

		for (int i = 0; i < count; i++) {
			finAdd(10, 5);
		}
		System.out.println("____nano:" + (System.nanoTime() - startNano) + "   mill: " + (System.nanoTime() - startNano)/1000_000);
*/
		List<List<Integer>> lists = finAdd(10, 5);
		print(lists);
	}

	private static void print(List<List<Integer>> add) {
		for (List<Integer> list : add) {
			for (Integer integer : list) {
				System.out.print(integer);
				System.out.print(" + ");
			}
			System.out.println();
		}
	}
}
