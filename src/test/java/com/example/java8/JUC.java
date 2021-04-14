package com.example.java8;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/4/14 10:30
 * @Description:
 */
public class JUC {
	@Test
	public void test1() {
		ConcurrentSkipListSet<Object>  skipListSet = new ConcurrentSkipListSet<>();
		 skipListSet.size();

		ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
		Set<Object> objects1 = ConcurrentHashMap.newKeySet();
		concurrentHashMap.size();
		CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
		list.size();
	}
}

