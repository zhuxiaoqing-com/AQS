package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.youxi.building.Misc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.omg.SendingContext.RunTime;

import javax.mail.Address;
import java.io.File;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test14 {

	public static void main1(String[] args) {
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
		System.out.println(Arrays.toString(a.split(";", -1)));
		;
		String[] split = a.split(";");
		for (String s : split) {
			System.out.println(s);
		}
	}

	public static void main2(String[] args) {
	/*	Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.println("111111");
				}
			}
		};
		t.setDaemon(true);
		t.start();*/
		new Thread() {
			@Override
			public void run() {
				while (true) {

				}
			}
		}.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("Bye");
	}

	@Test
	public void test19() {
		System.out.println(Integer.parseInt("11111111", 2));
		System.out.println(Integer.parseInt("11111110", 2));
		System.out.println(Integer.parseInt("11111100", 2));
		System.out.println(Integer.parseInt("11111000", 2));
		System.out.println(Integer.parseInt("11110000", 2));
		System.out.println(Integer.parseInt("11100000", 2));
		System.out.println(Integer.parseInt("11000000", 2));
		System.out.println(Integer.parseInt("10000000", 2));
	}

	@Test
	public void test20() {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			list.add(i);
		}
		List<Integer> collect = list.stream().sorted(Comparator.comparingInt(a -> (int) a)).collect(Collectors.toList());
		System.out.println(collect);
	}

	@Test
	public void test21() {
		int a = 6;
		int b = 3;
		TestUtil.testTime(() -> {
			int aa = (int) Math.ceil(a / (float) b);
		}, 100000000);
		TestUtil.testTime(() -> {
			int aa = (a + b - 1) / b;
		}, 100000000);


		System.out.println((a + b - 1) / b);
		System.out.println(Math.ceil(a / (float) b));
	}

	/**
	 * ......................damagePercentFloor : 30
	 * 93
	 * 100
	 * 51
	 * 30
	 * ......................damagePercentFloor : 100
	 * 100
	 * 100
	 * 100
	 * 100
	 * ......................damagePercentFloor : 0
	 * 90
	 * 100
	 * 30
	 * 0
	 * ......................damage : -1000
	 * -900
	 * -1000
	 * -300
	 * 0
	 */
	@Test
	public void test22() {
		System.out.println("......................damagePercentFloor : 30");
		System.out.println(damageDamping(20, 90, 100));
		System.out.println(damageDamping(30, 100, 100));
		System.out.println(damageDamping(30, 30, 100));
		System.out.println(damageDamping(30, 0, 100));

		System.out.println("......................damagePercentFloor : 100");
		System.out.println(damageDamping(100, 90, 100));
		System.out.println(damageDamping(100, 100, 100));
		System.out.println(damageDamping(100, 30, 100));
		System.out.println(damageDamping(100, 0, 100));

		System.out.println("......................damagePercentFloor : 0");
		System.out.println(damageDamping(0, 90, 100));
		System.out.println(damageDamping(0, 100, 100));
		System.out.println(damageDamping(0, 30, 100));
		System.out.println(damageDamping(0, 0, 100));

		System.out.println("......................damage : -1000");
		System.out.println(damageDamping(0, 90, -1000));
		System.out.println(damageDamping(0, 100, -1000));
		System.out.println(damageDamping(0, 30, -1000));
		System.out.println(damageDamping(0, 0, -1000));
	}

	@Test
	public void test24() {
		System.out.println("......................damagePercentFloor : 30");
		System.out.println(damageDamping2(20, 90, 100));
		System.out.println(damageDamping2(30, 100, 100));
		System.out.println(damageDamping2(30, 30, 100));
		System.out.println(damageDamping2(30, 0, 100));

		System.out.println("......................damagePercentFloor : 100");
		System.out.println(damageDamping2(100, 90, 100));
		System.out.println(damageDamping2(100, 100, 100));
		System.out.println(damageDamping2(100, 30, 100));
		System.out.println(damageDamping2(100, 0, 100));

		System.out.println("......................damagePercentFloor : 0");
		System.out.println(damageDamping2(0, 90, 100));
		System.out.println(damageDamping2(0, 100, 100));
		System.out.println(damageDamping2(0, 30, 100));
		System.out.println(damageDamping2(0, 0, 100));

		System.out.println("......................damage : -1000");
		System.out.println(damageDamping2(0, 90, -1000));
		System.out.println(damageDamping2(0, 100, -1000));
		System.out.println(damageDamping2(0, 30, -1000));
		System.out.println(damageDamping2(0, 0, -1000));
	}

	@Test
	public void test25() {

		TestUtil.testTime(() -> damageDamping(0, 90, -1000), 2222222);
		TestUtil.testTime(() -> damageDamping2(0, 90, -1000), 2222222);
		TestUtil.testTime(() -> bombDamageDamping3(100, 90, 1000, 20), 2222222);

		TestUtil.testTime(() -> damageDamping(0, 90, -1000), 222222222);
		TestUtil.testTime(() -> bombDamageDamping3(100, 90, 1000, 20), 222222222);
	}


	/**
	 * 衰减伤害百分比 = (100 - 距离中心点距离百分比) * (100 - 伤害波动下限) / 100
	 * 衰减伤害 = 衰减伤害百分比 * 伤害 /100f
	 * final伤害 = 总伤害 - 衰减伤害
	 *
	 * @param damagePercentFloor 伤害波动下限
	 * @param centerDisPercent   距离中心点距离百分比 	 1 - 该单位距离中心点距离 / 中心点与最大边缘距离
	 * @param damage             中心点伤害
	 * @return 衰减后的伤害值
	 */
	public int damageDamping(int damagePercentFloor, int centerDisPercent, int damage) {
		// 如果伤害百分比下限是百分之100 说明没有波动直接返回
		if (damagePercentFloor == 100) {
			return damage;
		}
		// 每距离离中心点百分之一远 就衰减 damageCentiDamping 伤害;
		float damageCentiDamping = (100 - damagePercentFloor) / 100f;
		// 计算到底衰减了百分之多少伤害
		float damageTotalDamping = (100 - centerDisPercent) * damageCentiDamping;
		// 衰减的伤害总值
		float totalDampingDamage = damageTotalDamping * damage / 100f;
		return (int) (damage - totalDampingDamage);
	}

	/**
	 * 衰减伤害百分比 = (100 - 距离中心点距离百分比) * (100 - 伤害波动下限) / 100
	 *
	 * @param damagePercentFloor 伤害波动下限
	 * @param centerDisPercent   距离中心点距离百分比 	 1 - 该单位距离中心点距离 / 中心点与最大边缘距离
	 * @param damage             中心点伤害
	 * @return 衰减后的伤害值
	 */
	public int damageDamping2(float damagePercentFloor, float centerDisPercent, int damage) {
		damagePercentFloor = damagePercentFloor / 100;
		centerDisPercent = centerDisPercent / 100;
		float noCalcDamage = damage * damagePercentFloor;
		float calcDamage = (damage - noCalcDamage) * centerDisPercent;
		return (int) (calcDamage + noCalcDamage);
	}

	/**
	 * 爆炸衰减公式
	 * 默认伤害* [1 - 伤害计算点距离中心点距离/ 爆炸半径 * (1 - 最低伤害百分比) + 最低伤害百分比]
	 *
	 * @param damage             默认伤害
	 * @param centerDistance     伤害计算点距离中心点距离
	 * @param bombRadius         爆炸半径
	 * @param damagePercentFloor 最低伤害百分比
	 * @return 衰减后的伤害值
	 */
	public static int bombDamageDamping3(int damage, float centerDistance, float bombRadius, double damagePercentFloor) {
		// bombRadius是除数不能为0
		if (bombRadius == 0) {
			return damage;
		}

		// 最低伤害百分比 必须在 (0,1) 之间
		if (damagePercentFloor >= 1 || damagePercentFloor <= 0) {
			return damage;
		}

		centerDistance = Math.min(centerDistance, bombRadius);
		return (int) (damage * (1 - centerDistance / bombRadius * (1 - damagePercentFloor) + damagePercentFloor));
	}

	/**
	 * 爆炸衰减公式
	 * 默认伤害* [(1 - 伤害计算点距离中心点距离/ 爆炸半径) * (1 - 最低伤害百分比) + 最低伤害百分比]
	 *
	 * @param damage             默认伤害
	 * @param centerDistance     伤害计算点距离中心点距离
	 * @param bombRadius         爆炸半径
	 * @param damagePercentFloor 最低伤害百分比
	 * @return 衰减后的伤害值
	 */
	public static int bombDamageDamping4(int damage, float centerDistance, float bombRadius, double damagePercentFloor) {
		// bombRadius是除数不能为0
		if (bombRadius == 0) {
			return damage;
		}

		// 最低伤害百分比 必须在 (0,1) 之间
		if (damagePercentFloor >= 1 || damagePercentFloor <= 0) {
			return damage;
		}

		centerDistance = Math.min(centerDistance, bombRadius);
		return (int) (damage * ((1 - centerDistance / bombRadius) * (1 - damagePercentFloor) + damagePercentFloor));
	}

	@Test
	public void test26() {
		System.out.println(damageDamping(20, (int) ((1 - 70 / 100f) * 100), 100));
		System.out.println(damageDamping2(0, 90, 100));
		System.out.println(bombDamageDamping3(100, 70, 100, 0.2));
		System.out.println(bombDamageDamping4(100, 70, 100, 0.2));
	}

	@Test
	public void test27() {
		Object[] objects = new Object[0];
		System.out.println(objects.getClass().getSimpleName());

	}

	@Test
	public void test28() {
		int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String s = JSON.toJSONString(ints);
		Object parse = JSON.parse(s);
		System.out.println(parse);
	}

	@Test
	public void test29() {
		double a = 1000f / 0;
		System.out.println(a);
		System.out.println((int) (a * 1000));
	}

	@Test
	public void test30() {
		String a = "a";
		switch (a) {
			case "a":
				System.out.println("a");
				break;
			case "b": {
				System.out.println("b");
			}
		}
		Runnable runnable = () -> System.out.println();
	}

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println(".....ddfdfsfsf")));
	}

	@Test
	public void test31() {
		int i = 10;
		for (int i1 = 0; i1 < i; i1++) {
			System.out.println(Runtime.getRuntime().availableProcessors());
		}
	}

	@Test
	public void test32() {
		ArrayList<Integer> objects = new ArrayList<>();
		int i = 10;
		for (int i1 = 0; i1 < i; i1++) {
			objects.add(i1);
		}

		List<Integer> collect = objects.stream().sorted((a, b) -> -1).collect(Collectors.toList());
		System.out.println(collect);
	}

	@Test
	public void test33() {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int i = 10;
		for (int i1 = 0; i1 < i; i1++) {
			map.put(i1*10, i1);
		}
		System.out.println(map);
		System.out.println(map.lastKey());
		System.out.println(map.firstKey());
		System.out.println(map.higherKey(70));
		System.out.println(map.ceilingKey(79));
		System.out.println(map.higherKey(80));
		System.out.println(map.ceilingKey(80));
		System.out.println(map.ceilingKey(-1));
	}

	@Test
	public void test34() {
		ArrayList<Object> objects = new ArrayList<>();
		int i = 10;
		for (int i1 = 0; i1 < i; i1++) {
			objects.add(null);
		}

		objects.stream().filter(o->o.getClass() == null).collect(Collectors.toList());
	}

}













