package com.techm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCreateDemo {
	public static void main(String [] args) throws ClassNotFoundException {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "SYSTEM";
			String password = "suba152";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			try  {
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				Statement stmt = conn.createStatement(); {
					String sql = "CREATE TABLE SUBA152.Users_Data" +
					"(userID INTEGER not NULL, " +
					" userName VARCHAR(255) , " +
					" password VARCHAR(255) , " +
					" PRIMARY KEY (userName))";
					stmt.executeUpdate(sql);
					System.out.println("Created table in given database...");
					conn.close();
					} 
			}
			catch (SQLException ex) {
					ex.printStackTrace();
			}
	}
}
