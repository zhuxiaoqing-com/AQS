package com.example.TestArithmetic.彩虹表.RArith;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/9/27 15:11
 * @Description:
 */
public class R1 implements RArith {
	@Override
	public String calc(String hash) {
		return hash.replace("a", "b");
	}
}
