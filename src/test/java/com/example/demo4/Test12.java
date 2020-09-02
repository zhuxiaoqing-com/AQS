package com.example.demo4;

import com.youxi.util.RandomUtil;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
		System.out.println((System.nanoTime() - startNano));

		startNano = System.nanoTime();
		for (int i = 0; i < maxNum; i++) {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodHandle a = lookup.findVirtual(Test12.class, "a", MethodType.methodType(void.class));
			a.invoke(test12);
		}
		System.out.println((System.nanoTime() - startNano));


	}

	public void a() {
	}

	@Test
	public void test02() {
		short a = -1;
		System.out.println(a == -1);
		System.out.println(Long.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-1));
	}

	@Test
	public void test03() {
		Object[] objects = {2};
		System.out.println(objects[0].getClass());
	}

	@Test
	public void test04() {
		List<Integer> objects = new ArrayList<>();
		for (int i = 0; i <= 100; i++) {
			objects.add(i);
		}

	/*	TestUtil.testTime(() -> {
			List<Integer> random = RandomUtil.getRandom(objects, 10000);
			//System.out.println(random);
		}, 1);
*/
		TestUtil.testTime(() -> {
			List<Integer> random = RandomUtil.randomNoRepeatNums(0, objects.size() - 1, 10);
			ArrayList<Object> objects1 = new ArrayList<>(10);
			for (Integer integer : random) {
				objects1.add(objects.get(integer));
			}
			//System.out.println(objects1);
		}, 1);

		TestUtil.testTime(() -> {
			List<Integer> random = RandomUtil.getRandomValues(0, objects.size() - 1, 10);
			ArrayList<Object> objects1 = new ArrayList<>(10);
			for (Integer integer : random) {
				objects1.add(objects.get(integer));
			}
			//System.out.println(objects1);
		}, 1);
	}

	@Test
	public void test05() {
		int maxNum = 10000;
		int randomNum = 5000;

		TestUtil.testTime(() -> RandomUtil.randomNoRepeatNums(0, maxNum, randomNum), 11111);
		TestUtil.testTime(() -> RandomUtil.getRandomArray(0, maxNum, randomNum), 11111);

		List<Integer> integers = RandomUtil.randomNoRepeatNums(0, maxNum, randomNum);
		int[] randomArray = RandomUtil.getRandomArray(0, maxNum, randomNum);
		HashSet<Integer> objects = new HashSet<>();
		for (int i : randomArray) {
			objects.add(i);
		}
		System.out.println(new HashSet<>(integers).size() + "......" + objects.size());
	}

	// 1302
	@Test
	public void test06() {
		Instant start = Instant.now();
		int length = 10;
		int[] sum = new int[length];
		for (int i = 0; i < 1_000_000_000; i++) {
			sum[i % 10] = sum[i % 10] + i;
		}
		int sumNum = 0;
		for (int i : sum) {
			sumNum += i;
		}
		System.out.println(sumNum);

		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}

	// 253
	@Test
	public void test07() {
		Instant start = Instant.now();
		float sum = 0;
		for (float i = 0; i < 1_000_000_0; i++) {
			sum *= i;
		}
		System.out.println(sum);
		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}


	@Test
	public void test08() {
		Instant start = Instant.now();
		float sum1 = 0;
		float sum2 = 0;
		float sum3 = 0;
		float sum4 = 0;
		float sum5 = 0;
		int whileCount = 1_000_000_0;
		int i1 = whileCount / 5;
		for (float i = 0; i < i1; i++) {
			sum2 *= i;
		}
		for (float i = 0; i < i1; i++) {
			sum3 *= i;
		}
		for (float i = 0; i < i1; i++) {
			sum4 *= i;
		}
		for (float i = 0; i < i1; i++) {
			sum5 *= i;
		}
		for (float i = 0; i < i1; i++) {
			sum1 *= i;
		}
		System.out.println(sum1 * sum2 * sum3 * sum4 * sum5);

		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}


	@Test
	public void test12() {
		Instant start = Instant.now();
		float sum1 = 0;
		float sum2 = 0;
		float sum3 = 0;
		float sum4 = 0;
		float sum5 = 0;
		int whileCount = 1_000_000_0;
		int i1 = whileCount / 5;
		for (float i = 0; i < whileCount; i += 5) {
			sum1 *= i;
			sum2 *= i+1;
			sum3 *= i+2;
			sum4 *= i+3;
			sum5 *= i+4;
		}
		System.out.println(sum1 * sum2 * sum3 * sum4 * sum5);

		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}

	@Test
	public void test09() {
		Instant start = Instant.now();
		int sum = 0;
		for (int i = 0; i < 10_000_000_00; i += 2) {
			sum += 2;
		}
		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}

	@Test
	public void test10() {
		Instant start = Instant.now();

		long sum = 0;
		for (long i = 0; i < 10_000_000_00L; i++) {
			sum += i;
		}
		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
		System.out.println(sum);
	}

	@Test
	public void test11() {
		Instant start = Instant.now();
		long sum = 10_000_000_00L * (10_000_000_00L - 1L) / 2L;

		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
		System.out.println(sum);
	}

	int inlineVar = 0;
	@Test
	public void test13() {
		int i = inlineVar;
		test15();
	}

	@Test
	public void test14() {
		int i = inlineVar;
		test15();
	}
	@Test
	public void test15() {
		System.out.println("inline method");
	}
}
