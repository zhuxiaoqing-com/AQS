package com.example.demo3;

import org.junit.Test;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Test2List {
	private Random random = new Random();
	;

	List<Long> list;

	{
		list = new ArrayList<>();
		for (long i = 0L; i < 10L; i++) {
			list.add(i);
		}
	}

	/**
	 * subList start 包含
	 * end 不包含
	 * 所有的删除基于 subList 最终会映射到 list 里面
	 */
	@Test
	public void fun1() {
		List<Long> longs = list.subList(0, 9);
		System.out.println(longs);
		longs.remove(0);
		System.out.println(longs);
		System.out.println(list);
	}

	@Test
	public void fun2() {
		Long[] temps = new Long[9999];
		Arrays.fill(temps, -1L);
		List<Long> list = Arrays.asList(temps);
		List<Long> longs = list.subList(2, 44);
		System.out.println(longs);
		System.out.println(list);
	}

	/**
	 * 不会报错
	 */
	@Test
	public void fun3() {
		Long[] temps = new Long[9999];
		Arrays.fill(temps, -1L);
		List<Long> list = Arrays.asList(temps);
		ArrayList<Long> longs = new ArrayList<>(list);
		longs.remove(2);
	}

	/**
	 * 不会报错
	 */
	@Test
	public void fun4() {
		int min = 2;
		int max = 22;

		while (true) {
			int i = random.nextInt(1);
			System.out.println(i);

		}
	}

	private static final Map<Integer, Integer> randomMap = new LinkedHashMap();

	{
		/**
		 * 大于 5000
		 * 最左的玩家 根据玩家自身排名往前取 500 内随机，显示在最左的位置
		 * 中间的玩家 根据玩家自身排名往前取 1000 内随机，显示在中间的位置
		 * 最右的玩家 根据玩家自身排名往前取 2000 内随机，显示在最右的位置
		 */
		randomMap.put(5000, 11);
		randomMap.put(1000, 22);
		randomMap.put(500, 33);
		randomMap.put(100, 44);
		randomMap.put(50, 55);
		randomMap.put(10, 66);
	}

	@Test
	public void fun5() {
		while (true) {
			for (int rankRange : randomMap.keySet()) {
				System.out.println(rankRange);
			}
			System.out.println("---------------------------------------------");

		}
	}

	/**
	 * list 为 null 还是有值的就是 [null]
	 */
	@Test
	public void fun6() {
		ArrayList<Object> list = new ArrayList<>();
		list.add(null);
		System.out.println(list);
	}

	@Test
	public void fun7() {
		double i = (2 + 0.0 - 1) / 2;
		System.out.println(i);
	}

	@Test
	public void fun8() {
		while (true) {
			int randomInt = random.nextInt(2);
			System.out.println(randomInt);
		}
	}

	@Test
	public void fun9() {
		boolean i = 1 == 2 - 1 * 0.5;
		System.out.println(i);
	}

	@Test
	public void fun10() {
		Random random = new Random();
	}

	@Test
	public void fun11() {
		Random random = new Random();
	}
	//Random random1 = new Random(10000);
	//Random random1 = ThreadLocalRandom.current();

	/**
	 * 测试出来了  ThreadLocalRandom  341 286 285 306 267
	 * random   几乎卡死
	 * SecureRandom  真随机Random
	 */
	Random random1 = new Random();

	@Test
	public void fun12() throws InterruptedException {
		Instant instant1 = Instant.now();
		int end = 50;
		CountDownLatch countDownLatch = new CountDownLatch(end);
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		for (int j = 0; j < end; j++) {
			executorService.execute(() -> {
				for (int i = 0; i < 500_000; i++) {
					// 341 286 285 306 267
					//ThreadLocalRandom.current().nextInt(1000);//731
					// 几乎卡死 662
					//random1.nextInt(1000);// 1501
				}
				//SecureRandom secureRandom = new SecureRandom();

				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		Instant instant2 = Instant.now();
		System.out.println(instant2.toEpochMilli() - instant1.toEpochMilli());

	}

	private void randomTest(int whileCount) throws InterruptedException {
		Instant instant1 = Instant.now();
		int threadCount = 20;
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		for (int j = 0; j < threadCount; j++) {
			executorService.execute(() -> {
				for (int i = 0; i < whileCount; i++) {
					// 341 286 285 306 267
					ThreadLocalRandom.current().nextInt(1000);//731
					// 几乎卡死 662
					//random1.nextInt(1000);// 1501
				}
				//SecureRandom secureRandom = new SecureRandom();

				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		Instant instant2 = Instant.now();
		System.out.println(instant2.toEpochMilli() - instant1.toEpochMilli() + "  whileCount :" + whileCount);
	}

	@Test
	public void fun14() throws InterruptedException {
		randomTest(1000); //34 35
		randomTest(10000); //4 18
		randomTest(100000);//33 201
		randomTest(1_000_000);//21 1910
	}

	@Test
	public void fun13() throws InterruptedException {
		Instant instant1 = Instant.now();
		long nano = instant1.getEpochSecond();
		long nano2 = instant1.toEpochMilli();
		long nano1 = instant1.getNano();
		System.out.println(nano2);
		System.out.println(System.currentTimeMillis());
	}
}
