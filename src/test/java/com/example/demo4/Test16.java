package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.youxi.building.Misc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
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
		System.out.println(id&65535);

		ArrayList<Object> objects = new ArrayList<>();
		objects.containsAll(new ArrayList<>());
	}

	@Test
	public void test17() throws InterruptedException {
	// \u000d System.out.println("coder Hydra");
	}

	@Test
	public void test18() throws InterruptedException {
		System.out.println((int)Float.POSITIVE_INFINITY);
		System.out.println((int)Float.NEGATIVE_INFINITY);
		System.out.println((int)Float.NaN);
	}
}

/**
 * abcdefghijklmnopqrst
 */













