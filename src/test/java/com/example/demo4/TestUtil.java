package com.example.demo4;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/12 17:46
 * @Description:
 */
public class TestUtil {

	public static void testTime(Runnable runnable, int maxNum, String... param) {
		long startNano = System.nanoTime();
		for (int i = 0; i < maxNum; i++) {
			runnable.run();
		}
		String str = param.length > 0 ? param[0] : "";
		System.out.println(str + "____" + (System.nanoTime() - startNano));
	}

	

	public static void testTime(Runnable runnable, long maxNum, String... param) {
		long startNano = System.nanoTime();
		for (long i = 0; i < maxNum; i++) {
			runnable.run();
		}
		String str = param.length > 0 ? param[0] : "";
		System.out.println(str + "____" + (System.nanoTime() - startNano));
	}

}
