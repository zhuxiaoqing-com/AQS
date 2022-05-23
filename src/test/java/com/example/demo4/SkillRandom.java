package com.example.demo4;

import java.util.HashMap;
import java.util.Random;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/5/20 17:17
 * @Description:
 */
public class SkillRandom {

	static final String BadBound = "bound must be positive";

	private long seed;

	private static final long multiplier = 0x5DEECE66DL;
	private static final long addend = 0xBL;
	private static final long mask = (1L << 48) - 1;

	public SkillRandom(long seed) {
		setSeed(seed);
	}

	public void setSeed(long seed) {
		this.seed = initialScramble(seed);
	}

	private long initialScramble(long seed) {
		return (seed ^ multiplier) & mask;
	}


	protected int next(int bits) {
		long oldseed, nextseed;
		oldseed = this.seed;
		nextseed = (oldseed * multiplier + addend) & mask;
		seed = nextseed;
		return (int)(nextseed >>> (48 - bits));
	}

	public int nextInt(int bound) {
		if (bound <= 0)
			throw new IllegalArgumentException(BadBound);

		int r = next(31);
		int m = bound - 1;
		if ((bound & m) == 0)  // i.e., bound is a power of 2
			r = (int)((bound * (long)r) >> 31);
		else {
			for (int u = r;
				 u - (r = u % bound) + m < 0;
				 u = next(31))
				;
		}
		return r;
	}


	/**
	 * 检测万分数概率是否成功
	 *
	 * @param value 给定的概率
	 */
/*	public boolean checkRatePercent10000(int value) {
		int i = random.nextInt(10000);
		return value >= i;
	}*/


	public static void main(String[] args) {
		int seed = 1000;
		int count = 11;
		int bound = 103213;

		for (int i = -10000; i < seed; i++) {
			testResult(seed, count, bound);
		}
	}

	private static void testResult(int seed, int count, int bound) {
		SkillRandom skillRandom = new SkillRandom(seed);
		Random random = new Random(seed);
		for (int i = 0; i < count; i++) {
			if(skillRandom.nextInt(bound) != random.nextInt(bound)) {
				System.out.println("error11111111111111111111111111111111111111111");
			}
		}

	}
}
