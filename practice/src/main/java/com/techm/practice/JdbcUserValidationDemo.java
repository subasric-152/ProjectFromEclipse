package com.techm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcUserValidationDemo {

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
			Statement smt = conn.createStatement();
			String selectSql = "SELECT username FROM SUBA152.Users_Data";
			ResultSet datas = smt.executeQuery(selectSql);
			boolean flag = false;
			int i = 0;
			while(!flag) {
					while(datas.next()) {
						String users_name = datas.getString("username");
						if(users_name.equals(username_user)) {
							System.out.println("User already registered.");
							i = 1;
							break;
						}
					}
					if(i == 0) {
						String sql = "INSERT INTO SUBA152.Users_Data (userId,userName,password) VALUES (?, ?, ?)";
						PreparedStatement prep = conn.prepareStatement(sql);
						prep.setInt(1, userId_user);
						prep.setString(2, username_user);
						prep.setString(3, password_user);
						int rowcount = prep.executeUpdate();
						if(rowcount > 0) 
							System.out.println("User added successfully.");
					}
					flag = true;
				
			}
			conn.close();
			scan.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
