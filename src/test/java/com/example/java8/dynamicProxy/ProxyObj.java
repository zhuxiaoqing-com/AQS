package com.example.java8.dynamicProxy;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/9/22 15:28
 * @Description:
 */
public class ProxyObj implements IProxy {

	@Override
	public void a() {
		System.out.println("ProxyObj");
	}
}
