package com.example.demo4;

import com.example.demo1.util.GeomUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;

public class Test10 {
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
		long start = System.nanoTime();
		short s;
		for (int i = 1; i <= 100_000_000_0; i++) {
			s = 0;
		}
		long end = System.nanoTime();
		System.out.println((end - start) / 1000);
	}

	@Test
	public void test03() {
		float attr1 = 0.2f;
		float attr2 = 0.2f;
		float result1 = (1 + attr1) * (1 + attr2) - 1;
		System.out.println(result1);
		System.out.println(100 * 1.2 * 1.2);
		System.out.println(100 + 100 * result1);
		System.out.println(100 * (1 + attr1));
	}

	@Test
	public void test04() {
		// -2^31 <= int1 <= 2^31-1
		// -2^32 <= int1+int2 <= 2^32-2
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);
	}

	@Test
	public void test05() {
		double v = Math.atan2(2, 1);
		System.out.println((Math.toDegrees(v) + 360) % 360);
		System.out.println(Math.toDegrees(v));

		double v1 = GeomUtil.toUnityDegrees(v);
		System.out.println(v1);
	}


	@Test
	public void test06() {
		System.out.println(-115);
		System.out.println(90 - -115);
		System.out.println(90 + 115);
		System.out.println(90 + (-1));
	}
    /*
    360 - x +90 == 90 - x
    270 -x == 90-x  ?
     */

	@Test
	public void test07() {
		Long a = 0L;
		Long a1 = 3L;
		if (a == a1) {

		}
	}

	@Test
	public void test08() {
		int result = 0B101001001;
		int i = 0B110101;
		int i1 = 0B1101;

		System.out.println(result);
		System.out.println(i * i1);

		int aa = 0b1010;
		int a1 = 0b10;
		System.out.println(aa % a1);
	}


	@Test
	public void test09() {
		printTime(100, 50, 5);
		printTime(100, 10000, 4);
	}


	/*
	 * 比较数组和链表执行插入数据时花费的时间
	 * len    定义数组的长度
	 * num    循环插入的次数(插入一次时间太短难比较)
	 * index  每次插入的位置
	 */
	public void printTime(int len, int num, int index) {
		LinkedList<Integer> link = new LinkedList<Integer>();//定义链表
		ArrayList<Integer> arr = new ArrayList<Integer>();//定义数组
		//ArrayDeque<Integer> arr=new ArrayDeque<Integer>();//定义数组
		//为数组赋初值
		for (int i = 0; i < len; i++) {
			arr.add(i);
			link.add(i);
		}
		//计算数组执行操作花费的时间
		long startTime = System.nanoTime();
		for (int i = 0; i < num; i++) {
			arr.add(index, 2);
		}
		long endTime = System.nanoTime();
		System.out.println("数组花费时间：" + (endTime - startTime) + "纳秒");
		//计算链表执行相同操作花费的时间
		long sTime = System.nanoTime();
		for (int i = 0; i < num; i++) {
			link.add(index, 2);
		}
		long eTime = System.nanoTime();
		System.out.println("链表花费时间：" + (eTime - sTime) + "纳秒");
	}


	/**
	 * 803112612528400
	 * 803122772524800
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void test10() throws InterruptedException {
		System.out.println((System.nanoTime() + ""));
		System.out.println((System.currentTimeMillis() + ""));
		System.out.println((System.nanoTime() + "").length());
		System.out.println((System.currentTimeMillis() + "").length());

		ArrayList<Long> objects = new ArrayList<>(100000);
		for (int i = 0; i < 100000; i++) {
			objects.add(System.nanoTime());
		}
		System.out.println(objects);
	}


	@Test
	public void test11() throws InterruptedException {
		long currNano = System.currentTimeMillis() * 1000_000;
		System.out.println(currNano);
		System.out.println(String.valueOf(currNano).length());
		System.out.println(Long.MAX_VALUE - currNano);
		System.out.println(String.valueOf(Long.MAX_VALUE).length());

		long nano = 1;
		LocalDate now = LocalDate.now();
		while (nano > 0) {
			now = now.plusYears(1);
			nano = now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() * 1000_000;
		}

		System.out.println(now);
	}

	@Test
	public void test12() {
		double pow = Math.pow(2, 52);
		long l = -1L ^ (-1L << 52);
		System.out.println(pow);
		System.out.println((long) pow);
		System.out.println(l);
		System.out.println(pow == l);
	}

	public void printByFormat(String str, Object... param) {
		String format = String.format(str, param);
	}

	@Test
	public void test13() {
		System.out.println(Integer.toBinaryString(128));
		System.out.println(Integer.toBinaryString(13));
		System.out.println(Integer.toBinaryString(138));
	}

	@Test
	public void test14() {
		long start01 = System.nanoTime();
		for (int i = 0; i <= 1000_000_000; i++) {
			if (1 > 2) {
			}
		}

		System.out.println(System.nanoTime() - start01);

		long start02 = System.nanoTime();
		for (int i = 0; i <= 1000_000_000; i++) {
			if (1 >= 2) {
			}
		}
		System.out.println(System.nanoTime() - start02);
	}

	@Test
	public void test15() {
		Map<Integer, Integer> hashMap = new HashMap<>();
		Map<Integer, Integer> treeMap = new TreeMap<>();

		long start = System.nanoTime();
		for (int i = 0; i < 500; i++) {
			hashMap.put(i, i);
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for (int i = 0; i < 500; i++) {
			treeMap.put(i, i);
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for (int i = 0; i < 500; i++) {
			hashMap.get(3);
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for (int i = 0; i < 500; i++) {
			treeMap.get(250);
		}
		System.out.println(System.nanoTime() - start);

	}

	@Test
	public void test16() {
		Map<Integer, Integer> hashMap1 = new HashMap<Integer, Integer>() {
			{
				put(1, 1);
			}
		};
		Map<Integer, Integer> hashMap2 = new HashMap<Integer, Integer>() {
			{
				put(1, 1);
			}
		};


		System.out.println(hashMap1.equals(hashMap2));
	}


	@Test
	public void fun17() {
		TestUtil.testTime(()->{int s= 111111*1000;}, 10000000,"fd");
		TestUtil.testTime(()->{float s= 111111*0.01f;}, 10000000,"fd");
	}

	@Test
	public void fun18() {
		TestUtil.testTime(()->{new Object();}, 10_000_000,"fd");

	}

	@Test
	public void fun19() {
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Long.toBinaryString(-1L));
	}

	@Test
	public void fun20() {
		try{
			String s = "22335d";
			Integer.parseInt(s);
		}catch (Exception e){
			System.out.println(e);
		}
		System.out.println("还能运行到这段代码嘛");
	}

	@Test
	public void fun21() {
		int param = 1;
		switch (param){
			case 1:{
				System.out.println("哈哈哈");
				if(true){
					break;
				}
				System.out.println("还能运行到吗");
			}
		}
	}




}


/**
 * if(1频道){
 * if(1线){
 * 给角色一个无敌buff
 * } else if(2线){
 * 复活buff
 * }
 * }else if(2频道)
 */













