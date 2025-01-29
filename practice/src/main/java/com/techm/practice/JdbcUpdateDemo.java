package com.techm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcUpdateDemo {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the user Id : ");
		int userid_user = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the username : ");
		String username_user = scan.nextLine();
		System.out.println("Enter the new password to change : ");
		String password_user = scan.nextLine();
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dusername = "SYSTEM";
		String upassword = "suba152";
		
		// step 1 : registering the driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Can not register the driver class.");
		}
		
		// step 2 : Creating connection
		try {
			Connection conn = DriverManager.getConnection(url, dusername, upassword);
			String updateSql = "UPDATE SUBA152.Users_Data SET password=? WHERE username=? AND userid =?";
			
			// step 3 : Creating the statement
			
		    try (PreparedStatement prep = conn.prepareStatement(updateSql)) {
		        prep.setString(1, password_user);
		        prep.setString(2, username_user);
		        prep.setInt(3, userid_user);

		        int rowCount = prep.executeUpdate();
		        if (rowCount > 0) {
		            System.out.println("User password updated successfully.");
		        } 
		        else {
		            System.out.println("User not found.");
		        }
		        scan.close();
		    }
		    
		    // step 5 : closing the connection
		    
		    conn.close();
		} 
		catch (SQLException e) {
		    System.out.println("Cannot update the password due to an error.");
		} 
	}

}
