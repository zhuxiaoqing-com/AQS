package com.example.demo4;

import com.youxi.building.Misc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.models.auth.In;
import org.junit.Test;

import javax.mail.Address;
import java.io.File;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test14 {

	public static void main(String[] args) {
		int whileMax = 2000000000;

		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < whileMax; i++) {
			sum++;
		}
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		long sum2 = 0;
		for (int i = 0; i < whileMax; i++) {
			sum2++;
		}
		System.out.println(System.currentTimeMillis() - start);
	}


	@Test
	public void test01() {
		int value = 555553135;
		System.out.println(Integer.toBinaryString(value));
		while ((value & -128) != 0) {
			int s = (int) value & 127 | 128;
			System.out.println(Integer.toBinaryString(s));
			value >>>= 7;
		}

	}

	@Test
	public void test02() {
		int value = 555553135;
		int s = (int) value & 127 | 128;
		System.out.println(Integer.toBinaryString(value & 127));
		System.out.println(Integer.toBinaryString(value & 127 | 128));
	}

	@Test
	public void test03() {
		String a = "\uD835\uDD46";
		System.out.println("\uD835\uDD46");
		char[] chars = "\uD835\uDD46".toCharArray();
	}

	@Test
	public void test() {
		freeMarkerSumup01();
	}

	/**
	 * 对freeMarker进行总结
	 */
	public void freeMarkerSumup01() {

		try {
			//创建freeMarker配置实例
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File("WebRoot/templates"));

			//创建数据模型
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", "徐书一");
			map.put("randoms", Integer.parseInt(new Random().nextInt(100) + ""));

			List<Object> list = new ArrayList<Object>();
			list.add(1);
			list.add(1);
			list.add(1);
			list.add(1);
			map.put("lst", list);

			map.put("date1", new Date());

			//加载模板文件
			Template template = configuration.getTemplate("a.ftl");

			//显示生成数据，并将数据打印到控制台
			OutputStreamWriter writer = new OutputStreamWriter(System.out);
			template.process(map, writer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Test
	public void test04() {
		System.out.println(Integer.toBinaryString(0x00A2));
	}

	@Test
	public void test05() {
		System.out.println("客户端坐标" + Misc.getIntHigh(3604525) + "___" + Misc.getIntLow(3604525));
		System.out.println("服务器坐标" + Misc.getIntHigh(2818107) + "___" + Misc.getIntLow(2818107));
	}

	volatile ArrayList<Object> objects;

	@Test
	public void test06() {
		long start = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();

		}
		long end = System.nanoTime();
		System.out.println((end - start) / 1000 / 1000);

		start = System.nanoTime();
		for (int i = 0; i < 100_000; i++) {
			objects = new ArrayList<>();
			objects.add(1);
			objects.add(3);
			objects.add(6);
		}
		end = System.nanoTime();
		System.out.println((end - start) / 1000 / 1000);
	}

	@Test
	public void test07() {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
	}

	@Test
	public void test08() throws Exception {
		Test14 test14 = new Test14();
		Method a = test14.getClass().getDeclaredMethod("a", int[].class);
		a.invoke(test14, new int[0]);
}

/*
	public void aa() {
		System.out.println("aa()");
	}

	public void a(int a) {
		System.out.println("a(int a)");
	}
*/

	public void a(int... a) {
		System.out.println("a(int... a)");
	}


	@Test
	public void test09() {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
	}

	int currentStepId;
	int default_currentStepId = 10;
	int STEP = 10;

	private Set<Set<Long>> a = new HashSet<>();

	@Test
	public void test10() {
		String a1 = "a";
		String a2 = "a";
		//System.out.println("aa" + a1 == a2);
	}

	@Test
	public void test11() {
		System.out.println(-1L ^ (-1L << 22));
		System.out.println(-1L ^ (-1L << 2));
		//System.out.println("aa" + a1 == a2);
		System.out.println(-1L ^ (-1L << 18) / 16);

		System.out.println(16383 * 4); // 65532
		// 10位(1023)线程id + 22位(4194303)地图id +  自增id

		/**
		 * 现跨度为 16 的 一个线程最多可以有 16383 个地图
		 * 现在有4个线程 可以有 65532 个地图
		 *
		 * 12位(空闲没有使用) + 18位(线程id + STEP*n) + 32位地图id
		 * STEP 是跨度 现值为 16
		 * threadId = sceneId >>32 % 16
		 * 这样的话 如果 线程数大于 16 就会有问题;
		 * 比如 17+16*1 = 33; 33 % 16 = 1; threadId = 1;
		 * 本来应该是 线程id为17的; 结果threadId却为1了;
		 *
		 */
	}

	@Test
	public void test12() {
		System.out.println(365 * 2 / 24);
	}

	@Test
	public void test13() {
		int a = 2;
		switch (a) {
			case 1:
				System.out.println(1);
			case 2:
				System.out.println(2);
			case 3:
				System.out.println(3);
			case 4:
				System.out.println(4);
		}
	}

	@Test
	public void test14() {
		int a = 2;
		a += 3 * 4;
		System.out.println(a);
	}


	@Test
	public void test15() {
		int a = 2;
		switch (a) {
			case 1:
				int b = 2;
				System.out.println(b);
				break;
			case 2:
				b = 4;
				System.out.println(b);
		}
	}

	/**
	 * str hashcode计算
	 * 31*x + y = 31*a + b
	 * 31*x - 31*a = b - y;
	 * 31*(x - a) = b - y;
	 * x - a = 1;
	 * b - y = 31;
	 * <p>
	 * 由上可得：对于任意两个字符串 xy 和 ab，
	 * 如果它们满足 x-a=1，即第一个字符的 ASCII 码值相差为 1，
	 * 同时满足 b-y=31，即第二个字符的 ASCII 码值相差为 -31。
	 * 那么这两个字符的 hashCode 一定相等。
	 */
	@Test
	public void test16() {
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
		System.out.println("AaBB".hashCode());
		System.out.println("BBAa".hashCode());
		System.out.println("BBAa".hashCode());
		System.out.println("AaBB".hashCode());
	}

	@Test
	public void test17() {
		ArrayList<Integer> objects = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			objects.add(1);
		}
		Set<Integer> collect = objects.stream().collect(Collectors.toSet());
		System.out.println(collect);
	}
	@Test
	public void test18() {
		String a = "d;g;h;;;g;";
		System.out.println(Arrays.toString(a.split(";", -1)));;
		String[] split = a.split(";");
		for (String s : split) {
			System.out.println(s);
		}
	}
}













