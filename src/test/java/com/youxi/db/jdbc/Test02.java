package com.youxi.db.jdbc;

import com.example.demo1.util.FileUtil;
import com.youxi.db.jdbc.entity.Field;
import com.youxi.db.jdbc.entity.TableBean;
import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test02 {

	static Connection conn;
	static PreparedStatement preStmt = null;
	static ResultSet rt = null;

	static String driverName = "com.mysql.jdbc.Driver";
	//	static String url = "jdbc:mysql://localhost:3306/test1?rewriteBatchedStatements=true";
	static String url = "jdbc:mysql://localhost:3306/test01?useSSL=false";

	static String user = "root";
	static String password = "123456";

	static String filePath = "G:\\mh\\game_server-develop\\AQS\\src\\test\\java\\com\\youxi\\a.txt";

/*	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://10.0.0.101:3306/SECONDWAR_401?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;rewriteBatchedStatements=true&amp;user=root&amp;password=123456";
	static String user = "root";
	static String password = "123456";*/


	static {
		try {
			Class.forName(driverName);

			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void test01() throws Exception {
		List<TableBean> tableBeans = parseTableBean();
		//System.out.println(tableBeans);

		for (TableBean tableBean : tableBeans) {
			PreparedStatement preparedStatement = conn.prepareStatement(tableBean.creatTableSQL());
			try {
			preparedStatement.execute();

			}catch (Exception e) {
				System.out.println(tableBean.creatTableSQL());
				throw e;
			}
		}
	}



	/**
	 * 1、读取txt加载表名 字段名 字段类型;
	 * INSERT INTO `aaa` (`a`,`b`,`c`,`d`)
	 * string;int;int;int;
	 */
	private List<TableBean> parseTableBean() throws Exception {
		File file = new File(filePath);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String s1 = null;
		ArrayList<TableBean> list = new ArrayList<>();
		while (true) {
			System.out.println("------------------------");
			String str1 = bufferedReader.readLine();
			String str2 = bufferedReader.readLine();
			System.out.println(str1);
			System.out.println(str2);
			if (str1 == null || str1.trim().isEmpty()) {
				break;
			}
			List<String> tableList = parseTableLine(str1);
			System.out.println(tableList);
			List<String> fieldList = parseField(str2);
			System.out.println(fieldList);
			TableBean tableBean = createTableBean(tableList, fieldList);
			list.add(tableBean);
			System.out.println(tableBean);
		}
		return list;
	}

	private TableBean createTableBean(List<String> tableList, List<String> fieldList) {
		TableBean tableBean = new TableBean();
		tableBean.setTableName(tableList.get(0));

		List<Field> collect = tableList.stream().skip(1)
				.map(Field::new)
				.collect(Collectors.toList());

		for (int i = 0; i < collect.size(); i++) {
			String s = fieldList.get(i >= fieldList.size() ? fieldList.size() - 1 : i);
			collect.get(i).setType(s);
		}
		tableBean.setFieldList(collect);
		return tableBean;
	}

	private List<String>  parseTableLine(String lineStr) {
		if (lineStr == null || !lineStr.trim().startsWith("INSERT")) {
			return null;
		}

		Pattern p = Pattern.compile("(`[\\w]*?`)");
		Matcher m = p.matcher(lineStr);
		// find 查找多个符合正则的语句
		/*
		第一次查找 abc<icon>def</icon>deftfh
		第二次查找 <icon>a</icon>b
 		 */

		ArrayList<String> deque = new ArrayList<>();

		while (m.find()) {
			String group = m.group(1);
			deque.add(group.substring(1,group.length()-1));
		}

		return deque;
	}


	private List<String> parseField(String lineStr) {
		if (lineStr == null || lineStr.trim().startsWith("INSERT")) {
			return null;
		}
		return Arrays.stream(lineStr.split(";"))
				.filter(a -> !a.trim().isEmpty())
				.collect(Collectors.toList());
	}

	@Test
	public void test03() throws SQLException {
		//String a = "INSERT INTO `aaa` (`a`,`b`,`c`,`d`)";
		String a = "INSERT INTO `jilu1` (`daili`,`tinghao`,`jitaihao`,`NOTEAin`,`NOTEAout`,`NOTEAbalance`,`NOTEBin`,`NOTEBout`,`NOTEBbalance`,`_5k80`,`rs80`,`sf80`,`_4k80`)\n";
		ArrayList<String> list = new ArrayList<>();

		boolean collecting = false;
		int index = 0;
		char splitChar = '`';
		char[] chars = a.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (splitChar != c) {
				continue;
			}
			if (!collecting) {
				collecting = true;
				index = i;
				continue;
			}
			// 到了终点开始收集字节
			if (collecting) {
				collecting = false;
				int subIndex = 0;
				char[] strChar = new char[i - index - 1];
				for (int j = index + 1; j < i; j++) {
					strChar[subIndex++] = chars[j];
				}
				list.add(new String(strChar));
			}
		}

		System.out.println(list);

		testaa(a);
	}

	public void testaa(String str) {
		Pattern p = Pattern.compile("(`[\\w]*?`)");
		Matcher m = p.matcher(str);
		// find 查找多个符合正则的语句
		/*
		第一次查找 abc<icon>def</icon>deftfh
		第二次查找 <icon>a</icon>b
 		 */

		Deque<String> deque = new ArrayDeque<>();


		while (m.find()) {
			String group = m.group(1);
			deque.add(group.substring(1,group.length()-1));
		}

		System.out.println(deque);
	}
}

