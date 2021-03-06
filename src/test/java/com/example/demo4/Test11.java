package com.example.demo4;

import org.apache.commons.cli.*;
import org.junit.Test;

import java.io.*;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

	@Test
	public void test11() {
		List<Integer> list = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
			map.put(i, i);
		}
		TestUtil.testTime(() -> list.get(9), 1000000000);
		TestUtil.testTime(() -> map.get(9), 1000000000);
	}

	@Test
	public void test12() {
		List<Integer> list = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
			map.put(i, i);
		}
		Object[] objects = list.toArray();
		System.out.println(objects);
		objects[0] = new Object();
	}

	@Test
	public void test13() {
		String[] s = new String[]{"1", "2"};
		Object[] a = s;
		System.out.println(a);
	}

	@Test
	public void test14() {
		List<String> strings = Arrays.asList("1", "2");
		System.out.println(strings.toArray());
	}

	@Test
	public void test15() {
		int i = Runtime.getRuntime().availableProcessors();
		System.out.println(i);
	}


	@Test
	public void test16() {
		ArrayList<Object> objects = new ArrayList<>();
		objects.add(1);
		objects.add(2);
		Object next = objects.iterator().next();
		System.out.println(next);
	}

	@Test
	public void test17() {
		int i = ThreadLocalRandom.current().nextInt();
		System.out.println(i);
	}

	@Test
	public void test18() {
		System.out.println(0x28 & 0x0F);
	}

	@Test
	public void test19() {
		boolean b = Boolean.parseBoolean(null);
		System.out.println(b);

		String s = "zhu.xiao.qing";
		System.out.println(s.replace(".", "/"));
	}

	@Test
	public void test20() {
		boolean b = Boolean.parseBoolean(null);
		System.out.println(b);
		System.exit(1);
		String s = "D:/xGenCode/bin/proto_java/Game_LogicServer_logic_player_handler.proto";
		System.out.println(s.lastIndexOf("/"));
	}

	@Test
	public void test21() {
		Long lon1 = Long.MAX_VALUE;
		System.out.println(lon1);
		String s = "111111111111111111111111111111111111111111111";
		//System.out.println(Long.parseLong(s));
		double v = Double.parseDouble(s);
		DecimalFormat df = new DecimalFormat("0");

		System.out.println(Double.parseDouble(s));
		String format = df.format(1.111111111111111E44);
		System.out.println(format);
		double v1 = Double.parseDouble("1.111111111111111E44");
		System.out.println(v1);
		long l = Long.parseLong("9.22337E+18");
		System.out.println(l);
	}


	@Test
	public void test22() {
		Long lon1 = Long.MAX_VALUE;
		System.out.println(lon1);
		String s = "111111111111111";
		long l = Long.parseLong(s);
		System.out.println(l);
	}


	@Test
	public void test23() {
		String s = "fa,s,fdfa,,fdf,,";
		System.out.println(Arrays.toString(s.split(",")));
		System.out.println(Arrays.toString(s.split(",", -1)));
	}

	@Test
	public void test24() {
		String s = "多余的数据";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(2).append("有用的数据").append(s);

		StringBuilder delete = stringBuilder.delete(stringBuilder.length() - s.length(), stringBuilder.length());
		System.out.println(delete.toString());

	}


	@Test
	public void test25() {
		String a = "a|b|c|d|e";
		String[] split = a.split("\\|");
		split[1] = "a";
		System.out.println(Arrays.toString(split));
		System.out.println(a);

		String join = String.join("\\|", split);
		System.out.println(join);
	}


	@Test
	public void test26() {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		List<Integer> collect = list.stream().filter(a -> a > 2).collect(Collectors.toList());
		System.out.println(collect);
	}

	@Test
	public void test27() throws IOException {

	/*	for (int i = 0; i < 1000; i++) {
			FileInputStream fileInputStream = new FileInputStream("F:\\mh\\game_server-develop\\AQS\\src\\test\\java\\com\\example\\demo4\\Test10.java");
			fileInputStream.close();
		} */
		System.out.println("\ufffe");
	}

	@Test
	public void test28() throws IOException {
		if (a() || b() && c()) {
		}
	}

	public boolean a() {
		System.out.println("aaaaaaaaa");
		return true;
	}

	public boolean b() {
		System.out.println("bbbbbbb");
		return true;
	}

	public boolean c() {
		System.out.println("cccccccc");
		return false;
	}

	@Test
	public void test29() {
		//System.out.println(numberA() + numberB() * numberC() > numberD() ? numberF() : 33333);
		System.out.println(numberA() + numberB() * numberC() < numberD() ? numberF() : 4 > 3 ? numberD1() : numberF1());
	}

	public int numberA() {
		System.out.println("numberA");
		return 1;
	}

	public int numberB() {
		System.out.println("numberB");
		return 1;
	}

	public int numberC() {
		System.out.println("numberC");
		return 1;
	}

	public int numberD() {
		System.out.println("numberD");
		return 1;
	}

	public int numberF() {
		System.out.println("numberD");
		return 1;
	}

	public int numberG() {
		System.out.println("numberD");
		return 1;
	}

	public int numberC1() {
		System.out.println("numberC1");
		return 1;
	}

	public int numberD1() {
		System.out.println("numberD1");
		return 1;
	}

	public int numberF1() {
		System.out.println("numberF1");
		return 1;
	}

	public int numberG1() {
		System.out.println("numberG1");
		return 1;
	}


	@Test
	public void test30() {

	}
}















