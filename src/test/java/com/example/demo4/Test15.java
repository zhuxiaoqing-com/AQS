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
		for (int i = 0; i< fun1(); i++) {

		}
	}

	int i = 3;
	public int fun1() {
		System.out.println(++i);
		return i;
	}
}

class MyThread extends Thread {
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


/**
 * 0001
 * 1110
 * <p>
 * 1111
 */












