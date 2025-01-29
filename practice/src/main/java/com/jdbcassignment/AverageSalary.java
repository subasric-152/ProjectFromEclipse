package com.jdbcassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AverageSalary {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dusername = "SYSTEM";
		String dpassword = "suba152";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Can Not register the driver class");
		}
		
		try(Connection conn = DriverManager.getConnection(url, dusername, dpassword)){
			Statement smt = conn.createStatement();
			String sql = "SELECT JOB,AVG(SAL) AS AVERAGE_SALARY FROM SUBA152.EMP GROUP BY JOB";
			ResultSet rs = smt.executeQuery(sql);
			System.out.println("Jobs           Average_salary");
			System.out.println("-----------------------------");
			while(rs.next()) {
				String job = rs.getString("JOB");
                double averageSalary = rs.getDouble("AVERAGE_SALARY");
                System.out.printf("%-15s %.2f%n", job, averageSalary);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Can not get the details.");
		}

	}

}
