package com.example.代码优化.设计模式之美.a_57观察者模式.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/11 10:28
 * @Description:
 */
public class ObserverAction {
	private Object target;
	private Method method;

	public ObserverAction(Object target, Method method) {
		this.target = target;
		this.method = method;
	}

	// event 是 Method 方法的参数
	public void execute(Object event) {
		try {
			method.invoke(target, event);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		class a {
			public void aa(){

			}
		}

	}
}
