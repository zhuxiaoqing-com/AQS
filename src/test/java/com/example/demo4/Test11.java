package com.example.demo4;

import com.example.demo1.util.GeomUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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


}















