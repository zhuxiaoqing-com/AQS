package com.example.java8;

import com.example.java8.entity.Employee;
import com.google.common.collect.Lists;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test02Stream {
	@Test
	public void test01() {
		List<Employee> employees = new ArrayList<>();
		Map<Integer, Employee> collect = employees.stream().collect(Collectors.toMap(Employee::getId, x -> x));
		Map<Integer, Map<Integer, Employee>> a = new HashMap<>();
		Map<Integer, Map<Integer, Employee>> b = employees.stream()
				.collect(Collectors.groupingBy(Employee::getAge, LinkedHashMap::new, Collectors.toMap(Employee::getId, Function.identity())));


		Map<Integer, List<Map.Entry<Integer, Employee>>> collect1 = collect.entrySet().stream()
				.collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.toList()));



		Map<Integer, List<Map.Entry<Integer, Employee>>> collect2 = collect.entrySet().stream()
				.collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.toList()));

	}


	@Test
	public void test02() {
		ArrayList<A> resources = new ArrayList<>();
		resources.add(new A(1, 3, 1));
		resources.add(new A(1, 2, 1));
		resources.add(new A(2, 3, 1));
		resources.add(new A(2, 3, 1));
		Map<Integer, Integer> collect = resources.stream()
				.collect(Collectors.groupingBy(A::getId, Collectors.summingInt(A::getCount)));

		System.out.println(collect);

		int sum = resources.stream().mapToInt(r -> r.getId() + r.getCount()).sum();
		System.out.println(sum);
	}

	class A {
		int id;
		int count;
		int coun2;

		public A(int id, int count, int coun2) {
			this.id = id;
			this.count = count;
			this.coun2 = coun2;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getCoun2() {
			return coun2;
		}

		public void setCoun2(int coun2) {
			this.coun2 = coun2;
		}

		@Override
		public String toString() {
			return "A{" +
					"id=" + id +
					", count=" + count +
					", coun2=" + coun2 +
					'}';
		}
	}


	@Test
	public void test03() {
		ArrayList<Object> objects = new ArrayList<>();
		objects.add(1);
		objects.add(2);
		objects.add(3);
		objects.add(4);
		objects.add(5);
		List<Object> collect = objects.stream().skip(1).collect(Collectors.toList());
		System.out.println(collect);
		List<Object> collect1 = objects.stream().distinct().collect(Collectors.toList());
		System.out.println(collect1);
	}

	@Test
	public void test04() {
		double v = 1544000 * 0.06;
		System.out.println(v);
		System.out.println(v / 8);
	}

	@Test
	public void test05() {
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("祝", 1);
		map.put("小", 2);
		map.put("庆", 3);

		LinkedHashMap<String, Integer> collect = map.entrySet().stream().sorted((o1, o2) -> 1).
				collect(Collectors.toMap(e -> e.getKey(), e -> 2, (o1, o2) -> {
					System.out.println(o1);
					System.out.println("......");
					System.out.println(o2);
					return 1;
				}, LinkedHashMap::new));

		LinkedHashMap<String, Integer> collect2 = map.entrySet().stream().sorted((o1, o2) -> 1).
				collect(LinkedHashMap::new, (subMap, entry) -> subMap.put(entry.getKey(), entry.getValue()), LinkedHashMap::putAll);

		System.out.println(collect);
	}

	/**
	 * 如果有重复的就会合并 Collectors.toMap(key, value, mergeFunction, HashMap::new);
	 */
	@Test
	public void test06() {
		ArrayList<TestData> list = new ArrayList();
		for (int i = 0; i <= 10; i++) {
			list.add(new TestData(i, i));
		}
		list.add(new TestData(1, 11));
		list.add(new TestData(3, 11));
		list.add(new TestData(1, 11));

		HashMap<Integer, Integer> collect = list.stream().collect(Collectors.toMap(a -> a.id, a -> a.count, (o1, o2) -> {
			System.out.println(o1);
			System.out.println(o2);
			return o1 + o2;
		}, HashMap::new));
		System.out.println(collect);

	collect = list.stream().collect(Collectors.toMap(a -> a.id, a -> a.count, (o1, o2) -> o1, HashMap::new));
	}


	/**
	 * 如果有重复的就会合并 Collectors.toMap(key, value, mergeFunction, HashMap::new);
	 */
	@Test
	public void test07() {
		ArrayList<TestData> list = new ArrayList();
		for (int i = 0; i <= 10; i++) {
			list.add(new TestData(i, i));
		}
     /*   list.add(new TestData(1, 11));
        list.add(new TestData(3, 11));
        list.add(new TestData(1, 11));*/


		List<List<TestData>> partition = Lists.partition(list, 4);
		System.out.println(partition);

		Map<Object, Object> map = new HashMap<>();
		//map.putIfAbsent()
	}

	class TestData implements Cloneable {
		int id;
		int count;

		public TestData(int id, int count) {
			this.id = id;
			this.count = count;
		}

		@Override
		public String toString() {
			return id + "";
		}
	}

	@Test
	public void test08() {
		HashMap<Integer, A> hashMap = new HashMap<>();
		hashMap.put(1, new A(2, 3, 1));
		hashMap.put(2, new A(2, 2, 1));
		hashMap.put(3, new A(3, 4, 1));
		hashMap.put(4, new A(3, 5, 1));
		hashMap.put(5, new A(1, 4, 1));
		hashMap.put(6, new A(1, 5, 1));

		TreeMap<Integer, List<A>> collect = hashMap.values().stream().collect(Collectors.groupingBy(A::getId, TreeMap::new, Collectors.toList()));
		System.out.println(collect);
		HashMap<Integer, List<Integer>> collect2 = hashMap.values().stream().collect(Collectors.groupingBy(A::getId, HashMap::new, Collectors.mapping(A::getCount, Collectors.toList())));
		System.out.println(collect2);
	}

	@Test
	public void test09() {
		ArrayList<TestData> list = new ArrayList();
		for (int i = 0; i <= 10; i++) {
			list.add(new TestData(i, i));
		}
     /*   list.add(new TestData(1, 11));
        list.add(new TestData(3, 11));
        list.add(new TestData(1, 11));*/


		//list.stream().filter(a -> true).map(a -> a.clone()).collect(Collectors.toList());
	}

	@Test
	public void test10() {
		Set<Set<Integer>> sets = new HashSet<>();
		Set<Integer> s1 = new HashSet<Integer>() {{
			add(1);
			add(2);
			add(3);
		}};
		Set<Integer> s2 = new HashSet<Integer>() {{
			add(3);
			add(4);
			add(5);
		}};
		Set<Integer> s3 = new HashSet<Integer>() {{
			add(5);
			add(6);
			add(7);
		}};

		sets.add(s1);
		sets.add(s2);
		sets.add(s3);

		//HashSet<Integer> collect = sets.stream().collect(HashSet::new, (set, entry) -> set.addAll(entry), HashSet::addAll);
		HashSet<Integer> collect = sets.stream().collect(HashSet::new, HashSet::addAll, HashSet::addAll);
		System.out.println(collect);

		Set<Integer> collect1 = sets.stream().flatMap(Collection::stream).collect(Collectors.toSet());
		System.out.println(collect1);

		List<Integer> collect2 = sets.stream().flatMap(Collection::stream).collect(Collectors.toList());
		System.out.println(collect2);

		Optional<Integer> max = s1.stream().max(Comparator.comparingInt(a -> a));
		System.out.println(max);
	}

	@Test
	public void test11() {
		List<Integer> integers = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			integers.add(i);
		}

		Collections.sort(integers, Comparator.reverseOrder());
		List<Integer> collect = integers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(collect);
		Optional<Integer> max = integers.stream().max(Comparator.comparingInt(a -> a));
		System.out.println(max);
	}

	@Test
	public void test12() {
		int[] ints = new int[10];
		Arrays.fill(ints, -1);
		Map<Long, Long> collect = Arrays.stream(ints)
				.asLongStream()
				.boxed()
				// 一个输出跟输入一样的Lambda表达式对象
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


		HashMap<Integer, String> map = new HashMap<>();
		int i =10;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i1 = 0; i1 < i; i1++) {
			list.add(i1);
			map.put(i1,i1+"dd");
		}

		Map<String, Long> collect1 =list.stream()
				// 一个输出跟输入一样的Lambda表达式对象
				.collect(Collectors.groupingBy(a->map.get(a), Collectors.counting()));
		System.out.println(collect1);
	}

	/**
	 * Compare
	 */
	@Test
	public void test13() {
		ArrayList<A> objects = new ArrayList<>();
		
		int index = 2;
		for (int i = 0; i < index; i++) {
			for (int i1 = 0; i1 < index; i1++) {
				for (int i2 = 0; i2 < index; i2++) {
					objects.add(new A(i,i1,i2));
				}
			}
		}

		List<A> collect = objects.stream().sorted(Comparator.comparing(A::getId).reversed().thenComparing(A::getCount).thenComparingInt(A::getCoun2))
				.collect(Collectors.toList());
		System.out.println(collect);

		List<A> collect1 = objects.stream().sorted(Comparator.comparing(A::getId).reversed().thenComparing(A::getCount).thenComparingInt(A::getCoun2).reversed())
				.collect(Collectors.toList());
		System.out.println(collect1);

		List<A> collect2 = objects.stream().sorted(Comparator.comparing(A::getId).thenComparing(A::getCount).reversed().thenComparingInt(A::getCoun2))
				.collect(Collectors.toList());
		System.out.println(collect2);


		List<A> collect3 = objects.stream().sorted(Comparator.comparing(A::getId, Comparator.reverseOrder()).thenComparing(A::getCount).thenComparing(A::getCoun2, Comparator.reverseOrder()))
				.collect(Collectors.toList());
		System.out.println(collect3);


		Optional<A> max = objects.stream().max(Comparator.comparing(A::getId).thenComparing(A::getCount));
		//System.out.println(max.get());

		objects.stream().sorted(Comparator.comparing(a -> a.getCount()));
		objects.stream().sorted(Comparator.<A>comparingInt(a -> a.getCount()).reversed());

	}

	@Test
	public void test16() {
		HashMap<Integer, AtomicLong> map = new HashMap<>();
		int a = 10;
		for (int i1 = 0; i1 < a; i1++) {
			map.put(i1, new AtomicLong(i1));
		}

		List<Map.Entry<Integer, AtomicLong>> collect = map.entrySet().stream()
				.sorted(Comparator.<Map.Entry<Integer, AtomicLong>>comparingLong(entry -> entry.getValue().get()).reversed())
				.collect(Collectors.toList());

		List<Map.Entry<Integer, AtomicLong>> collect1 = map.entrySet().stream()
				.sorted(Comparator.comparing(entry -> entry.getValue().get(), Collections.reverseOrder()))
				.collect(Collectors.toList());

		List<Map.Entry<Integer, AtomicLong>> collect2 = map.entrySet().stream()
				.sorted(Comparator.<Map.Entry<Integer, AtomicLong>, Comparable>comparing(entry -> entry.getValue().get()).reversed())
				.collect(Collectors.toList());

		List<Map.Entry<Integer, AtomicLong>> collect3 = map.entrySet().stream()
				.sorted(Comparator.comparing((Map.Entry<Integer, AtomicLong>entry) -> entry.getValue().get()).reversed())
				.collect(Collectors.toList());

		System.out.println(collect);
	}

	@Test
	public void test14() {
		List<Integer> integers = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			integers.add(i);
		}

		List<Integer> collect = integers.stream().skip(2).limit(2).collect(Collectors.toList());
		System.out.println(collect);

		Stream<Integer> limit = integers.stream().filter(a -> a != 0).skip(2).limit(2);
		System.out.println(limit.collect(Collectors.toList()));

	}

	@Test
	public void test15() {
		HashMap<Integer, Integer> map = new HashMap<>();
		int a = 10;
		for (int i = 0; i < a; i++) {
			map.put(i, i);
		}
		//map.entrySet().removeIf(e -> e.getValue() > 4);
		map.values().removeIf(e->e>4);
		System.out.println(map);

		String collect = map.values().stream().map(Object::toString).collect(Collectors.joining("连接符", "前缀", "后缀"));
		System.out.println(collect);
	}

	@Test
	public void test17() {
		int a = 100;
		List<Integer> collect = Stream.iterate(1, f -> f + 1).limit(32).collect(Collectors.toList());
		//System.out.println(collect);

	/*	System.out.println(splitList(collect, 10));
		System.out.println(splitList(collect, 1));
		System.out.println(splitList(collect, 110));*/
	//	System.out.println(splitList(collect, -1));
		System.out.println(splitList(collect, Integer.MAX_VALUE));
		System.out.println(splitList(collect, Integer.MIN_VALUE));
	}

	private <T> List<List<T>> splitList(List<T> list, int num) {
		// 向上取整
		int limit = (list.size() + num - 1) / num;
		return Stream.iterate(0, f -> f + 1).limit(limit)
				.map(a -> list.stream().skip((long) a * num).limit(num).collect(Collectors.toList()))
				.collect(Collectors.toList());
	}



	static int count = 0;
	static int count2 = 0;
	@Test
	public void test18() {
		ArrayList<A> aArrayList = new ArrayList<>();
		aArrayList.add(new A(1,1,1 ));
		aArrayList.add(new A(6,1,1 ));
		aArrayList.add(new A(5,1,1 ));
		aArrayList.add(new A(3,1,1 ));
		aArrayList.add(new A(2,1,1 ));
		aArrayList.add(new A(4,1,1 ));

		List<A> collect = aArrayList.stream().sorted(Comparator.comparingInt(a -> {
			count++;
			return a.id;
		})).collect(Collectors.toList());

		Optional<A> first = aArrayList.stream().max(Comparator.comparingInt(a -> {
			count2++;
			return a.id;
		}));

		System.out.println(count);
		System.out.println(count2);
	}

	@Test
	public void test19() {
		ArrayList<Integer> objects = new ArrayList<>();
		System.out.println(objects.stream().map(a->a.toString()).collect(Collectors.joining(";")));
		objects.add(1);
		System.out.println(objects.stream().map(a->a.toString()).collect(Collectors.joining(";")));
		objects.add(2);
		System.out.println(objects.stream().map(a->a.toString()).collect(Collectors.joining(";")));
	}

	@Test
	public void test20() {
		HashMap<Integer, Integer> map = new HashMap<>();
		int index = 1000;
		for (int i = 0; i < index; i++) {
			map.put(i,i);
		}
		Object[] objects = map.keySet().toArray();

		List<Integer> collect = map.keySet().stream().unordered().limit(10).collect(Collectors.toList());
		System.out.println(collect);

		System.out.println((char)97);

	}

	@Test
	public void test21() {
		ArrayList<Integer> list = new ArrayList<>();

		System.out.println(list.stream().allMatch(a->a>0));
		System.out.println(list.stream().noneMatch(a->a>0));
		System.out.println(list.stream().anyMatch(a->a>0));

	}

	@Test
	public void test22() {
		ArrayList<Boolean> list = new ArrayList<>();
		list.add(true);
		list.add(true);
		list.add(true);
		list.add(false);
		list.add(false);
		list.add(false);
		list.add(false);

		List<Boolean> collect = list.stream().sorted(Comparator.comparing(a -> a)).collect(Collectors.toList());
		System.out.println(collect);
		List<Boolean> collect2 = list.stream().sorted(Comparator.comparing(a -> a,Comparator.reverseOrder())).collect(Collectors.toList());
		System.out.println(collect2);

	}

	@Test
	public void test23() {
		HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
		hashMap.put(1,Arrays.asList(1,1));
		hashMap.put(2,Arrays.asList(1,2));
		hashMap.put(3,Arrays.asList(1,3));

		Map<Integer, Integer> collect = hashMap.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, b -> b.getValue().stream().mapToInt(c -> c).sum()));
		System.out.println(collect);
	}

	@Test
	public void test24() {
		HashMap<Integer, Integer> map = new HashMap<>();
		int a = 10;
		for (int i = 0; i < a; i++) {
			map.put(0, i);
		}

		Optional<Map.Entry<Integer, Integer>> max = map.entrySet().stream()
				.max(Comparator.<Map.Entry<Integer, Integer>>comparingInt(Map.Entry::getKey)
						.thenComparingLong(Map.Entry::getValue));
		System.out.println(max);
	}

}










