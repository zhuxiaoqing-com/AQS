package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.example.demo4.testObj1.BuffTest;
import com.example.demo4.testObj1.Temp;
import com.google.gson.Gson;
import com.youxi.buff.Buff;
import com.youxi.building.Misc;
import com.youxi.entity.CMath;
import com.youxi.entity.Position;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test16 {

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

	class ChildObj extends Obj {
		private int childObj1;
		private int childObj2;

		public int getChildObj1() {
			return childObj1;
		}

		public void setChildObj1(int childObj1) {
			this.childObj1 = childObj1;
		}

		public int getChildObj2() {
			return childObj2;
		}

		public void setChildObj2(int childObj2) {
			this.childObj2 = childObj2;
		}
	}

	class ObjList<T extends Obj> {
		private T a;

		public T getA() {
			return a;
		}

		public void setA(T a) {
			this.a = a;
		}
	}


	@Test
	public void test01() throws InterruptedException {
		ChildObj childObj = new ChildObj();
		childObj.setObj1(1);
		childObj.setObj2(2);
		childObj.setChildObj1(11);
		childObj.setChildObj2(22);

		ObjList objObjList = new ObjList<>();
		objObjList.setA(childObj);


		String o = JSON.toJSONString(childObj);
		System.out.println(o);
	}

	//  -XX:AutoBoxCacheMax=100000
	@Test
	public void test02() {
		float[] floats = new float[36_000];
		for (int i = 0; i < 36000; i++) {
			floats[i] = (float) Math.sin(Math.toRadians(i / 100f));
		}

		int count = 100_000_000;
		TestUtil.testTime(() -> Math.sin(22.23), count, "sin");
		TestUtil.testTime(() -> Math.cos(22.23), count, "cos");
		TestUtil.testTime(() -> math(floats, 22.23f), count, "cache");
		TestUtil.testTime(() -> {
			int i = 2 + 3;
		}, count, "sum");
	}

	private float math(float[] floats, float d) {
		return floats[(int) (d * 100)];
	}

	@Test
	public void test03() {
		long minValue = Long.MIN_VALUE + 2;
		float v = 1.4f * minValue;

		System.out.println(Integer.toBinaryString(Float.floatToIntBits(v)));

		System.out.println((long) v);
		System.out.println(1 * minValue);
		System.out.println(Long.MIN_VALUE);
	}

	@Test
	public void test04() {
		HashMap<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> integerIntegerMap = Collections.unmodifiableMap(map);
		System.out.println(integerIntegerMap);
		System.out.println(integerIntegerMap.entrySet());
		System.out.println(integerIntegerMap.keySet());
		System.out.println(integerIntegerMap.values());

		map.put(1, 1);
		map.put(2, 1);
		map.put(4, 1);

		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		System.out.println(integerIntegerMap);
		System.out.println(integerIntegerMap.entrySet());
		System.out.println(integerIntegerMap.keySet());
		System.out.println(integerIntegerMap.values());
	}

	@Test
	public void test05() {
		float[] floats = new float[360];
		for (int i = 0; i < 360; i++) {
			floats[i] = (float) Math.sin(Math.toRadians(i)) * 4096;
		}

		System.out.println(Arrays.toString(floats));
/*

		float[] aaa = new float[360];
		for (int i = -360; i < 0; i++) {
			aaa[i + 360] = (float) Math.sin(Math.toRadians(i));
		}

		System.out.println(Arrays.toString(aaa));
*/

	}

	@Test
	public void test06() {
		System.out.println(Math.pow(2, 13));
		System.out.println((-1 << 12));
		System.out.println(~(-1 << 12));
	}

	@Test
	public void test07() {
		double v1 = 2 * Math.PI;
		double v = v1 / 4096;
		for (double i = 0; i < 2 * Math.PI; i += v) {
			System.out.println(Math.sin(i));
		}
	}

	@Test
	public void test08() {
		int count = 100;
		TreeSet<Integer> list = new TreeSet<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < count; i++) {
			list.add(i);
			map.put(i, i);
		}


		int count111 = 100_000_000;
		Integer index = 100;
		TestUtil.testTime(() -> list.contains(index), count111, "list");
		TestUtil.testTime(() -> map.get(index), count111, "map");
	}

	@Test
	public void test09() throws InterruptedException {
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		ArrayList<Integer> list3 = new ArrayList<>();

		int index = 100_000_000_0;
		for (int i = 0; i < index; i++) {
			list1.add(i);
			list2.add(i + 10);
			list3.add(i + 20);
		}

		Thread.sleep(20000);
		int i = 100;
		for (int i1 = 0; i1 < i; i1++) {
			Stream.of(list1, list2, list3).flatMap(Collection::stream).forEach((a) -> {
			});

		}
	}


	private void toString(ByteBuf buffer) {
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		System.out.println(Arrays.toString(bytes));
	}

	@Test
	public void test10() throws InterruptedException {
		ByteBuf buffer1 = PooledByteBufAllocator.DEFAULT.heapBuffer(110);
		ByteBuf buffer2 = PooledByteBufAllocator.DEFAULT.heapBuffer(110);
		buffer1.writeByte(11);
		buffer1.writeByte(11);
		buffer1.writeByte(11);
		buffer1.writeByte(11);
		buffer2.writeByte(22);
		buffer2.writeByte(22);
		buffer2.writeByte(22);

		System.out.println(buffer1.arrayOffset());
		System.out.println(buffer2.arrayOffset());
		System.out.println(buffer1.readableBytes());
		System.out.println(buffer2.readableBytes());

		buffer1.release();
		buffer2.release();
		buffer1.writeByte(1);
	}

	@Test
	public void test11() throws InterruptedException {
		ByteBuf buffer1 = PooledByteBufAllocator.DEFAULT.heapBuffer();
		ByteBuf buffer2 = PooledByteBufAllocator.DEFAULT.heapBuffer();
		ByteBuf buffer3 = PooledByteBufAllocator.DEFAULT.directBuffer();

		int index = 100;
		for (int i = 0; i < index; i++) {
			buffer3.writeByte(i);
		}

		buffer3.readInt();
		buffer3.getBytes(0, buffer2, buffer2.arrayOffset(), buffer3.readableBytes());

		System.out.println(buffer2.readableBytes());
		System.out.println(buffer3.readerIndex());
	}


	@Test
	public void test12() throws InterruptedException {
		ByteBuf buffer1 = PooledByteBufAllocator.DEFAULT.heapBuffer();
		ByteBuf buffer2 = PooledByteBufAllocator.DEFAULT.heapBuffer();
		ByteBuf directBuffer = PooledByteBufAllocator.DEFAULT.directBuffer();

		int index = 100;
		for (int i = 0; i < index; i++) {
			buffer2.writeByte(i);
		}

		directBuffer.writeBytes(buffer2);

		toString(directBuffer);
	}

	@Test
	public void test13() throws InterruptedException {
		ByteBuf buffer1 = PooledByteBufAllocator.DEFAULT.heapBuffer();
		ByteBuf buffer2 = PooledByteBufAllocator.DEFAULT.heapBuffer();
		ByteBuf directBuffer = PooledByteBufAllocator.DEFAULT.directBuffer();

		int index = 1000;
		for (int i = 0; i < index; i++) {
			directBuffer.writeByte(i);
		}

		directBuffer.readBytes(buffer2, 1000);

		toString(buffer2);
	}


	@Test
	public void test14() throws InterruptedException {
		HashMap<Integer, Integer> map = new HashMap<>();
		int index = 100;
		for (int i = 0; i < index; i++) {
			map.put(i, i);
		}

		for (Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
			Map.Entry<Integer, Integer> entry = iterator.next();

			if (entry.getKey() == 10) {
				iterator.remove();
			}

			if (entry.getKey() == 11) {
				iterator.remove();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int[] ints = new int[11];

		while (true) {
			Thread.sleep(3000);
			for (int anInt : ints) {

			}

		}
	}

	@Test
	public void test15() throws InterruptedException {
		float x = 1 / 0f;
		int intx = (int) x;

		System.out.println(x);

		System.out.println(intx);
	}

	@Test
	public void test16() throws InterruptedException {
		long id = 1423006710225633280L;
		System.out.println(id & 65535);

		ArrayList<Object> objects = new ArrayList<>();
		objects.containsAll(new ArrayList<>());
	}

	@Test
	public void test17() throws InterruptedException {
		// \u000d System.out.println("coder Hydra");
	}

	@Test
	public void test18() throws InterruptedException {
		System.out.println((int) Float.POSITIVE_INFINITY);
		System.out.println((int) Float.NEGATIVE_INFINITY);
		System.out.println((int) Float.NaN);
	}

	@Test
	public void test19() {
		//System.out.println((16382+4094)&~4095);
		/*System.out.println((1<<17)-1);
		System.out.println(0xfff);
		System.out.println(1000*1000*4);*/

		/*System.out.println(0x9FFFF / 1024);
		System.out.println(0xfffffff8- 0xfffff000);
		System.out.println(0xfffff000- 0xffffe000);
		*/
		Integer a = 0xfffffff8;
		a(a);
		a = 0xfffff000;
		a(a);
		a = 0xffffe000;
		a(a);
	}

	private void a(Integer a) {
		String x = Integer.toBinaryString(a);
		String a1 = x.substring(0, 10);
		String a2 = x.substring(10, 20);
		String a3 = x.substring(20, 32);
		System.out.println(Integer.parseInt(a1, 2));
		System.out.println(Integer.parseInt(a2, 2));
		System.out.println(Integer.parseInt(a3, 2));
		System.out.println("---------------");
	}

	@Test
	public void test20() throws UnsupportedEncodingException {
	/*	System.out.println(1193180/1000);
		long l = System.currentTimeMillis();
		int currSeed = (int) (l / 1000);

		int index =10;
	*//*	for (int skillId = 0; skillId < index; skillId++) {
		System.out.println("第 "+skillId +" 次---- 结果："+ new Random(currSeed+skillId).nextInt(10000));
		}

		System.out.println("----------------------------");
		currSeed=currSeed+1022;
		for (int skillId = 0; skillId < index; skillId++) {
			System.out.println("第 "+skillId +" 次---- 结果："+ new Random(currSeed+skillId).nextInt(10000));
		}*//*

		Random random = new Random(-222);
		for (int skillId = 0; skillId < index; skillId++) {
			System.out.println(random.nextInt(1000));
		}*/
		String a = "测试123";
		System.out.println(TestUtil.printHex(a.getBytes("GBK")));
	}


	@Test
	public void test21() {
		ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
		map.put(1, 1);
		System.out.println(map.putIfAbsent(1, 2));
		System.out.println(map.putIfAbsent(2, 2));
		System.out.println(map);

		System.out.println(Math.pow(10, 0));
	}

	@Test
	public void test22() {
		ArrayList<Integer> objects = new ArrayList<>();
		int index = 10;
		for (int i = 1; i < index; i++) {
			objects.add(i);
		}

		List<Integer> collect = objects.stream().skip(1).collect(Collectors.toList());
		System.out.println(collect);
	}


	@Test
	public void test23() {
		Temp temp = JSON.parseObject("", Temp.class);
		System.out.println(temp);
	}

	@Test
	public void test24() {
		long a = 1423006872251629568L;
		System.out.println(a >> 47);
		a = 1577818112484573184L;
		System.out.println(a >> 47);
		a = 1423006857546268672L;
		System.out.println(a >> 47);

		String aa = "2232$5555";
		System.out.println(aa.split("//$")[0]);
		System.out.println(aa.split("/$")[0]);
		System.out.println(aa.split("\\$")[0]);

		Object[] b = new Object[]{1, 2, 3};
		a(b);
	}


	public void a(Object... a) {
		for (Object o : a) {
			System.out.println(o);
		}
		System.out.println(Arrays.toString(a));
	}


	@Test
	public void test25() {
		int a = 111;
		System.out.println(Integer.toBinaryString(a));

		System.out.println(Integer.toBinaryString(a(a,2)));
	}


	public static int a(int value, int index) {
		return value &= ~(1 << index);
	}


	@Test
	public void test26() {
		System.out.println(0%3);
		System.out.println(1%3);
		System.out.println(2%3);
		System.out.println(3%3);
		System.out.println(4%3);
	}

	@Test
	public void test27() {
		HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
		hashMap.put(1,Arrays.asList(1,2,3));
		while (hashMap.entrySet().iterator().hasNext()) {
			Map.Entry<Integer, List<Integer>> next = hashMap.entrySet().iterator().next();
			System.out.println();
		}
	}

	@Test
	public void test28() {
		ArrayList<Integer> objects = new ArrayList<>();
		int index=  10;
		for (int i = 0; i < index; i++) {
			objects.add(i);
		}

		for (int i = 0; i < index; i++) {
			System.out.println(objects.stream().findAny().get());
		}
	}

	@Test
	public void test29() {
		LocalDate of = LocalDate.of(2123, 12, 4);
		LocalDate of1 = LocalDate.now();
		Duration between = Duration.between(of1.atStartOfDay(), of.atStartOfDay());
		System.out.println(between.getSeconds()*1000);
	}

	@Test
	public void test30() throws InterruptedException {
		 ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(1);
		 scheduled.schedule((Runnable) () -> System.out.println("1111111111111111111"), 0, TimeUnit.MILLISECONDS);

		 while (true) {
			 Thread.sleep(1000L);
		 }
	}
	public static final String HH_MM_SS = "HH:mm:ss";
	public static DateTimeFormatter HH_MM_SS_TimeFromatter = DateTimeFormatter.ofPattern(HH_MM_SS);
	@Test
	public void test31() {
		System.out.println(HH_MM_SS_TimeFromatter.format(LocalDateTime.now()));
		System.out.println(HH_MM_SS_TimeFromatter.format(LocalTime.now()));
	}

	@Test
	public void test32() {
		for (int i = 0; i < 4; ++i)
		{
		int dir = (i+3)&3;
			System.out.println(dir);
		}
	}

	@Test
	public void test33() {
		int i = LocalTime.of(2, 0).toSecondOfDay();
		System.out.println(i);
		int i1 = 2 * 60 * 60;
		System.out.println(i1);

		System.out.println(0x80);
		Pattern PATTERN_COUNTER= Pattern.compile("^[^\\\\]*\\.[^\\\\]*$");
		Matcher matcher = PATTERN_COUNTER.matcher("main-SERVER_ID.log");
		System.out.println(matcher.matches());
	}

}

/**
 * abcdefghijklmnopqrst
 */













