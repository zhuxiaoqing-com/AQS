package com.example.demo4.testObj1;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/6/5 14:50
 * @Description:
 */
public interface AInterface {

	String a();
	default String default1() {
		return "default1";
	}
}
