package com.revature.project1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		
		String url = System.getenv("JDBC_HOST");
		String username = System.getenv("JDBC_USER");
		String password = System.getenv("JDBC_PASSWORD");
		
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);			
		}
		
		return connection;
		
	}
	
	
}
