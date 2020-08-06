package com.example.java8.javaRegex;

import org.junit.Test;
import org.redisson.RedissonDeque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/6 20:12
 * @Description:
 */
public class RegexTest {

	@Test
	public void testGroup() {
		String str = "ab c<icon>def</icon>" +System.lineSeparator()+
				"   deftfh<icon>a  s</icon>b   		<icon>c</icon>d<icon>e</icon>f<icon>g</icon>";
		//System.out.println(str);
		//Pattern p = Pattern.compile("(\\w*)<icon>(\\w*)</icon>(\\w*)");
		Pattern p = Pattern.compile("<trn-b2e0077-rs>(\\s|\\n)*(?<st><status>(.|\\s|\n)*</status>)(\\s|\n)*</trn-b2e0077-rs>");
		Matcher m = p.matcher(str);
		// find 查找多个符合正则的语句
		/*
		第一次查找 abc<icon>def</icon>deftfh
		第二次查找 <icon>a</icon>b
 		 */

		System.out.println(str);
		System.out.println("---------------------");
		while (m.find()) {
			// abc<icon>def</icon>deftfh
			// 语句里面有多个组 (group1)xxx(group2)xxxx(group3)  ()里面的就是组
			for (int i = 0; i <= m.groupCount(); i++) {
				System.out.println(m.group(i));
			}
			System.out.println("---------------------");

		}
	}

	@Test
	public void testMatcherMethod01() {
		String regex = "\\bcat\\b";
		String input = "cat cat cat cattie cat";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		int count = 1;
		while (matcher.find()) {
			System.out.println("Match number" + count);
			System.out.println("start() " + matcher.start());
			System.out.println("end() " + matcher.end());
			count++;
		}
	}

	@Test
	public void testMatcherMethod02() {
		String REGEX = "dog";
		String INPUT = "The dog says meow. All dogs say meow.";
		String REPLACE = "cat";

		Pattern p = Pattern.compile(REGEX);
		// get a matcher object
		Matcher m = p.matcher(INPUT);
		INPUT = m.replaceAll(REPLACE);
		System.out.println(INPUT);
	}

	@Test
	public void testMatcherMethod03() {
		String REGEX = "dog";
		String INPUT = "The dog says meow. All dogs say meow.";
		String REPLACE = "cat";

		Pattern p = Pattern.compile(REGEX);
		// get a matcher object
		Matcher m = p.matcher(INPUT);
		String s = m.replaceFirst(REPLACE);
		System.out.println(s);
		Matcher m1 = p.matcher(s);
		String s1 = m1.replaceFirst(REPLACE);
		System.out.println(s1);
	}

	@Test
	public void testMatcherMethod04() {
		Deque<String> stringDeque = new ArrayDeque<>();

		String REGEX = "<1>([\\s\\S]*)</1>";
		String INPUT = "<1>The</1> dog says <1>meow</1> All dogs say meow.";
		String REPLACE = "cat";

		Pattern p = Pattern.compile(REGEX);
		// get a matcher object
		Matcher m = p.matcher(INPUT);
		String s = m.replaceFirst(REPLACE);
		System.out.println(s);
		Matcher m1 = p.matcher(s);
		String s1 = m1.replaceFirst(REPLACE);
		System.out.println(s1);
	}

}
