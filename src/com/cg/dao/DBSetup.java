package com.cg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {
	private static String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private static String username ="aman";
	private static String pass ="oracle";
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(url,username,pass);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found!");
			return null;
		} catch (SQLException e) {
			return null;
		}
		
	}
}
