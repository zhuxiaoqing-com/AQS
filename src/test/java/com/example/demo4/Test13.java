package com.example.demo4;

import com.youxi.util.RandomUtil;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test13 {
	@Test
	public void test01() {
		int whileMax = 127;
		int count = 20000_000;

		TestUtil.testTime(() -> {
			byte a = (byte) whileMax;
			for (byte i = 0; i < a; i++) {
				long l = i;
			}
		}, count, "byte");


		TestUtil.testTime(() -> {
			short a = (short) whileMax;
			for (short i = 0; i < a; i++) {
				long l = i;
			}
		}, count, "short");

		TestUtil.testTime(() -> {
			for (int i = 0; i < whileMax; i++) {
			}
		}, count, "int");

		TestUtil.testTime(() -> {
			long a = (long) whileMax;
			for (long i = 0; i < a; i++) {
			}
		}, count, "long");

	}

	@Test
	public void test02() {
		long whileMax = 2000000000;
		int count = 20;

		TestUtil.testTime(() -> {
			int a = (int) whileMax;
			for (int i = 0; i < a; i++) {
			}
		}, count, "int");


		TestUtil.testTime(() -> {
			for (int i = 0; i < whileMax; i++) {
			}
		}, count, "int");

		TestUtil.testTime(() -> {
			long aa = whileMax + count;
			for (int i = count; i < whileMax; i++) {
			}
		}, count, "long");

	}

	@Test
	public void test03() {
		long whileMax = 2000000000;
		int count = 20;

		TestUtil.testTime(() -> {
			int a = (int) whileMax;
			int sum = 0;
			for (int i = 0; i < a; i++) {
				sum++;
			}
		}, count, "int");

		TestUtil.testTime(() -> {
			int a = (int) whileMax;
			long sum = 0;
			for (int i = 0; i < a; i++) {
				sum++;
			}
		}, count, "int");
	}


	@Test
	public void test04() {
		long whileMax = 2000000000;
		int count = 2000;

		TestUtil.testTime(() -> {
			int a = (int) whileMax;
			int sum = 0;
			for (int i = 0; i < a; i++) {
				sum++;
			}
		}, count, "int");

		TestUtil.testTime(() -> {
			int a = (int) whileMax;
			long sum = 0;
			for (int i = 0; i < a; i++) {
				sum = sum + 1L;
				/*if (sum++ < i) {

				}*/
			}
		}, count, "long");

		TestUtil.testTime(() -> {
			int a = (int) whileMax;
			float sum = 0;
			for (int i = 0; i < a; i++) {
				sum++;
				/*if (sum++ < i) {

				}*/
			}
		}, count, "float");

		TestUtil.testTime(() -> {
			int a = (int) whileMax;
			double sum = 0;
			for (int i = 0; i < a; i++) {
				sum++;
				/*if (sum++ < i) {

				}*/
			}
		}, count, "double");
	}
}


