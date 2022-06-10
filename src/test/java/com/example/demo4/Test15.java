package com.example.demo4;

import com.example.demo4.testObj1.AClass;
import com.example.demo4.testObj1.CeShiObj1;
import com.example.代码优化.design_mode.TowerAOI.Vector2f;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
	 * 101 1111 0 000 0000 0000 0000 0000 0000
	 * <p>
	 * 10 1101 0 010 1111 1110 1011 1111 1111
	 * 011 1111 1 000 0000 0000 0000 0000 0000
	 * 110 1110 0 000 0000 0000 0000 0000 0000
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
		fun1(a1 - 33333);
		fun1((long) b);
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(b)));
		System.out.println(Long.toBinaryString(a1 - 1));
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
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.remove(2);
		Set<Integer> integers = map.keySet();
		System.out.println(integers);
	}


	@Test
	public void test11() {
		HashMap<Integer, Integer> map = new HashMap<>();
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
		System.out.println(3 + 3 + 3 + 4 + 2 + 10 + 3 + 2 + 2 + 7);
	}

	@Test
	public void test14() {
		ArrayList<Integer> objects = new ArrayList<>();
		int a = 10;
		for (int i1 = 0; i1 < a; i1++) {
			objects.add(i1);
		}

		//objects.add(4,33);
		objects.set(4, 33);
		System.out.println(objects);
	}

	@Test
	public void test15() {
		int aa = 1111;//
		long x = -1L ^ (-1L << 54) - aa;
		System.out.println(x);
		System.out.println((long) (double) x);
		System.out.println();

		x = (-1L ^ (-1L << 53)) - (-1L ^ (-1L << 52));
		System.out.println(Long.toBinaryString(x));
		System.out.println(Long.toBinaryString(x));
		System.out.println(x);
		System.out.println((long) (double) x);
		System.out.println();

		x = -1L ^ (-1L << 52) - aa;
		System.out.println(x);
		System.out.println((long) (double) x);
		System.out.println();

		x = -1L ^ (-1L << 51) - aa;
		System.out.println(x);
		System.out.println((long) (double) x);
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
			if (test15 == null) {

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
		if (default1.isDefault()) {
			Object defaultValue = default1.getDefaultValue();
			System.out.println(defaultValue);
		}
		Object invoke = default1.invoke(aClass);
		//System.out.println(invoke);
		BigDecimal bigDecimal = new BigDecimal(1);
	}

	@Test
	public void test20() {
		/*int i = 1000;
		for (int i1 = 0; i1 < i; i1++) {
			System.out.println(ThreadLocalRandom.current().nextInt(2));
		}*/
		System.out.println("\\\\\\'a");
	}

	@Test
	public void test21() {
		int i = 10;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i1 = 0; i1 < i; i1++) {
			map.put(i1, i1);
		}
		String collect = map.entrySet().stream().map(e -> e.getKey() + "   " + e.getValue()).collect(Collectors.joining(System.getProperty("line.separator")));
		System.out.println(collect);
	}


	@Test
	public void test22() {
	/*	int i = 10;
		i*=0.2;
		System.out.println(i);
		System.out.println(new Date(1625122200000L));*/
		c();
	}

	public void c(int... a) {
		System.out.println(a);
	}

	@Test
	public void test23() {
		String a = "D:\\\\陈哈哈\\\\加班')";
		System.out.println(a);
		int i = 1;
		System.out.println((Integer) i);
	}

	@Test
	public void test24() {
		HashMap<Integer, AtomicLong> map = new HashMap<>();
		int a = 10;
		for (int i1 = 0; i1 < a; i1++) {
			map.put(i1, new AtomicLong(i1));
		}

		List<Map.Entry<Integer, AtomicLong>> collect = map.entrySet().stream()
				.sorted(Comparator.<Map.Entry<Integer, AtomicLong>>comparingLong(entry -> entry.getValue().get()).reversed())
				.collect(Collectors.toList());

		List<Map.Entry<Integer, AtomicLong>> collect1 = map.entrySet().stream()
				.sorted(Comparator.comparing(entry -> entry.getValue().get(), Collections.reverseOrder()))
				.collect(Collectors.toList());

		List<Map.Entry<Integer, AtomicLong>> collect2 = map.entrySet().stream()
				.sorted(Comparator.<Map.Entry<Integer, AtomicLong>, Comparable>comparing(entry -> entry.getValue().get()).reversed())
				.collect(Collectors.toList());

		System.out.println(collect);
	}

	@Test
	public void test25() {
		CeShiObj1 ceShiObj1 = new CeShiObj1();
		Method[] declaredMethods = ceShiObj1.getClass().getMethods();
		System.out.println(Arrays.toString(declaredMethods));
	}

	@Test
	public void test26() {
		//double x = 180/Math.PI;
		double x = 1;
		System.out.println(Math.atan2(1, 1) * x);
		System.out.println(Math.atan2(1, -1) * x);
		System.out.println(Math.atan2(-1, -1) * x);
		System.out.println(Math.atan2(-1, 1) * x);

		x = 180 / Math.PI;
		System.out.println(Math.atan2(1, 1) * x);
		System.out.println(Math.atan2(1, -1) * x);
		System.out.println(Math.atan2(-1, -1) * x);
		System.out.println(Math.atan2(-1, 1) * x);
	}

	@Test
	public void test27() {
		HashMap<Object, Long> map = new HashMap<>();
		map.put(new Object(), 1L);
		map.put(new Object(), 2L);
		map.put(new Object(), 3L);
		map.put(new Object(), 4L);
		System.out.println(map);

		map = new LinkedHashMap<>();
		map.put(new Object(), 1L);
		map.put(new Object(), 2L);
		map.put(new Object(), 3L);
		map.put(new Object(), 4L);
		System.out.println(map);
	}


	@Test
	public void test28() {
		long a = 1000002321324321421L;
		int count = 1000000000;
		TestUtil.testTime(() -> {
			long aa = a * a;
		}, count);
		TestUtil.testTime(() -> {
			long aa = a * a;
		}, count);
		TestUtil.testTime(() -> {
			double sqrt = Math.sqrt(a);
		}, count);
		TestUtil.testTime(() -> {
			double sqrt = Math.sqrt(a);
		}, count);
		TestUtil.testTime(() -> {
			long aa = a * a;
		}, count);
		List<List<Object>> partition = Lists.partition(new ArrayList<>(), 11);
	}

	@Test
	public void test29() {
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime() / 1000_000);
	}

	@Test
	public void test30() {
		int a = 2;
		int b = 4;
		Integer c = -2;
		System.out.println(Integer.toUnsignedLong(c));
	}

	@Test
	public void test31() {
		int size = 50;
		int bytes = 200;
		System.out.println(bytes * 100);
		System.out.println(40000); // 一人40kb 1000个人; = 40mb
	}

	@Test
	public void test32() {
		int i = 100;
		ArrayList<Long> list = new ArrayList<>();
		long now = System.currentTimeMillis() / 1000;
		for (int i1 = 0; i1 < i; i1++) {
			list.add(createBuildingId((int) now + i1));
		}

		ArrayList<Integer> count1 = new ArrayList<>();
		for (Long aLong : list) {
			int l = (int) (aLong & 3);
			count1.add(l);
		}

	/*	ArrayList<Integer> count2 = new ArrayList<>();
		ArrayList<Integer> hashCount = new ArrayList<>();
		for (Long aLong : list) {
			int l = (int) (aLong.hashCode() & 3);
			hashCount.add(aLong.hashCode());
			count2.add(l);
		}
*/

		ArrayList<Integer> count3 = new ArrayList<>();
		ArrayList<Integer> hashCount = new ArrayList<>();
		for (Long aLong : list) {
			int longHash = aLong.hashCode();
			int h = longHash ^ (longHash >>> 16);
			int l = (int) (h % 2);
			hashCount.add(h);
			count3.add(l);
		}

		System.out.println(list);
		System.out.println(count1);
		//System.out.println(count2);
		System.out.println(count3);
		System.out.println(hashCount);

	/*
		System.out.println(hashCount);
		for (Integer integer : hashCount) {
			System.out.println(Integer.toBinaryString(integer));
		}*/
/*
		for (Long aLong : list) {
			String s = Long.toBinaryString(aLong);
			//System.out.println(s);
			//System.out.println(s.substring(0,s.length() - 32));
			System.out.println(s.substring(s.length() - 32));
			System.out.println();
		}*/
	}

	@Test
	public void test33() {
		Vector2f vector2f1 = new Vector2f(89496, 74646);
		Vector2f vector2f2 = new Vector2f(90996, 73146);

		Vector2f vector2f3 = new Vector2f(91289, 72853);
		Vector2f vector2f4 = new Vector2f(89778, 74364);

		System.out.println(getScreenPos(vector2f1));
		System.out.println(getScreenPos(vector2f2));
		System.out.println(getScreenPos(vector2f3));
		System.out.println(getScreenPos(vector2f4));

	}

	public Vector2f getScreenPos(Vector2f p) {
		int x = (int) (p.getX() / 45000);
		int z = (int) (p.getY() / 45000);

		return new Vector2f(x, z);
	}

	public static long getIndexByPos(int x, int z) {
		long _x = x;
		return _x << 32 | z;
	}


	/**
	 * 生成用户id 每秒生成32768个id
	 **/
	public synchronized static long createBuildingId(int second) {
		return (100 * 1L << 47) + (second * 1L << 15) + 0;
	}


	@Test
	public void test34() {
		String a = " ";
		String a1 = " ";
		System.out.println(a.codePointAt(0));
		System.out.println(a1.codePointAt(0));
	}

	private ArrayList createList() {
		ArrayList intList = new ArrayList<>();
		intList.add(2);
		intList.add(3);
		intList.add(4);
		return intList;
	}

	@Test
	public void test35() {
		int index = 10;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i1 = 0; i1 < index; i1++) {
			map.put(i1, i1);
		}

		System.out.println(map.ceilingKey(-1));
		System.out.println(map.ceilingKey(0));
		System.out.println(map.ceilingKey(1));
		System.out.println(map.ceilingKey(5));
		System.out.println(map.ceilingKey(9));
		System.out.println(map.ceilingKey(10));
		System.out.println(map.ceilingKey(11));

		System.out.println("---------------------");
		System.out.println(map.floorKey(-1));
		System.out.println(map.floorKey(0));
		System.out.println(map.floorKey(1));
		System.out.println(map.floorKey(5));
		System.out.println(map.floorKey(9));
		System.out.println(map.floorKey(10));
		System.out.println(map.floorKey(11));
	}

	@Test
	public void test36() {
		int nverts = 4;

		for (int i = 0, j = nverts - 1; i < nverts; j = i++) {
			System.out.println("i: " + i);
			System.out.println("j: " + j);
			System.out.println("--------------");
		}
	}

	@Test
	public void test37() {
		long l = System.currentTimeMillis();
		System.out.println(l / 1000);
		int l1 = (int) (l / 1000);
		System.out.println(l1);

		System.out.println(Integer.MAX_VALUE / 1000 / 60 / 60 / 24);
	}

	@Test
	public void test38() {
		int index = 10;
		SkillRandom skillRandom = new SkillRandom(1000);
		for (int i1 = 0; i1 < index; i1++) {
			System.out.println(skillRandom.nextInt(1000));
		}
	}

	@Test
	public void test39() {
		int index = 10;

		for (int i1 = -5; i1 < 6; i1++) {
			System.out.println(i1 >>> 2);
		}

		System.out.println("---------------------");
		System.out.println("---------------------");
		System.out.println("---------------------");
		for (int i1 = -5; i1 < 6; i1++) {
			System.out.println(i1 >> 2);
		}
	}

	@Test
	public void test40() {
		int[] a = new int[4];
		System.out.println(a.length);
	}

}


/**
 * 0001
 * 1110
 * <p>
 * 1111
 */












