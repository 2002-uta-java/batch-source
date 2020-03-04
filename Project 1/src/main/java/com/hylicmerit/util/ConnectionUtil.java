package com.hylicmerit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = System.getenv("HOST");
		String username = System.getenv("USER");
		String password = System.getenv("PASS");
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
}
