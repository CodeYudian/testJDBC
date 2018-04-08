package com.codeYu;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJDBC {

	// �Լ����ݿ��URL,�û�,����
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	static final String USER = "root";
	static final String PASS = "yudian123";

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Map<String, String>> IMap = new ArrayList<Map<String, String>>();
		try {
			// 1.��������
			Class.forName("com.mysql.jdbc.Driver");

			// 2.�������ݿ�
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 3.ִ��sql���
			System.out.println("Creating statement...");

			// ��ʹ��Statement����ִ��SQL���֮ǰ����Ҫʹ��Connection�����createStatement()��������һ��Statement����
			stmt = conn.createStatement();

			String sql = "SELECT id, name,gender FROM employees";
			rs = stmt.executeQuery(sql);

			// ���ؽ����
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
			// ��ʾ���
			for (int i = 0; i < IMap.size(); i++) {
				System.out.println(IMap.get(i));
			}
			for (Map<String, String> map : IMap) {
					System.out.println(map.get("name"));
			}

		} catch (SQLException se) {
			// ����JDBC�Ĵ���
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���������
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
