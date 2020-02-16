package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("JDBC_DB_URL");
		String username = System.getenv("JDBC_DB_USER");
		String password = System.getenv("JDBC_DB_PASS");
		
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}
	
}
