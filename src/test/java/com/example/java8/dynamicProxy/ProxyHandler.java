package com.example.java8.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/9/22 15:28
 * @Description:
 */
public class ProxyHandler implements InvocationHandler {
	private Object object;
	public ProxyHandler(Object object){
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Before invoke "  + method.getName());
		method.invoke(object, args);
		System.out.println("After invoke " + method.getName());
		return null;
	}
}
