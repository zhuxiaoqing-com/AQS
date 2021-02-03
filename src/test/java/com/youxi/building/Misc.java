package com.youxi.building;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/12 10:26
 * @Description:
 */
public class Misc {
	public static int getIntIndexByXY(int chunkX, int chunkY) {
		return chunkX << 16 | chunkY;
	}

	public static short getIntLow(int index) {
		return (short) (0xFFFF & index);
	}

	public static short getIntHigh(int index) {
		return (short) ((0xFFFF0000 & index) >> 16);
	}

	public static long getLongIndexByXY(int high, int low) {
		return ((long) low & 0xFFFFFFFFL) | (((long) high << 32) & 0xFFFFFFFF00000000L);
	}

	public static int getLongLow(long index) {
		return (int) (0xFFFFFFFFL & index);
	}

	public static int getLongHigh(long index) {
		return (int) ((0xFFFFFFFF00000000L & index) >> 32);
	}
}
