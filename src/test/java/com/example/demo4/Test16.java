package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.util.vo.Vector2f;
import com.example.demo4.testObj1.AClass;
import com.example.demo4.testObj1.StringArrayObj;
import com.example.demo4.testObj1.Temp;
import com.google.gson.JsonObject;
import com.youxi.entity.Position;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.Time;
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
		//
		System.out.println("coder Hydra");
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

		System.out.println(Integer.toBinaryString(a(a, 2)));
	}


	public static int a(int value, int index) {
		return value &= ~(1 << index);
	}


	@Test
	public void test26() {
		System.out.println(0 % 3);
		System.out.println(1 % 3);
		System.out.println(2 % 3);
		System.out.println(3 % 3);
		System.out.println(4 % 3);
	}

	@Test
	public void test27() {
		HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
		hashMap.put(1, Arrays.asList(1, 2, 3));
		while (hashMap.entrySet().iterator().hasNext()) {
			Map.Entry<Integer, List<Integer>> next = hashMap.entrySet().iterator().next();
			System.out.println();
		}
	}

	@Test
	public void test28() {
		ArrayList<Integer> objects = new ArrayList<>();
		int index = 10;
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
		System.out.println(between.getSeconds() * 1000);
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
		for (int i = 0; i < 4; ++i) {
			int dir = (i + 3) & 3;
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
		Pattern PATTERN_COUNTER = Pattern.compile("^[^\\\\]*\\.[^\\\\]*$");
		Matcher matcher = PATTERN_COUNTER.matcher("main-SERVER_ID.log");
		System.out.println(matcher.matches());
	}

	@Test
	public void test34() {
		a(5);
		a(4);
		a(20);
	}

	private void a(int aa) {
		System.out.println((aa >> 4) & 0x3);
		System.out.println((aa >> 0) & 0x3);
		System.out.println((aa >> 2) & 0x3);
		System.out.println("---------------------");
	}


	@Test
	public void test35() throws IllegalAccessException {

		System.out.println(1 << 15);
		// 1 00000 00000 00000
		System.out.println(Integer.toBinaryString(1 << 15));

		System.out.println(1 << 16);
		System.out.println(Integer.toBinaryString(1 << 16));

		System.out.println(Short.MAX_VALUE);
	}

	@Test
	public void test36() {
		HashSet<Long> set = new HashSet<>();
		int index = 50000;

		for (int i = 0; i < index; i++) {
			long itemId = IdFactory.createItemId();
			boolean add = set.add(itemId);
			if (!add) {
				System.out.println("error index :" + i);
			}
		}

	}

	@Test
	public void test37() {
		List<Integer> integers = Arrays.asList(4, 2, 0, 6);

		for (Integer integer : integers) {
			System.out.println((integer + 4) & 0x7);
		}
	}

	@Test
	public void test38() {
		for (int i = 0; i < 4; ++i) {
			int dir = (i + 3) & 3;
			System.out.println(dir);
		}
	}

	@Test
	public void test39() {
		ArrayList<Integer> objects = new ArrayList<>();
		int index = 10;
		for (int i = 0; i < index; i++) {
			objects.add(i);
		}

		long count = objects.stream().mapToInt(a -> a).count();
		System.out.println(count);
	}

	@Test
	public void test40() {
		System.out.println(LocalDateTime.now());
		long minute30 = TimeUtil.minuteMills * 30;
		long l = TimeUtil.getTime() / minute30 * minute30;
		System.out.println(new Date(l));

		System.out.println((int) 1.9);
		System.out.println((int) 1.1);
	}

	@Test
	public void test41() {
		LocalDateTime localDateTime = LocalDate.of(2023, 1, 1).atStartOfDay();
		long l = TimeUtil.toEpochMilli(localDateTime);
		LocalDateTime localDateTime1 = LocalDate.of(2030, 1, 1).atStartOfDay();
		long l2 = TimeUtil.toEpochMilli(localDateTime1);
		long l1 = (l2 - l) / 1000;
		System.out.println(l1);

		String s = Long.toBinaryString(l1);
		System.out.println(s.length());


		long aaaa = TimeUtil.toEpochMilli(LocalDate.of(2050, 1, 1).atStartOfDay());
		System.out.println(aaaa / 1000);
		System.out.println(Integer.MAX_VALUE);
	}

	@Test
	public void test42() {
		String a= "amount=0.01&appId=11114&channelCode=122323&dealAmount=0.01&extrasParams={\"playerId\":\"15762875192016896\",\"payForId\":1,\"serverId\":112}&orderGoodsId=1&orderNo=0120230410191015754287&orderSubject=首充礼包&payTime=1681125084&payType=1&productOrderNo=15762881241546752&sign=0eca7be4fa285545cb6957c3274b0a22&uid=2092548";
		Map<String, Object> urlRequest = getURLRequest(a);
		JSONObject jsonObject = new JSONObject(urlRequest);
		TenHeroCallbackData tenHeroCallbackData = jsonObject.toJavaObject(TenHeroCallbackData.class);
		System.out.println(tenHeroCallbackData);
	}

	public static Map<String, Object> getURLRequest(String URL) {
		Map<String, Object> mapRequest = new HashMap<>();
		if (URL == null || URL.equals("")) {
			return mapRequest;
		}
		StringTokenizer params;
		// 构造一个用来解析URL的StringTokenizer对象，并指定'&'分隔符。
		StringTokenizer entrys = new StringTokenizer(URL, "&");
		// hasMoreTokens()判断是否还有下一个分隔符号存在
		while (entrys.hasMoreTokens()) {
			// 再构造一个StringTokenizer对象，并指定'='分隔符。
			params = new StringTokenizer(entrys.nextToken(), "=");
			// items.nextToken()返回从当前位置到下一个分隔符的字符串 例：a=1，返回a
			mapRequest.put(params.nextToken(), params.hasMoreTokens() ? params.nextToken() : "");
		}
		return mapRequest;
	}

	@Test
	public void test43() {
		ArrayList<Integer> cont = new ArrayList<>();
		cont.add(1);
		cont.add(3);
		cont.add(4);
		cont.add(2);
		cont.add(1);
		cont.add(3);
		cont.add(4);
		for (int j = 0; j < cont.size(); )
		{
			int nj = (j+1) % cont.size();
			if (cont.get(j) == cont.get(nj))
			{
				// 重复的话就把j删除;整个数组数据向前移动一格
				for (int k = j; k < cont.size()-1; ++k)
					cont.set(k, cont.get(k+1));
				cont.remove(cont.size()-1);
			}
			else
				++j;
		}
		System.out.println(cont);
	}

	@Test
	public void test44() {
		System.out.println(1f/0);
		System.out.println(0/1f);
		System.out.println(Float.MAX_VALUE * 2);
		float x = Float.MAX_VALUE * 2;
		System.out.println(x*x*x);
		System.out.println(x/Float.MAX_VALUE);
		System.out.println((int)x);

		System.out.println(Float.NaN);
		System.out.println(0.0f/0.0f);
		System.out.println(0/100f);
	}

	@Test
	public void test45() {
		StringArrayObj stringArrayObj = new StringArrayObj();
		System.out.println(JSON.toJSONString(stringArrayObj));
		String[] strings = new String[3];
		strings[0]= "a";
		strings[1]= "aa";
		strings[2]= "aaa";
		stringArrayObj.setString(strings);
		System.out.println(JSON.toJSONString(stringArrayObj));
	}

	@Test
	public void test46() {
		String[] strings = new String[3];
		strings[0]= "a";
		strings[1]= "aa";
		strings[2]= "aaa";
		_test46(strings);
	}


	public void _test46(Object... a){
		System.out.println(a.length);
	}

	@Test
	public void test47() throws InterruptedException {
		Map<Integer, Integer> map = new ConcurrentHashMap<>();
		int index = 10000;
		for (int i = 0; i < index; i++) {
			map.put(index, index);
		}

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.submit(()->{
			while (true) {
				map.remove(ThreadLocalRandom.current().nextInt(10000));
				Thread.yield();
			}
		});
		executorService.submit(()->{
			while (true) {
				map.remove(ThreadLocalRandom.current().nextInt(10000));
				Thread.yield();
			}
		});
		executorService.submit(()->{
			while (true) {
				int key = ThreadLocalRandom.current().nextInt(10000);
				map.put(key, key);
				Thread.yield();
			}
		});

		executorService.submit(()->{
			while (true) {
				int key = ThreadLocalRandom.current().nextInt(10000);
				map.put(key, key);
				Thread.yield();
			}
		});

		Thread.sleep(5000);

		System.out.println("start-------------------------");

		int maxNum = 100000;
		TestUtil.testTime(() -> {
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				int a = 1;
			}
		}, maxNum, "aaa");


		TestUtil.testTime(() -> {
			map.forEach((a, b) -> {
				int aa = 1;
			});
		}, maxNum, "aaa");

		/*TestUtil.testTime(() -> {
			HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>(map);
			integerIntegerHashMap.forEach((a, b) -> {
				int aa = 1;
			});
		}, maxNum, "aaa");*/


		TestUtil.testTime(() -> {
			map.entrySet().stream().skip(ThreadLocalRandom.current().nextInt(10000)).limit(ThreadLocalRandom.current().nextInt(10000)).forEach((b) -> {
				int aa = 1;
			});
		}, maxNum, "aaa");
	}

	@Test
	public void test48() throws InterruptedException {
		int x = 16 * 100_000_0;
		System.out.println(x/1000/1000); //mb
		Vector2f vector2f = new Vector2f(0.001f, 0.001f);
		float dot = vector2f.dot(vector2f);
		System.out.println(dot);
	}

	@Test
	public void test49() throws InterruptedException {
		/*System.out.println(TimeUtil.convertLocalDateTime( 1686318219560L));
		System.out.println(TimeUtil.convertLocalDateTime( 1686321820560L));*/
		ArrayList<Integer> objects = new ArrayList<>();
		int index = 10;
		for (int i = 0; i < index; i++) {
			objects.add(i);
		}
		List<Integer> integers = objects.subList(0, 0);
		System.out.println(integers);
	}

	@Test
	public void test50() throws InterruptedException {
		int index = 200;
		Map<Integer, Integer> hashMap = new HashMap<>();
		Map<Integer, Integer> cMap = new ConcurrentHashMap<>();
		int[] array = new int[index];

		for (int i = 0; i < index; i++) {
			hashMap.put(i, i);
			cMap.put(i, i);
			array[i] = i;
		}
		int findInt =10;
		int maxNum =1000000;

		TestUtil.testTime(()->cMap.get(findInt),maxNum,"cmap");
		TestUtil.testTime(()->hashMap.get(findInt),maxNum,"hashMap");
		TestUtil.testTime(()->{
			for (int i : array) {
				if(findInt== i) {
					return;
				}
			}
		},maxNum,"array");

		TestUtil.testTime(()->hashMap.get(findInt),maxNum, "hashMap");
		TestUtil.testTime(()->{
			for (int i : array) {
				if(findInt== i) {
					return;
				}
			}
		},maxNum, "array");
		TestUtil.testTime(()->cMap.get(findInt),maxNum, "cmap");

		TestUtil.testTime(()->{
			for (int i : array) {
				if(findInt== i) {
					return;
				}
			}
		},maxNum, "array");
		TestUtil.testTime(()->cMap.get(findInt),maxNum,"cmap");
		TestUtil.testTime(()->hashMap.get(findInt),maxNum,"hashMap");
	}

	@Test
	public void test51() throws InterruptedException {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
		int index = 10;
		for (int i = 0; i < index; i++) {
			map.put(i,i);
		}
		map.entrySet().removeIf(a->a.getKey() == 1);
		System.out.println(map);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry);
		}
	}

	@Test
	public void test52() throws Exception {
		ArrayList<Integer> objects = new ArrayList<>();
		objects.add(1);
		objects.add(2);
		//objects.add(4);
		//objects.add(3);
		//objects.add(5);
		//objects.add(6);
		//objects.add(7);

		/*int index = 100;
		for (int i = 0; i < index; i++) {
			objects.add(7);
		}*/

		int a= 0;
		for (Integer object : objects) {
			a |= object;
		}
		System.out.println(a);
	}
}

/**
 * abcdefghijklmnopqrst
 */













