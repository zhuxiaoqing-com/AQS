package com.example.demo4;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test12 {
	@Test
	public void test01() throws Throwable {
		int maxNum = 100000;
		Test12 test12 = new Test12();
		long startNano;

		startNano = System.nanoTime();
		for (int i = 0; i < maxNum; i++) {
			test12.a();
		}
		System.out.println( (System.nanoTime() - startNano));

		startNano = System.nanoTime();
		for (int i = 0; i < maxNum; i++) {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodHandle a = lookup.findVirtual(Test12.class, "a", MethodType.methodType(void.class));
			a.invoke(test12);
		}
		System.out.println( (System.nanoTime() - startNano));



	}

	public void a() {
	}
}
