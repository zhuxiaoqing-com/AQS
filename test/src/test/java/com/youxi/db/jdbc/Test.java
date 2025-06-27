package com.youxi.db.jdbc;


import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Test {
	static Connection conn;
	static PreparedStatement preStmt = null;
	static ResultSet rt = null;

	static String driverName = "com.mysql.jdbc.Driver";
	//	static String url = "jdbc:mysql://localhost:3306/test1?rewriteBatchedStatements=true";
	static String url = "jdbc:mysql://localhost:3306/test01?useSSL=false";

	static String user = "root";
	static String password = "123456";

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

	@org.junit.Test
	public void test02() throws SQLException {
		List<String> allTableName = getAllTableName(conn);
		for (String s : allTableName) {
			selectTable(s);
		}
	}

	public static List<String> getAllTableName(Connection cnn) throws SQLException {
		List<String> tables = new ArrayList<>();
		DatabaseMetaData dbMetaData = cnn.getMetaData();

		String[] types = {"TABLE"};

		ResultSet tabs = dbMetaData.getTables(null, null, null, types/*只要表就好了*/);

		while (tabs.next()) {
			tables.add((String) tabs.getObject("TABLE_NAME"));
		}
		System.out.println(tables);
		return tables;
	}

	private String selectTable(String tableName) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " LIMIT 100;";
		Statement statement = conn.createStatement();

		ArrayList<String> vList = new ArrayList<>();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			String string = resultSet.getString(2);
			vList.add(string);
		}

		String selectV = null;
		List<String> collect = vList.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
		if (collect.size() == 1) {
			selectV = collect.get(0);
		}

		if (collect.size() > 1) {
			selectV = collect.get(collect.size() / 2);
		}

		return selectV;
	}

	@org.junit.Test
	public void test03() throws SQLException {
		//PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO a VALUES (1,2,3,4)");
		PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO a(a,c,d) VALUES ('1','3','4')");
		preparedStatement.executeUpdate();
	/*	PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM a");
		ResultSet resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		while (resultSet.next()) {
			int index = 1;
			System.out.println(metaData.getColumnType(index));
			System.out.println(resultSet.getInt(index++));

			System.out.println(metaData.getColumnType(index));
			System.out.println(resultSet.getInt(index++));

			System.out.println(metaData.getColumnType(index));
			System.out.println(resultSet.getInt(index++));

			System.out.println(metaData.getColumnType(index));
			System.out.println(resultSet.getInt(index++));*/
	}
}

