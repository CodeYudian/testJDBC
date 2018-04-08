package com.codeYu;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJDBC {

	// 自己数据库的URL,用户,密码
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	static final String USER = "root";
	static final String PASS = "yudian123";

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Map<String, String>> IMap = new ArrayList<Map<String, String>>();
		try {
			// 1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2.连接数据库
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 3.执行sql语句
			System.out.println("Creating statement...");

			// 在使用Statement对象执行SQL语句之前，需要使用Connection对象的createStatement()方法创建一个Statement对象
			stmt = conn.createStatement();

			String sql = "SELECT id, name,gender FROM employees";
			rs = stmt.executeQuery(sql);

			// 返回结果集
			while (rs.next()) {
				// Retrieve by column name
				Map<String, String> map = new HashMap<String, String>();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				map.put("id", Integer.toString(id));
				map.put("name", name);
				map.put("gender", gender);
				IMap.add(map);

			}
			// 显示结果
			for (int i = 0; i < IMap.size(); i++) {
				System.out.println(IMap.get(i));
			}
			for (Map<String, String> map : IMap) {
					System.out.println(map.get("name"));
			}

		} catch (SQLException se) {
			// 处理JDBC的错误
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭所有连接
			try {
				conn.close();
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}// end main
}
