package com.youxi.misc;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/5/24 10:52
 * @Description:
 */
public class Test01 {

	public static void main(String[] args) {
		MyHashMap<Integer, Integer> map = new MyHashMap<>();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(()->map.put(1,2));
		executorService.submit(()->map.put(2,2));
	}
	@Test
	public void test01() throws InterruptedException {
		MyHashMap<Integer, Integer> map = new MyHashMap<>();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(()->map.put(1,2));
		executorService.submit(()->map.put(2,2));

	/*	Thread.sleep(1111);
		map.put(3,3);
		map.put(3,3);
		map.put(3,3);
		map.put(3,3);
		map.put(3,3);
		map.put(3,3);*/
	}
}
