package com.youxi.util;

import java.util.Arrays;

/**
 * Math函数的一些扩展方法
 * 
 * @author Eric.Ma
 * 
 */
public class MathExt {

	/**
	 * C# clamp ，返回这个区间的值
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static int clamp(int value, int min, int max) {
		if (value < min) {
			value = min;
		} else if (value > max) {
			value = max;
		}
		return value;
	}

	public static double clamp(double value, double min, double max) {
		value = Math.max(value, min);
		value = Math.min(value, max);
		return value;
	}

	public static float clamp(float value, float min, float max) {
		value = Math.max(value, min);
		value = Math.min(value, max);
		return value;
	}

	public static int max(int... num) {
		return Arrays.stream(num).max().orElse(0);
	}

	public static long max(long... num) {
		return Arrays.stream(num).max().orElse(0L);
	}
	public static void main(String[] args) {
		System.err.println(clamp(5, 1, 3));
	}



	/**
	 * 不会溢出int 溢出了就返回Integer.MAX_VALUE
	 */
	public static int addSafe(int x, int y) {
		// Math.addExact(); 用的这个里面的代码
		int r = x + y;
		if (((x ^ r) & (y ^ r)) < 0) {
			return Integer.MAX_VALUE;
		}
		return r;
	}

	/**
	 * 不会溢出int 溢出了就返回Integer.MAX_VALUE
	 */
	public static int addSafe(int x, long y) {
		if (y >= Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		int r = (int) (x + y);
		if (((x ^ r) & (y ^ r)) < 0) {
			return Integer.MAX_VALUE;
		}
		return r;
	}
}
