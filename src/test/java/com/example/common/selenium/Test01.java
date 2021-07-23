package com.example.common.selenium;

import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/7/20 17:38
 * @Description:
 */
public class Test01 {
	@Test
	public void test01() throws InterruptedException {
		SogouTranslation sogouTranslation = new SogouTranslation();
		File file = new File("F:\\mh\\a\\cpu开发者手册\\325462-sdm-vol-1-2abcd-3abcd");
		Collection<Integer> successFile = findSuccessFile();
		successFile.add(481);
		successFile.add(461);
		successFile.add(4361);
		List<String> collect = Arrays.stream(Objects.requireNonNull(file.listFiles()))
				.filter(f -> !successFile.contains(Integer.parseInt(f.getName().substring(0, f.getName().indexOf("-")))))
				.sorted(Comparator.comparing(f -> Integer.parseInt(f.getName().substring(0, f.getName().indexOf("-"))), Comparator.reverseOrder()))
				.map(File::getAbsolutePath)
				.collect(Collectors.toList());

		for (String s : collect) {
			System.out.println("翻译文件start name" + s + "  ...time:" + new Date());
			sogouTranslation.sogoufanyi(s);
			System.out.println("翻译文件end name" + s + "...time:" + new Date());
			System.out.println();
		}

		System.out.println("翻译全部完成！！！！！！！！！！！！！");
	}

	@Test
	public void test02() {
		File file = new File("F:\\mh\\a\\cpu开发者手册\\325462-sdm-vol-1-2abcd-3abcd");
		Collection<Integer> successFile = findSuccessFile();
		String collect = Arrays.stream(Objects.requireNonNull(file.listFiles()))
				.filter(f -> !successFile.contains(Integer.parseInt(f.getName().substring(0, f.getName().indexOf("-")))))
				.sorted(Comparator.comparing(f -> Integer.parseInt(f.getName().substring(0, f.getName().indexOf("-")))))
				.map(File::getAbsolutePath)
				.collect(Collectors.joining("\r\n"));

		System.out.println(file.listFiles().length - successFile.size());
		System.out.println(collect);
	}

	private Collection<Integer> findSuccessFile() {
		File file = new File("D:\\Documents\\Downloads");
		Set<Integer> list = new HashSet<>();
		for (File listFile : file.listFiles()) {
			String name = listFile.getName();
			int i = name.indexOf("-");
			if (i < 0) {
				continue;
			}
			int int1;
			try {
				String substring = name.substring(0, i);
				int1 = Integer.parseInt(substring);
			} catch (Exception e) {
				continue;
			}
			list.add(int1);
		}
		return list;
	}
}
