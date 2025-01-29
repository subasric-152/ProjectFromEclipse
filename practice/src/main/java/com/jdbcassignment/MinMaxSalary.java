package com.jdbcassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MinMaxSalary {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dusername = "SYSTEM";
		String dpassword = "suba152";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Can not register the driver class.");
		}
		
		try(Connection conn = DriverManager.getConnection(url, dusername, dpassword)){
			String sql = "SELECT JOB, (MAX(SAL)- MIN(SAL)) AS FINAL_SALARY FROM SUBA152.EMP GROUP BY JOB HAVING MAX(SAL) BETWEEN 1000 AND 2000 ";
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			 System.out.println("Job Title        Salary Difference");
	            System.out.println("----------------------------------");

	            while (rs.next()) {
	                String job = rs.getString("JOB");
	                double salaryDifference = rs.getDouble("FINAL_SALARY");
	                System.out.printf("%-15s %.2f%n", job, salaryDifference);
	            }
	            prep.close();
	            rs.close();
			
		}
		catch(SQLException e) {
			System.out.println("Can not get details.");
		}

	}

}
