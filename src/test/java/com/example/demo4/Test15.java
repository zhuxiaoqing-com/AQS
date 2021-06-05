package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.example.demo1.hah.Role;
import com.example.demo4.testObj1.AA;
import com.example.demo4.testObj1.AClass;
import com.example.代码优化.design_mode.TowerAOI.Vector2f;
import com.youxi.building.Misc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test15 {
	public final static int SPAN = 10000;

	@Test
	public void test01() {
		short changeType = 0x00;
		System.out.println(changeType);
	}

	public static void find(int num) {
		int threadNum = (num + SPAN - 1) / SPAN;
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);

		for (int i = 0; i < num; i += SPAN) {
			executor.execute(() -> {
				while (true) {
					// todo 在线程里面利用  SPAN 让每个线程都取不同的区间
					// todo 插入到 阻塞队列; 如果阻塞队列满了 会自动阻塞住的;
				}
			});
		}

		executor.shutdown();
		// todo 如果查询完了  可以插入阻塞队列一个特殊标识  标识 已经阻塞队列已经没有东西了;
	}


	@Test
	public void test02() {
		ArrayList<Object> objects = new ArrayList<>();
		objects.add(0);

		System.out.println(Objects.hash(1, 2, 3));
		System.out.println(Objects.hash(1, 2, 3));
		HashMap<Object, Object> map = new HashMap<>();

	}

	private float dot(Vector2f v1, Vector2f v2) {
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}

	@Test
	public void test03() {
		MyThread myThread = new MyThread();
		myThread.start();

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myThread.print();
		}
	}

	private static class MyThread extends Thread {
		public void print() {
			System.out.println("print.....");
		}

		@Override
		public void run() {
			super.run();
			int max = 10;
			for (int i = 0; i < max; i++) {
				System.out.println("run......");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			throw new RuntimeException("runtimeException");
		}
	}

	@Test
	public void test04() {
		float[] floats = new float[2];
		System.out.println(new Date(1611317154094L));
		System.out.println(new Date(1611317161126L));
	}

	@Test
	public void test05() {
		System.out.println(Integer.toBinaryString(194));
		System.out.println(Integer.toBinaryString(198));
		System.out.println(Integer.toBinaryString(200));
		System.out.println(Integer.toBinaryString(202));
	}

	@Test
	public void test06() {
		for (int i = 0; i < fun1(); i++) {

		}
	}

	int i = 3;

	public int fun1() {
		System.out.println(++i);
		return i;
	}

	/**
	 *   101 1111 0 000 0000 0000 0000 0000 0000
	 *
	 *    10 1101 0 010 1111 1110 1011 1111 1111
	 *   011 1111 1 000 0000 0000 0000 0000 0000
	 *   110 1110 0 000 0000 0000 0000 0000 0000
	 * 0 100 1111 0 000 0000 0000 0000 0000 0000
	 * 0 101 0110 0 000 0000 0000 0000 0000 0000
	 * 0 101 1110 1 000 0000 0000 0000 0000 0000
	 */
	@Test
	public void test07() {
		long a1 = 1L << 61;
		float b = a1;
		System.out.println(a1);
		System.out.println(b);
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(b * b * b)));
		System.out.println(b == a1);

		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);// 不用科学计数
		System.out.println("禁用科学计数法：num=" + nf.format(b));
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.00000000001f)));

	}

	@Test
	public void test08() {
		long a1 = 1L << 60;
		float b = a1;
		System.out.println((long) b);
		System.out.println(a1);
		System.out.println(b);
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);// 不用科学计数
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(b)));
		System.out.println(Long.toBinaryString(Double.doubleToLongBits(b)));
		System.out.println("禁用科学计数法：num=" + nf.format(b));
	}

	@Test
	public void test09() {
		long a1 = 1L << 62;
		float b = a1 - 1;
		System.out.println(Long.toBinaryString(a1));
		fun1(a1-33333);
		fun1((long) b);
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(b)));
		System.out.println(Long.toBinaryString(a1-1));
		/*System.out.println(b);
		System.out.println((long) b);
		System.out.println(a1 - 1);*/

		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);// 不用科学计数
		System.out.println("禁用科学计数法：num=" + nf.format(b));
	}

	public void fun1(long a) {
		System.out.println(a);
	}

	@Test
	public void test10() {
		LinkedHashMap<Integer, Integer> map = new  LinkedHashMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.remove(2);
		Set<Integer> integers = map.keySet();
		System.out.println(integers);
	}


	@Test
	public void test11() {
		HashMap<Integer, Integer> map = new  HashMap<>();
		map.put(3, 3);
		map.put(1, 1);
		map.put(2, 2);
		List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
		System.out.println(collect);
		ConcurrentSkipListSet<Object> objects = new ConcurrentSkipListSet<>();
		objects.size();

		ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
		Set<Object> objects1 = ConcurrentHashMap.newKeySet();
		objectObjectConcurrentHashMap.size();

		File file = new File("");
	}

	@Test
	public void test12() {
		String a = "\u51C9";
		String b = "\uF979";
		System.out.println(Integer.toHexString("凉".codePointAt(0)));
		System.out.println(a);
		System.out.println(b);
		System.out.println(a == b);
	}


	@Test
	public void test13() {
		System.out.println(3+3+3+4+2+10+3+2+2+7);
	}

	@Test
	public void test14() {
		ArrayList<Integer> objects = new ArrayList<>();
		int a = 10;
		for (int i1 = 0; i1 < a; i1++) {
			objects.add(i1);
		}

		//objects.add(4,33);
		objects.set(4,33);
		System.out.println(objects);
	}

	@Test
	public void test15() {
		int aa = 1111;//
		long x = -1L ^ (-1L << 54) - aa;
		System.out.println(x);
		System.out.println((long)(double) x);
		System.out.println();

		x = (-1L ^ (-1L << 53)) - (-1L ^ (-1L << 52));
		System.out.println(Long.toBinaryString(x));
		System.out.println(Long.toBinaryString(x));
		System.out.println(x);
		System.out.println((long)(double) x);
		System.out.println();

		x = -1L ^ (-1L << 52) - aa;
		System.out.println(x);
		System.out.println((long)(double) x);
		System.out.println();

		x = -1L ^ (-1L << 51) - aa;
		System.out.println(x);
		System.out.println((long)(double) x);
		System.out.println();

		System.out.println(Long.toBinaryString(-1L ^ (-1L << 2)).length());

	}

	@Test
	public void test16() throws InterruptedException {
		System.out.println(new Date(1622766630000L));
		while (true) {
			Thread.sleep(1111);
		}
	}

	@Test
	public void test17() throws InterruptedException {
		Object test15 = new Test15();
		//java.lang.Class.isInstance(Object);
		Runnable runnable = () -> {
			if(test15 == null) {

			}
		};
		/**
		 * ____3315_400
		 * ____3273_400
		 * ____3613_600
		 * ____4168100
		 * ____3274800
		 * --------------------
		 * ____3173500
		 * ____3100300
		 */
		TestUtil.testTime(runnable, 10000000);
	}

	@Test
	public void test18() throws InterruptedException {
		System.out.println("ddddd\ndddd");
		System.out.println("ddddd\r\ndddd");
	}

	@Test
	public void test19() throws Exception {
		AClass aClass = new AClass();
		Method default1 = aClass.getClass().getDeclaredMethod("default1");
		if(default1.isDefault()) {
			Object defaultValue = default1.getDefaultValue();
			System.out.println(defaultValue);
		}
		Object invoke = default1.invoke(aClass);
		//System.out.println(invoke);
		BigDecimal bigDecimal = new BigDecimal(1);
	}
}


/**
 * 0001
 * 1110
 * <p>
 * 1111
 */












