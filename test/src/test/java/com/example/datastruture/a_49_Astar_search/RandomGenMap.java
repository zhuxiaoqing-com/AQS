package com.example.datastruture.a_49_Astar_search;

import java.util.Random;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/2/3 19:43
 * @Description:
 */
public class RandomGenMap {
	public static int[][] randomGenMap(int maxX, int maxY) {
		int[][] ints = new int[maxX][maxY];
		Random random = new Random();
		for (int i = 0; i < maxX; i++) {
			for (int i1 = 0; i1 < maxY; i1++) {
				// 生成的边界都是墙
				if (i == 0 || i1 == 0 || i == maxX - 1 || i1 == maxY - 1) {
					ints[i][i1] = 1;
				} else {
					int i2 = random.nextInt(10);
					ints[i][i1] = i2 > 7 ? 1 : 0;
				}
				//ints[i][i1] = 0;
			}
		}
		return ints;
	}
}
