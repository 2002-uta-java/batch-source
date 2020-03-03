package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String url = System.getenv("JDBC_DB_HOST");
		String username = System.getenv("JDBC_DB_USER");
		String password = System.getenv("JDBC_DB_PASS");
		
		if(connection == null || connection.isClosed()) {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}
	
}
