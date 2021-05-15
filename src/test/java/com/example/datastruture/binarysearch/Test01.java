package com.example.datastruture.binarysearch;

import com.example.demo4.TestUtil;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test01 {
	/**
	 * >> 的优先级 低于 +
	 */
	@Test
	public void test01() {
		int i = 8 >> 1 + 1; // 2
		System.out.println(i);
	}

	/**
	 * >> 的优先级 低于 +
	 */
	@Test
	public void test02() {
		//				 0  1  2  3  4  5  6  7  8  9
		int[] doubles = {1, 2, 4, 4, 5, 5, 8, 8, 9, 10};
		BinarySearch binarySearch = new BinarySearch();
		int
				value = -1;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 0;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 1;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 2;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 3;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 4;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 5;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 6;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 7;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 8;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 9;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 10;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));
		value = 11;
		System.out.println("value:" + value + "..." + binarySearch.bsearchInternally1(doubles, 0, doubles.length - 1, value));

	}


	@Test
	public void test03() {
		//				 0   1   2  3  4  5  6  7  8  9  10
		int[] doubles = {11, 10, 9, 8, 8, 5, 5, 4, 3, 2, 1};
		//int[] doubles = {1, 2, 4, 4, 5, 5, 8, 8, 9, 10, 11};
		//int[] doubles = {};

		List<Long> collect = Arrays.stream(doubles).mapToObj(Long::valueOf).collect(Collectors.toList());
		Map<Long, Double> collect1 = collect.stream().collect(Collectors.toMap(a -> a, a -> a * 1.0, (a, b) -> a));
		BinarySearch binarySearch = new BinarySearch();

		for (int i = -1; i < 12; i++) {
			System.out.println("value:" + i + "..." + binarySearch.bsearchInternally11(collect, collect1, i, Comparator.reverseOrder()));
			//System.out.println("value:" + i + "..." + binarySearch.bsearchInternally11(collect, collect1, i, Comparator.naturalOrder()));
		}
	}

	@Test
	public void test04() {
		ArrayList<Long> objects = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			objects.add((long) i);
		}

		//				 0   1   2  3  4  5  6  7  8  9  10
		int[] doubles = {11, 10, 9, 8, 8, 5, 5, 4, 3, 2, 1};
		//int[] doubles = {1, 2, 4, 4, 5, 5, 8, 8, 9, 10, 11};
		//int[] doubles = {};
		BinarySearch binarySearch = new BinarySearch();
		List<Long> collect = objects;//Arrays.stream(doubles).mapToObj(Long::valueOf).collect(Collectors.toList());
		Map<Long, Double> collect1 = collect.stream().collect(Collectors.toMap(a -> a, a -> a * 1.0, (a, b) -> a));

		int count = 10000;
		// ____321_215_000
		/*TestUtil.testTime(()-> {
			int i = binarySearch.bsearchInternally11(collect, collect1, 25, Comparator.reverseOrder());
			objects.add(i, (long)25);
		}, count,"");
*/
		//____32_476_800
/*
		TestUtil.testTime(()->{
			collect1.entrySet().stream().sorted(Comparator.comparing(e->e.getValue())).map(e->e.getKey()).collect(Collectors.toList());
		}, count,"");
*/

		// ____46_233_100
		// ____49_129_000
		// ____43_129_000
		ConcurrentHashMap<Long, Double> currMap = new ConcurrentHashMap<>(collect1);
	/*	TestUtil.testTime(()->{
			currMap.entrySet().stream().sorted(Comparator.comparing(e->e.getValue())).map(e->e.getKey()).collect(Collectors.toList());
		}, count,"");*/

		/*
		____35_696_000
		____33504600
		____34639600

		 */
		TestUtil.testTime(() -> {
			HashMap<Long, Double> longDoubleHashMap = new HashMap<>(currMap);
			longDoubleHashMap.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue())).map(e -> e.getKey()).collect(Collectors.toList());
		}, count, "");

	}
}
