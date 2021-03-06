package com.example.misc;

import com.example.demo4.TestUtil;
import com.youxi.util.RandomUtil;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Encode {
	@Test
	public void test01() throws Throwable {
		int maxNum = 100000;
		Encode test12 = new Encode();
		long startNano;

		startNano = System.nanoTime();
		for (int i = 0; i < maxNum; i++) {
			test12.a();
		}
		System.out.println((System.nanoTime() - startNano));

		startNano = System.nanoTime();
		for (int i = 0; i < maxNum; i++) {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodHandle a = lookup.findVirtual(Encode.class, "a", MethodType.methodType(void.class));
			a.invoke(test12);
		}
		System.out.println((System.nanoTime() - startNano));


	}

	public void a() {
	}

	@Test
	public void test02() {
		short a = -1;
		System.out.println(a == -1);
		System.out.println(Long.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-1));
	}

	@Test
	public void test03() {
		Object[] objects = {2};
		System.out.println(objects[0].getClass());
	}

	@Test
	public void test04() {
		List<Integer> objects = new ArrayList<>();
		for (int i = 0; i <= 100; i++) {
			objects.add(i);
		}

	/*	TestUtil.testTime(() -> {
			List<Integer> random = RandomUtil.getRandom(objects, 10000);
			//System.out.println(random);
		}, 1);
*/
		TestUtil.testTime(() -> {
			List<Integer> random = RandomUtil.randomNoRepeatNums(0, objects.size() - 1, 10);
			ArrayList<Object> objects1 = new ArrayList<>(10);
			for (Integer integer : random) {
				objects1.add(objects.get(integer));
			}
			//System.out.println(objects1);
		}, 1);

		TestUtil.testTime(() -> {
			List<Integer> random = RandomUtil.getRandomValues(0, objects.size() - 1, 10);
			ArrayList<Object> objects1 = new ArrayList<>(10);
			for (Integer integer : random) {
				objects1.add(objects.get(integer));
			}
			//System.out.println(objects1);
		}, 1);
	}

	@Test
	public void test05() {
		int maxNum = 10000;
		int randomNum = 5000;

		TestUtil.testTime(() -> RandomUtil.randomNoRepeatNums(0, maxNum, randomNum), 11111);
		TestUtil.testTime(() -> RandomUtil.getRandomArray(0, maxNum, randomNum), 11111);

		List<Integer> integers = RandomUtil.randomNoRepeatNums(0, maxNum, randomNum);
		int[] randomArray = RandomUtil.getRandomArray(0, maxNum, randomNum);
		HashSet<Integer> objects = new HashSet<>();
		for (int i : randomArray) {
			objects.add(i);
		}
		System.out.println(new HashSet<>(integers).size() + "......" + objects.size());
	}

	// 1302
	@Test
	public void test06() {
		Instant start = Instant.now();
		int length = 10;
		int[] sum = new int[length];
		for (int i = 0; i < 1_000_000_000; i++) {
			sum[i % 10] = sum[i % 10] + i;
		}
		int sumNum = 0;
		for (int i : sum) {
			sumNum += i;
		}
		System.out.println(sumNum);

		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}

	// 253
	@Test
	public void test07() {
		Instant start = Instant.now();
		float sum = 0;
		for (float i = 0; i < 1_000_000_0; i++) {
			sum *= i;
		}
		System.out.println(sum);
		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}


	@Test
	public void test08() {
		Instant start = Instant.now();
		float sum1 = 0;
		float sum2 = 0;
		float sum3 = 0;
		float sum4 = 0;
		float sum5 = 0;
		int whileCount = 1_000_000_0;
		int i1 = whileCount / 5;
		for (float i = 0; i < i1; i++) {
			sum2 *= i;
		}
		for (float i = 0; i < i1; i++) {
			sum3 *= i;
		}
		for (float i = 0; i < i1; i++) {
			sum4 *= i;
		}
		for (float i = 0; i < i1; i++) {
			sum5 *= i;
		}
		for (float i = 0; i < i1; i++) {
			sum1 *= i;
		}
		System.out.println(sum1 * sum2 * sum3 * sum4 * sum5);

		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}


	@Test
	public void test12() {
		Instant start = Instant.now();
		float sum1 = 0;
		float sum2 = 0;
		float sum3 = 0;
		float sum4 = 0;
		float sum5 = 0;
		int whileCount = 1_000_000_0;
		int i1 = whileCount / 5;
		for (float i = 0; i < whileCount; i += 5) {
			sum1 *= i;
			sum2 *= i + 1;
			sum3 *= i + 2;
			sum4 *= i + 3;
			sum5 *= i + 4;
		}
		System.out.println(sum1 * sum2 * sum3 * sum4 * sum5);

		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}

	@Test
	public void test09() {
		Instant start = Instant.now();
		int sum = 0;
		for (int i = 0; i < 10_000_000_00; i += 2) {
			sum += 2;
		}
		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}

	@Test
	public void test10() {
		Instant start = Instant.now();

		long sum = 0;
		for (long i = 0; i < 10_000_000_00L; i++) {
			sum += i;
		}
		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
		System.out.println(sum);
	}

	@Test
	public void test11() {
		Instant start = Instant.now();
		long sum = 10_000_000_00L * (10_000_000_00L - 1L) / 2L;

		Instant end = Instant.now();
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
		System.out.println(sum);
	}

	int inlineVar = 0;

	@Test
	public void test13() {
		int i = inlineVar;
		test15();
	}

	@Test
	public void test14() {
		int i = inlineVar;
		test15();
	}

	@Test
	public void test15() {
		System.out.println("inline method");
	}

	@Test
	public void test16() {
		Boolean a = true;
		System.out.println(a.equals("true"));
	}

	@Test
	public void test17() {
		String s = "";
		String[] ds = s.split(",", -1);
		System.out.println(Arrays.toString(ds) + "...." + ds.length);
	}

	@Test
	public void test18() {
		HashSet<Object> set = new HashSet<>();
		ArrayList<Object> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			set.add(i);
			list.add(i);
		}

		TestUtil.testTime(() -> list.contains(11), 111111);
		TestUtil.testTime(() -> set.contains(11), 111111);
	}

	@Test
	public void test19() throws Exception {
		String fileName = "1111.xml";
		String absolutePath = new File(fileName).getAbsolutePath();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2.创建DocumentBuilder对象
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document d = builder.parse(fileName);
	}

	@Test
	public void test20() throws Exception {
		String a = "\uFEFF<";
		String b = "<";
		String c = "<";
	}

	@Test
	public void test21() throws Exception {
		long start = System.currentTimeMillis();
		long startNano = System.nanoTime();
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			sum++;
		}

		long end = System.currentTimeMillis();
		long endNano = System.nanoTime();
		System.out.println(end - start);
		System.out.println(endNano - startNano);
	}

	@Test
	public void test22() {
	/*	String aa = a1();
		System.out.println(aa);*/
		a2(1, 3, 4, 5);
	}

	private void a2(Object... args) {
		String[] strings1 = Arrays.asList(args).toArray(new String[0]);
		System.out.println(Arrays.toString(strings1));
	}

	private String a1(String... args) {

		String join = String.join(":", args);
		return join;
	}

	@Test
	public void test23() throws UnsupportedEncodingException {
		String a = "我是的1e";
		System.out.println(Arrays.toString(a.getBytes("GBK"))); // [-50, -46, -54, -57]
		System.out.println(Arrays.toString(a.getBytes())); // [-26, -120, -111, -26, -104, -81]
	}

	@Test
	public void test24() {
		String a = "\uD845\uDE3E";
		byte[] bytes = a.getBytes();
		for (byte aByte : bytes) {
			int i = Byte.toUnsignedInt(aByte);
			System.out.print(Integer.toBinaryString(aByte));
			System.out.print("   ");
		}
	}

	@Test
	public void test25() throws UnsupportedEncodingException {
		String a = "这个字\uD845\uDE3E\uD855\uDE3E无法识别";
		String s = filterSpecialContent("\uD855\uDE3E");
		System.out.println("\uD855\uDE3E");
		System.out.println("\uD865\uDE3F");
//		System.out.println(s);
		//String s1 = filterUtf84(a);
		System.out.println(filterUtf84(a));
		System.out.println(filterUtf162(a));
		System.out.println("\u10000");
		System.out.println("\u1FFFFF");
		System.out.println(Integer.toBinaryString(0xD845));
		System.out.println(Integer.toBinaryString(0xD845));
	}


	// 只保留名字中的 字母丶中文还有日文平假字[\u3040-\u309F]片假字[\u30A0-\u30ff]片假字扩展[\u31f0-\u31ff]
	String special = "[^a-zA-Z丶\\u4e00-\\u9fa5\\u3040-\\u309F\\u30A0-\\u30ff\\u31f0-\\u31ff]";
	Pattern NAME_SPECIAL = Pattern.compile(special);

	/**
	 * 将特殊符号空格等过滤掉
	 */
	private String filterSpecialContent(String content) {
		return NAME_SPECIAL.matcher(content).replaceAll("*");
	}


	private String filterUtf84(String a) throws UnsupportedEncodingException {
		byte[] bytes = a.getBytes("UTF-8");
		byte[] bytes1 = new byte[bytes.length];
		int bytes1Index = 0;
		int utf84Index = -100;
		for (int i = 0; i < bytes.length; i++) {
			String s = Integer.toBinaryString(bytes[i]);
			if ((bytes[i] & 0xf0) == 0xf0) {
			//	System.out.println(s.substring(s.length() - 8));
				utf84Index = i;
			}
			if (i <= utf84Index + 3) {
			//	System.out.println(s.substring(s.length() - 8));
				continue;
			}
			bytes1[bytes1Index++] = bytes[i];
		}
		byte[] bytes2 = new byte[bytes1Index];
		for (int i = 0; i < bytes2.length; i++) {
			bytes2[i] = bytes1[i];
		}
		return new String(bytes2);
	}

	private String filterUtf162(String a) throws UnsupportedEncodingException {
		byte[] bytes = a.getBytes("UTF-16");

		byte[] bytes3 = new byte[bytes.length];

		int bytes1Index = 0;
		int utf84Index = -100;
		for (int i = 0; i < bytes.length; i++) {
			if ((i & 1) == 0 && (bytes[i] & 0xfc) == 0xd8) {
				//System.out.println(byteToBinaryString(bytes[i]));
				utf84Index = i;
			}
			if (i <= utf84Index + 3) {
				//System.out.println(byteToBinaryString(bytes[i]));
				continue;
			}
			bytes3[bytes1Index++] = bytes[i];
		}
		byte[] bytes4 = new byte[bytes1Index];
		for (int i = 0; i < bytes4.length; i++) {
			bytes4[i] = bytes3[i];
		}
		return new String(bytes4,"UTF-16");
	}

	@Test
	public void test26() throws UnsupportedEncodingException {
		String s = new String("\uD835\uDD46");
		System.out.println(Arrays.toString(s.getBytes("UTF-8")));
		System.out.println(s);
		System.out.println(s.length());
		System.out.println("\uD835\uDD46");
		System.out.println(0xD835);
		System.out.println(0xDD46);
		System.out.println(0xD835 + 0xDD46);
		System.out.println(0x1D546);
		System.out.println(0x1D546);

		System.out.println(Arrays.toString("a".getBytes("utf-8")));
		System.out.println(Arrays.toString("a".getBytes("utf-16")));
		System.out.println(Arrays.toString("a".getBytes("utf-32")));
		System.out.println(Arrays.toString("\uD835\uDD46".getBytes("utf-8")));
		System.out.println(Arrays.toString("\uD835\uDD46".getBytes("utf-16")));
		System.out.println(Arrays.toString("\uD835\uDD46".getBytes("utf-32")));
		//System.out.println(Arrays.toString("\uD845\uDE3E".getBytes("utf-16")));
	}

	private String byteToBinaryString(byte a) {
		String s = Integer.toBinaryString(a);
		if (a >= 0) {
			return s;
		}
		return s.substring(s.length() - 8);
	}

	@Test
	public void test27() throws UnsupportedEncodingException {
		System.out.println(Integer.toBinaryString(78));
		System.out.println(Integer.toBinaryString(79));
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(0xfc));
	}

	public void a(int a, int b, int c, int d) {

	}

}


