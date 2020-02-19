package com.dean.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection connection;


	public static Connection getConnection() throws SQLException {
		
		String url = System.getenv("host");
		String username = System.getenv("username");
		String password = System.getenv("password");
	
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}

}
