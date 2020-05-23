package com.example.demo4;

import com.example.demo1.util.GeomUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test11 {
	@Test
	public void test01() {
		long start = System.nanoTime();
		short s;
		for (int i = 1; i <= 100_000_000_0; i++) {
			s = 0x00;
		}
		long end = System.nanoTime();
		System.out.println((end - start) / 1000);
	}

	@Test
	public void test02() {
		int x = 3;
		int k = 1;
		System.out.println(x/(1<<k));

		System.out.println(x>>(k));
		System.out.println((x+(1<<k)-1)>>k);

		System.out.println();

		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.stream().map(member -> {
			if (member.startsWith("\"")) {
				member = member.substring(1, member.length() - 1);
			}
			return Integer.parseInt(member);
		}).collect(Collectors.toList());

	}

	@Test
	public void test03() {
		String s = "a231231s";
		System.out.println(s.substring(1,s.length()-1));
	}


}















