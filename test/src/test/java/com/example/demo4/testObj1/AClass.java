package com.example.demo4.testObj1;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/6/5 14:50
 * @Description:
 */
public class AClass implements AInterface{
/*	@Override
	public String default1() {
		return AInterface.super.default1();
	}*/

	@Override
	public String a() {
		return "AClass";
	}

	public void test() {
		int a = 1/0;
		int aq = 0/1;
	}
}
