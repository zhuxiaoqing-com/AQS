package com.example.java8.dynamicProxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/9/22 15:29
 * @Description:
 */
public class ProxyTest01 {

	@Test
	public void test01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		IProxy proxyObj = new ProxyObj();

		InvocationHandler handler = new ProxyHandler(proxyObj);
		ProxyHandler proxyHandler = new ProxyHandler(proxyObj);
		IProxy o = (IProxy)Proxy.newProxyInstance(proxyObj.getClass().getClassLoader(), proxyObj.getClass().getInterfaces(), handler);

		Method a = o.getClass().getDeclaredMethod("a");
		Object invoke = a.invoke(o);
	}
}
// 1_xkjdsjkadjklsajl$bilap    map<int,Player>   101->player  102->player