package com.youxi.create;

import com.example.demo1.util.FileUtil;
import org.junit.Test;


import java.io.*;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/5/16 10:37
 * @Description:
 */
public class CostTypeNumCreate {

	@Test
	public void test01() throws Exception {
		File file = new File("GainType.java");
		File newFile = new File("GainType1.java");
		if(!newFile.exists()) {
			newFile.createNewFile();
		}
		System.out.println(file.getAbsolutePath());
/*
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
*/

		StringBuffer sb = new StringBuffer();

		String old = FileUtil.readFile(file);
		int index = 1;

		String regex = "=(.*?);";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(old);

		while (matcher.find()) {
			matcher.appendReplacement(sb, "= " +String.valueOf(index++)+";");
		}

		matcher.appendTail(sb);
		System.out.println(sb);

		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
		bufferedWriter.write(sb.toString());
		bufferedWriter.close();
	}



}
