package com.techm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcInsertDemo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the user id : ");
		int userId_user = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the username : ");
		String username_user = scan.nextLine().trim();
		System.out.println("Enter the password : ");
		String password_user = scan.nextLine().trim();
		
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
			String sql = "INSERT INTO SUBA152.Users_Data (userId,userName,password) VALUES (?, ?, ?)";
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, userId_user);
			prep.setString(2, username_user);
			prep.setString(3, password_user);
			int rowcount = prep.executeUpdate();
			if(rowcount > 0) 
				System.out.println("User added successfully.");
			else
				System.out.println("User not added successfully.");
			conn.close();
			scan.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
