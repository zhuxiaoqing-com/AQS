package com.example.TestArithmetic.彩虹表;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/9/27 15:22
 * @Description:
 */
public class HashUtil {
	public String hash(String pwd) {
		return String.valueOf(pwd.hashCode());
	}
}
