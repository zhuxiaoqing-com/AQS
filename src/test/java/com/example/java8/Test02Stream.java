package com.example.java8;

import com.example.java8.entity.Employee;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test02Stream {
    @Test
    public void test01() {
        List<Employee> employees = new ArrayList<>();
        Map<Integer, Employee> collect = employees.stream().collect(Collectors.toMap(x -> x.getId(), x -> x));
        Map<Integer, Map<Integer, Employee>> a = new HashMap<>();
        Map<Integer, Map<Integer, Employee>> b = employees.stream().collect(Collectors.groupingBy(Employee::getAge, LinkedHashMap::new, Collectors.toMap(Employee::getId, Function.identity())));

    }


    @Test
    public void test02() {
        ArrayList<A> resources = new ArrayList<>();
        resources.add(new A(1, 3));
        resources.add(new A(1, 2));
        resources.add(new A(2, 3));
        resources.add(new A(2, 3));
        Map<Integer, Integer> collect = resources.stream()
                .collect(Collectors.groupingBy(A::getId, Collectors.summingInt(A::getCount)));
        System.out.println(collect);

        int sum = resources.stream().mapToInt(r -> r.getId() + r.getCount()).sum();
        System.out.println(sum);
    }

    class A {
        int id;
        int count;

        public A(int id, int count) {
            this.id = id;
            this.count = count;
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

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", count=" + count +
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
        hashMap.put(1, new A(2, 3));
        hashMap.put(2, new A(2, 2));
        hashMap.put(3, new A(3, 4));
        hashMap.put(4, new A(3, 5));
        hashMap.put(5, new A(1, 4));
        hashMap.put(6, new A(1, 5));

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
        Set<Integer> s1 = new HashSet<Integer>(){{add(1);add(2);add(3);}};
        Set<Integer> s2 = new HashSet<Integer>(){{add(3);add(4);add(5);}};
        Set<Integer> s3 = new HashSet<Integer>(){{add(5);add(6);add(7);}};

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

		Optional<Integer> min = s1.stream().max(Comparator.comparingInt(a -> a));
		System.out.println(min);
	}

}