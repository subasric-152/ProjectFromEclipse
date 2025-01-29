package com.techm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcDeleteDemo {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the user id to delete : ");
		int userid_users = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the user name to delete : ");
		String username_users = scan.nextLine();
		
		String durl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dusername = "SYSTEM";
		String dpassword = "suba152";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Can not register the driver class.");
		}
		
		try(Connection conn = DriverManager.getConnection(durl, dusername, dpassword)){
			String sql = "DELETE FROM SUBA152.USERS_DATA WHERE userid = ? AND username =?";
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, userid_users);
			prep.setString(2, username_users);
			scan.close();
			int rowcount = prep.executeUpdate();
			if(rowcount > 0)
				System.out.println("User deleted successfully.");
			else
				System.out.println("User not found.");
		}
				

	}

}
