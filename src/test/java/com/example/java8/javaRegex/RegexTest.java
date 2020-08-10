package com.example.java8.javaRegex;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
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
		String str = "ab c<icon>def</icon>" + System.lineSeparator() +
				"   deftfh<icon>a  s</icon>b<icon>c</icon>";

		//System.out.println(str);
		//Pattern p = Pattern.compile("(\\w*)<icon>(\\w*)</icon>(\\w*)");
		//Pattern p = Pattern.compile("<trn-b2e0077-rs>(\\s|\\n)*(?<st><status>(.|\\s|\n)*</status>)(\\s|\n)*</trn-b2e0077-rs>");
		Pattern p = Pattern.compile("<icon>([\\s\\S]*?)</icon>");
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
		String REGEX = "<1>([\\s\\S]*?)</1>";
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


	@Test
	public void testGroup05() {
		String str = "ab c<icon>def</icon>" + System.lineSeparator() +
				"   deftfh<icon>a  s</icon>b<icon>c</icon>";
		String s = "";
		System.out.println(s);
	}


	@Test
	public void testMethod06() {
		String REGEX = "a*b";
		String INPUT = "aabfooaabfooabfoobkkk";
		String REPLACE = "-";
		Pattern p = Pattern.compile(REGEX);
		// 获取 matcher 对象
		Matcher m = p.matcher(INPUT);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, REPLACE);
		}
		//m.appendTail(sb);
		System.out.println(sb.toString());
	}


	public static void main(String[] args) {

		// 内容
		String value = "fileNameCode-->_A    " + System.lineSeparator() + "D246752.4284s..d0..234.json";

		// 匹配规则 这里 * 后面的 ? 表示懒惰匹配 只要找到第一个匹配的就直接返回;
		// 没有 ? 就是贪婪匹配  要查找到最后一个
		/**
		 D246752.4284s..d0..234
		 就上面的 找 .. 如果使用 ? 则找到 4s.. 的..
		 如果不使用 ? 则找到 d0..
		 */
		//String reg = "_([\\s\\S]*?)\\.\\.";
		String reg = "_([\\s\\S]*)\\.\\.";
		Pattern pattern = Pattern.compile(reg, Pattern.MULTILINE);

		// 内容 与 匹配规则 的测试
		Matcher matcher = pattern.matcher(value);

		if (matcher.find()) {
			// 包含前后的两个字符
			System.out.println(matcher.group());
			// 不包含前后的两个字符
			System.out.println(matcher.group(1));
		} else {
			System.out.println(" 没有匹配到内容....");
		}
	}

	@Test
	public void testMethod07() {
		String str = "ab c<icon>def</icon>" + System.lineSeparator() +
				"   deftfh<icon>a  s</icon>b<icon>c</icon>xxxx";
		Pattern p = Pattern.compile("(<icon>[\\s\\S]*?</icon>)");
		Matcher m = p.matcher(str);
		// find 查找多个符合正则的语句
		/*
		第一次查找 abc<icon>def</icon>deftfh
		第二次查找 <icon>a</icon>b
 		 */

		Deque<String> deque = new ArrayDeque<>();
		System.out.println(str);

		while (m.find()) {
			deque.add(m.group(1));
		}

		System.out.println("---------------------");

		String str2 = "ab c<icon>自动</icon>" + System.lineSeparator() +
				"   deftfh<icon>填   充</icon>b<icon>的测试" + System.lineSeparator() + "字符串</icon>sdas";

		Matcher matcher = p.matcher(str2);
		StringBuffer sb = new StringBuffer();

		while(matcher.find()) {
			matcher.appendReplacement(sb, deque.remove());
		}
		// 将剩余的字符串全部添加进去
		matcher.appendTail(sb);
		System.out.println(sb.toString());
	}


}
