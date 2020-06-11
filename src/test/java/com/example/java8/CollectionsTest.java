package com.example.java8;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/11 21:11
 * @Description:
 */
public class CollectionsTest {
	public void test01() {
		Set<Object> objects = Collections.newSetFromMap(new ConcurrentHashMap<>());
	}
}
