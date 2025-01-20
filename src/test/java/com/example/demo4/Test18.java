package com.example.demo4;

import com.example.demo1.util.vo.Vector2f;
import com.google.common.eventbus.EventBus;
import com.youxi.entity.Position;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.web3j.abi.datatypes.Int;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test18 {

	public static class Obj {
		private Map<Integer,Integer> obj1;
		private int obj2;

		public Map<Integer, Integer> getObj1() {
			return obj1;
		}

		public void setObj1(Map<Integer, Integer> obj1) {
			this.obj1 = obj1;
		}

		public int getObj2() {
			return obj2;
		}

		public void setObj2(int obj2) {
			this.obj2 = obj2;
		}
	}

	@Test
	public void test01() throws InstantiationException, IllegalAccessException, InvocationTargetException {
		Obj obj = Obj.class.newInstance();

		Method method = Arrays.stream(Obj.class.getMethods())
				.filter(a -> a.getName().equals("setObj1"))
				.findAny().orElse(null);

		Map<Integer, Integer> objectObjectHashMap = new HashMap<>();
		Object objectObjectHashMap1 = new HashMap<>();
		method.invoke(obj, objectObjectHashMap1);
		System.out.println(obj);
	}

	}


	//  -XX:AutoBoxCacheMax=100000















