package com.techm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSelectDemo {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username =  "SYSTEM";
		String password = "suba152";
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Class not found can not register the driver class.");
		}
		
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			String sql = "SELECT * FROM SUBA152.Users_data ORDER BY username DESC";
			Statement smt = conn.createStatement();
			ResultSet datas = smt.executeQuery(sql);
			System.out.printf("%-10s %-15s %-15s %n %n", "UserId", "Username", "Password"); 
			while(datas.next()) {
				int users_id = datas.getInt("userid");
				String users_name = datas.getString("username");
				String users_password = datas.getString("password");
				System.out.printf("%-10d %-15s %-15s%n", users_id, users_name, users_password); 
			}
			conn.close();
			datas.close();
		}
		catch(SQLException e) {
			System.out.println("Can not get the user names.");
		}

	}

}
