package com.example.demo4;

import com.youxi.building.Misc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import javax.mail.Address;
import java.io.File;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.*;

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
		a.invoke(test14, new int[]{1, 2});
	}

	/*public void a(int a) {
		System.out.println("a(int a)");
	}*/

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

}


