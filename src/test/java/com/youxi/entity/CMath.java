package com.youxi.entity;

/**
 * 2d碰撞数学库
 * 
 * @author Eric.Ma
 *
 */
public class CMath {

	public static int PRECISION = 1000;
	public static int HALF_PRECISION = PRECISION / 2;
	public static float PRECISION_FACTOR = 0.001f;
	public static int ONE = PRECISION;
	public static int HALF = ONE / 2;
	public static final int negOne = -PRECISION;

	// 定点数
	public static final int EPSILON_FIXED = 1;

	public static long Cross2D(Position u, Position v) {
		return (long) (u.getX() * v.getZ() * PRECISION_FACTOR) - (long) (u.getZ() * v.getX() * PRECISION_FACTOR);
	}

	public static long Dot2D(Position d1, Dir d2) {
		return Dot2D(d1.getX(), d1.getZ(), d2.getX(), d2.getZ());
	}

	public static long Dot2D(Position d1, Position d2) {
		return Dot2D(d1.getX(), d1.getZ(), d2.getX(), d2.getZ());
	}

	public static long Dot2D(long x1, long z1, long x2, long z2) {
		return (long) (x1 * x2 * PRECISION_FACTOR) + (long) (z1 * z2 * PRECISION_FACTOR);
	}

	public static long Cross2D(long x1, long z1, long x2, long z2) {
		return (long) (x1 * z2 * PRECISION_FACTOR) - (long) (z1 * x2 * PRECISION_FACTOR);
	}


	public static long fixedDiv(long a, long b) {
		return a * PRECISION / b;
	}

	public static long fixedMultiply(long a, long b) {
		return (long) (a * b * PRECISION_FACTOR);
	}

	public static long square(long v) {
		return fixedMultiply(v, v);
	}

	public static long sqrt(long v) {
		return (long) (Math.sqrt(v * PRECISION));
	}


	public static long min(long... value) {
		long min = Long.MAX_VALUE;
		for (long l : value) {
			if(l< min) {
				min = l;
			}
		}
		return min;
	}

	public static long max(long... value) {
		long max = Long.MIN_VALUE;
		for (long l : value) {
			if(l> max) {
				max = l;
			}
		}
		return max;
	}

	public static void main(String[] args) {
//		1876,1384,

		long d = Dot2D(1876, 1384, 1876, 1384);
		System.err.println(d);
	}
}
