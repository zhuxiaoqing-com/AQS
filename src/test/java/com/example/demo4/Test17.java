package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.util.vo.Vector2f;
import com.example.demo4.testObj1.StringArrayObj;
import com.example.demo4.testObj1.Temp;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.youxi.entity.Position;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test17 {

	class Obj {
		private int obj1;
		private int obj2;

		public int getObj1() {
			return obj1;
		}

		public void setObj1(int obj1) {
			this.obj1 = obj1;
		}

		public int getObj2() {
			return obj2;
		}

		public void setObj2(int obj2) {
			this.obj2 = obj2;
		}
	}


	//  -XX:AutoBoxCacheMax=100000


	@Test
	public void test03() {
		ArrayList<Integer> objects = new ArrayList<>();
		int index = 10;
		for (int i = 0; i < index; i++) {
			objects.add(i);
		}

		Stream<Integer> sorted = objects.stream().sorted(Comparator.comparingInt(a -> a));
		Stream<ArrayList<Integer>> objects1 = Stream.of(objects, objects);
		objects.clear();
		objects1.forEach(System.out::println);

		sorted.forEach(System.out::println);
	}

	@Test
	public void test04() {
		System.out.println(TimeUtil.convertLocalDateTime(1715848476042L));
		System.out.println(TimeUtil.convertLocalDateTime(1715852077042L));
	}

	@Test
	public void test05() {
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
		int index = 10;
		for (int i = 0; i < index; i++) {
			map.put(i, i);
		}

		int a = 0;
		for (Integer value : map.values()) {
			if(a == 3){
				map.remove(4);
			}
			System.out.println(value);
			a++;
		}
	}

	@Test
	public void test06() {
		long a  = 100;
		System.out.println( (a | 7) & 7);
		System.out.println( (a | 8) & 8);
	}

	@Test
	public void test07() {
		String  a= "0,,,0,0";
		String[] split = a.split(",");
		System.out.println(Arrays.toString(split));

		for (String s : split) {
			System.out.println(s.length());
		}

		ArrayList<byte[]> objects = new ArrayList<>();
		byte[][] bytes = objects.toArray(new byte[][]{});
		a(bytes);
	}

	public void a(byte[]... aa) {
		String  a= "0,,,0,0";
		String[] split = a.split(",");
		System.out.println(Arrays.toString(split));

		for (String s : split) {
			System.out.println(s.length());
		}
	}

	@Test
	public void test08() {
		Map<Integer, Integer> treeMap = new TreeMap<>();
		Map<Integer, Integer> hashMap = new HashMap<>();

		int index = 10000;
		for (int i = 0; i < index; i++) {
			treeMap.put(i, i);
			hashMap.put(i, i);
		}

		ArrayList<Integer> objects = new ArrayList<>();
		for (int i = 0; i < index; i++) {
			objects.add(ThreadLocalRandom.current().nextInt(index));
		}

		int maxNum = 1000;
		TestUtil.testTime(()->{
			for (Integer object : objects) {
				treeMap.get(object);
			}
		}, maxNum,"treeMap");


		TestUtil.testTime(()->{
			for (Integer object : objects) {
				hashMap.get(object);
			}
		}, maxNum,"hashMap");

	}


	@Test
	public void test09() {
		System.out.println(5000/0.7);
	}

	@Test
	public void test10() {
		/*System.out.println(20000/12.5);
		String collect = Arrays.stream(Thread.currentThread().getStackTrace()).map(StackTraceElement::toString)
				.collect(Collectors.joining(System.lineSeparator()));
		System.out.println(collect);*/
		//0.5RMB 1G

		TestUtil.testTime(()->{Thread.currentThread();
		}, 10000_000_00,"111");
		TestUtil.testTime(()->{int a= 0;
		}, 10000_000_00,"111");
	}

	@Test
	public void test11() {
		// (295.8, 2.7, 537.3) => (240.8, 0.0, 566.8)
		Vector2f a = new Vector2f(295.8f, 537.3f);
		Vector2f b = new Vector2f(240.8f, 566.8f);
		Vector2f sub = a.sub(b);
		System.out.println(sub.length());

		Position ap = new Position(295.8f, 537.3f);
		Position bp = new Position(240.8f, 566.8f);

		System.out.println(ap.sub(bp).length());
	}

}

/*
 * abcdefghijklmnopqrst
 */













