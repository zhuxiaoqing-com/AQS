package com.youxi.match.util;

import java.util.*;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/24 11:55
 * @Description:
 */
public class Utils {

	public static void main(String[] args) {
		Utils utils = new Utils();
		utils.fun(4,4);
		for (List<Integer> list : utils.lists) {
			for (Integer integer : list) {
				System.out.print(integer);
				System.out.print(" + ");
			}
				System.out.println();
		}
	}

	List<List<Integer>> lists = new ArrayList<>();
	List<Integer> list = new ArrayList<>();

	void fun(int val, int s) {
		if(val == 0) {
			lists.add(new ArrayList<>(list));
			return;
		}
		for (int i = val; i <= s && i>0; i--) {
			list.add(i);
			fun(val - i, val-i);
			list.remove(list.size()-1);
		}

	}

/*	void fun(int val, int s) {
		if(val == 0) {
			lists.add(new ArrayList<>(list));
			return;
		}
		for (int i = s; i <= val; i++) {
			list.add(i);
			fun(val - i, i);
			list.remove(list.size()-1);
		}

	}*/


}
