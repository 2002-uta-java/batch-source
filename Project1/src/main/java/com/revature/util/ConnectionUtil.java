package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		boolean isTest = Boolean.valueOf(System.getenv("DB_TEST"));
		if(isTest) 
			return getH2Connection();
		
		String url = System.getenv("PRJ1_DB_HOST");
		String username = System.getenv("PRJ1_DB_USER");
		String password = System.getenv("PRJ1_DB_PASS");
		
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;	
	}
	
	public static Connection getH2Connection() {

		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:h2:~/test");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
	
}
