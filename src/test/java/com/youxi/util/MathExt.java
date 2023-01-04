package com.youxi.util;

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

	public static void main(String[] args) {
		System.err.println(clamp(5, 1, 3));
	}
}
