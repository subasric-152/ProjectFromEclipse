package com.techm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcAlterDemo {

	public static void main(String[] args) throws SQLException {
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
		
		try(Connection conn = DriverManager.getConnection(url, dusername, upassword)){
			Statement smt = conn.createStatement();
			String alterSql = "ALTER TABLE SUBA152.Users_Data ADD PRIMARY KEY(userid)";
			boolean result =  smt.execute(alterSql);
			if(result)
				System.out.println("primary key constraint added.");
			else
				System.out.println("not added.");
		}

	}

}
