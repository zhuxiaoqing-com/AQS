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


}

/*
 * abcdefghijklmnopqrst
 */













