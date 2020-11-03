package com.example.javase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/11/2 10:26
 * @Description:
 */
public class CreateHashCodeSomeUtil {

	/**
	 * str hashcode计算
	 * 31*x + y = 31*a + b
	 * 31*x - 31*a = b - y;
	 * 31*(x - a) = b - y;
	 * x - a = 1;
	 * b - y = 31;
	 *
	 * 由上可得：对于任意两个字符串 xy 和 ab，
	 * 如果它们满足 x-a=1，即第一个字符的 ASCII 码值相差为 1，
	 * 同时满足 b-y=31，即第二个字符的 ASCII 码值相差为 -31。
	 * 那么这两个字符的 hashCode 一定相等。
	 */
	@Test
	public void test16() {
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
		System.out.println("AaBB".hashCode());
		System.out.println("BBAa".hashCode());
		System.out.println("BBAa".hashCode());
		System.out.println("AaBB".hashCode());
	}


	/**
	 * 种子数据：两个长度为2的 hashCode 一样的字符串
	 */
	private static String[] SEED = new String[]{"Aa", "BB"};

	/**
	 * 生成 2 的 n 次方个 HashCode 一样的字符串的集合
	 */
	public static List<String> hashCodeSomeList(int n) {
		List<String> initList = new ArrayList<String>(Arrays.asList(SEED));
		for (int i = 1; i < n; i++) {
			initList = createByList(initList);
		}
		return initList;
	}

	public static List<String> createByList(List<String> list) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < SEED.length; ++i) {
			for (String str : list) {
				result.add(SEED[i] + str);
			}
		}
		return result;
	}

	@Test
	public void a() {
		List<String> strings = hashCodeSomeList(4);
		System.out.println(strings);
	}
}
