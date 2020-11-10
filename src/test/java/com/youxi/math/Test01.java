package com.youxi.math;

import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/11/10 11:50
 * @Description:
 */
public class Test01 {
	/**
	 * angle 1 = r
	 * 周长= 2πr
	 * 360° = 2πr
	 * 360° = 2π*1弧度
	 * 1° = 2π/360°
	 * 1弧度 =  360/2π
	 */
	@Test
	public void test01() {
		System.out.println(Math.atan2(1, 1)*(180/Math.PI));
		System.out.println(Math.atan2(1, -1)*(180/Math.PI));
		System.out.println(Math.atan2(-1, 1)*(180/Math.PI));
		System.out.println(Math.atan2(-1, -1)*(180/Math.PI));
		System.out.println(Math.atan2(1, 0)*(180/Math.PI));
	}
}
