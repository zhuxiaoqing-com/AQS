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
	}

	private static final int SERVER_POS = 47;// 64-47 = 17 还有17位可以用 如果要保证正数那就只有16位可以用

	private static final int ADD_POS = 15; // 47-15 = 32 32位可以用;32位可以用作秒数;
	@Test
	public void text02() {

	}
}
