package com.example.demo4;

import io.swagger.models.auth.In;
import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test14 {

	public static void main(String[] args) {
		int whileMax = 2000000000;

		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < whileMax; i++) {
			sum++;
		}
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		long sum2 = 0;
		for (int i = 0; i < whileMax; i++) {
			sum2++;
		}
		System.out.println(System.currentTimeMillis() - start);
	}


	@Test
	public void test01() {
		int value = 555553135;
		System.out.println(Integer.toBinaryString(value));
		while ((value & -128) != 0) {
			int s = (int) value & 127 | 128;
			System.out.println(Integer.toBinaryString(s));
			value >>>= 7;
		}

	}

	@Test
	public void test02() {
		int value = 555553135;
		int s = (int) value & 127 | 128;
		System.out.println(Integer.toBinaryString(value&127));
		System.out.println(Integer.toBinaryString(value&127|128));

	}
}


