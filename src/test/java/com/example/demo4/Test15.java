package com.example.demo4;

import com.alibaba.fastjson.JSON;
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
		String a = "30;22";
		String[] split = a.split(";");

		System.out.println(Float.parseFloat(split[0]));
		System.out.println(Float.parseFloat(split[1]));
	}
}


/**
 * 0001
 * 1110
 * <p>
 * 1111
 */












