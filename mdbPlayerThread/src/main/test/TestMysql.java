import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestMysql {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		// 一开始必须填一个已经存在的数据库
		String dbName = "hello";
		String url = "jdbc:mysql://localhost:3306/?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		Connection conn = DriverManager.getConnection(url, "root", "123456");
		PreparedStatement stat = conn.prepareStatement("show databases like ?");
		stat.setString(1, dbName);
		ResultSet rs = stat.executeQuery();
		if(!rs.next()) {
			conn.createStatement().execute("create database " + dbName);
			System.err.println("database " + dbName +" not exsits, created successfully");
		}else {
			System.err.println("database " + dbName +" exsits");
		}
//		if(rs.next()) {
//			System.err.println(rs.getString(1));
//		}

		// 打开创建的数据库
		stat.close();
		conn.close();
	}
}