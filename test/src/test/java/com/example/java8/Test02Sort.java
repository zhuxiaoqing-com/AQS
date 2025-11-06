package com.example.java8;

import com.example.demo4.TestUtil;
import com.example.java8.entity.Employee;
import com.google.common.collect.Lists;
import com.youxi.util.RandomUtil;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test02Sort {
	@Test
	public void test01() {
		ArrayList<Integer> list = new ArrayList<>();
		int index = 50;

		for (int i = index; i > 0; i--) {
			int random = RandomUtil.getRandom(1000);
			for (int i1 = 0; i1 < random; i1++) {
				list.add(i);
			}
		}

		ArrayList<Integer> list1 = new ArrayList<>(list);
		ArrayList<Integer> list2 = new ArrayList<>(list);

		int maxNum = 10000;
		TestUtil.testTime(() -> sort(index, list2), maxNum, "bb");
		TestUtil.testTime(() -> list1.stream().sorted(Comparator.comparingInt(b -> b)).collect(Collectors.toList()), maxNum, "aa");


	}

	private void sort(int index, ArrayList<Integer> list) {
		ArrayList[] objects = new ArrayList[index];
		for (Integer integer : list) {
			int i = integer - 1;
			if (objects[i] == null) {
				objects[i] = new ArrayList();
			}
			objects[i].add(integer);
		}
		list.clear();

		for (ArrayList<Integer> object : objects) {
			for (Integer integer : object) {
				list.add(integer);
			}
		}
	}
}










