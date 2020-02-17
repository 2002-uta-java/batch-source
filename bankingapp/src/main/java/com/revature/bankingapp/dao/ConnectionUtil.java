package com.revature.bankingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection c;
	
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("JDBC_HOST");
		String username = System.getenv("JDBC_USER");
		String password = System.getenv("JDBC_PASS");
		
		if (c == null || c.isClosed()) {
			c = DriverManager.getConnection(url, username, password);
		}
		
		return c;
	}
}
