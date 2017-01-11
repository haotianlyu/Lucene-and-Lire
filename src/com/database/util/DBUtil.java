package com.database.util;
//数据库接口类，包括数据库开放和关闭
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public static Connection getConn(){
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1:3306/project?characterEncoding=utf-8";
			String username="root";
			String password="3721";
			conn=DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConn(ResultSet rs,Statement stat,Connection conn){
		try {
			// 鍏抽棴 ctrl+shift+F format
			if (rs != null) {
				rs.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConn(Statement stat,Connection conn){
		try {
			
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
