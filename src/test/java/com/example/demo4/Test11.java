package com.example.demo4;

import com.example.demo1.util.GeomUtil;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test11 {
	@Test
	public void test01() {
		long start = System.nanoTime();
		short s;
		for (int i = 1; i <= 100_000_000_0; i++) {
			s = 0x00;
		}
		long end = System.nanoTime();
		System.out.println((end - start) / 1000);
	}

	@Test
	public void test02() {
		int x = 3;
		int k = 1;
		System.out.println(x / (1 << k));

		System.out.println(x >> (k));
		System.out.println((x + (1 << k) - 1) >> k);

		System.out.println();

		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.stream().map(member -> {
			if (member.startsWith("\"")) {
				member = member.substring(1, member.length() - 1);
			}
			return Integer.parseInt(member);
		}).collect(Collectors.toList());

	}

	@Test
	public void test03() throws UnknownHostException {
		List<Integer> objects = new LinkedList<>();
		for (int i = 0; i <= 100000; i++) {
			objects.add(i);
		}
		long startNano = System.nanoTime();
		for (int i = 0; i < objects.size(); i++) {
			Integer integer = objects.get(i);
		}
		System.out.println("____" + (System.nanoTime() - startNano));


		startNano = System.nanoTime();
		for (Integer object : objects) {

		}
		System.out.println("____" + (System.nanoTime() - startNano));
	}

	@Test
	public void test04() throws UnknownHostException {
		List<Integer> objects = new LinkedList<>();
		for (int i = 0; i <= 100000; i++) {
			objects.add(i);
		}

		Iterator<Integer> iterator = objects.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			if (i == 3) {
				objects.remove(3);
			}
			System.out.println(iterator.next());
			i++;
		}
	}

	@Test
	public void test05() throws UnknownHostException {
		TestUtil.testTime(() -> {
			double a = 2 * 3;
		}, 1000000000, "double");
		TestUtil.testTime(() -> {
			long a = 2L * 3L;
		}, 1000000000, "long");

		TestUtil.testTime(() -> {
			int a = 2 * 3;
		}, 1000000000, "int");
		//TestUtil.testTime(()-> {float a = 2f*3f;}, 1000000000, "float");
		TestUtil.testTime(() -> {
			float a = 1.2f * 3.4f;
		}, 1000000000, "float");


	}


	@Test
	public void test06() throws UnknownHostException {
		TestUtil.testTime(() -> {
			int a = 2 * 3;
		}, 1000000000, "int");
	}


	@Test
	public void test07() throws UnknownHostException {
		TestUtil.testTime(() -> {
			float a = 1.2f * 3.4f;
		}, 1000000000, "float");


	}

	@Test
	public void test08() throws UnknownHostException {
		// 饿汉式
		A a = A.a;
		// 懒汉式
		A.getInstance();

	}

	@Test
	public void test09() throws UnknownHostException {
		for (int i = 0; i <= 10; i++) {
			System.out.println(i);
		}

		A a = A.a;
	}

	static class A {
		static {
			System.out.println("加载A");
		}

		public static A a = new A();

		public static A getInstance() {
			a = new A();
			return a;
		}
	}


	@Test
	public void test10() {
		System.out.println(new Date(1591348427463L));
		System.out.println(new Date(1591348437463L));
	}

}















