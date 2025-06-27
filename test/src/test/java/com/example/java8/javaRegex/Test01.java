package com.example.java8.javaRegex;

import org.junit.Test;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/5/10 15:55
 * @Description:
 */
public class Test01 {

	String modao_ideaJava = "modao_ideaJava";
	String modao_java = "modao_java";

	@Test
	public void test01() throws IOException {


		String ideaPath = "F:\\mh\\game_server-develop\\modao_ideaJava\\BOOT-INF\\classes";
		//	String other = "F:\\mh\\game_server-develop\\modao_java\\BOOT-INF\\classes";

		whileDir(new File(ideaPath));

	}

	public void whileDir(File dir) throws IOException {
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				whileDir(file);
			} else {
				if (file.getName().endsWith(".java")) {
					System.out.println("process fileName:" + file.getName());
					String oldPath = file.getAbsolutePath().replace(modao_ideaJava, modao_java);
					allReplace(file, new File(oldPath));
				}
			}
		}
	}


	@Test
	public void testMethod07() throws IOException {
		allReplace("F:\\mh\\game_server-develop\\AQS\\src\\test\\java\\com\\example\\java8\\javaRegex\\ExperienceTreasure.java", "F:\\mh\\game_server-develop\\AQS\\src\\test\\java\\com\\example\\java8\\javaRegex\\ExperienceTreasure2.java");

	}

	public void allReplace(String oldPath, String newPath) throws IOException {
		allReplace(new File(oldPath), new File(newPath));
	}

	public void allReplace(File oldFile, File newFile) throws IOException {
		String old1 = readFile(oldFile);
		String new1 = readFile(newFile);
		old1 = quoteReplacement1(old1);
		new1 = quoteReplacement1(new1);

		String start = "hashCode\\(\\)";
		String end = "return result;";
		String regex = "(" + start + "[\\s\\S]*?" + end + ")";
		String replace = regexReplace(old1, new1, regex);

		try (FileWriter fileWriter = new FileWriter(newFile)) {
			fileWriter.write(quoteReplacement2(replace));
			fileWriter.flush();
		} catch (IOException e) {
			System.exit(0);
		}
	}

	static String special = "special11111";

	public static String quoteReplacement1(String s) {
		if ((s.indexOf('\\') == -1) && (s.indexOf('$') == -1))
			return s;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '$') {
				sb.append(special);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String quoteReplacement2(String s) {
		return s.replace(special, "$");
	}


	private static String regexReplace(String extractContent, String replaceContent, String regex) {
		//Pattern p = Pattern.compile("(" + GlobalConstants.DO_NOT_EDIT_THIS_START + "[\\s\\S]*?" + GlobalConstants.DO_NOT_EDIT_THIS_END + ")");
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(extractContent);
		Deque<String> deque = new ArrayDeque<>();
		while (m.find()) {
			deque.add(m.group(1));
		}

		Matcher matcher = p.matcher(replaceContent);
		StringBuffer sb = new StringBuffer();
		while (matcher.find() && !deque.isEmpty()) {
			matcher.appendReplacement(sb, deque.remove());
		}
		matcher.appendTail(sb);

		return sb.toString();
	}


	private static String readFile(File file) {
		BufferedReader reader = null;
		try {
			StringBuilder builder = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				builder.append(tempString);
				builder.append(System.lineSeparator());
			}
			// 去除最后的分隔符
			if (builder.length() > 0) {
				builder.delete(builder.length() - System.lineSeparator().length(), builder.length());
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}
}
