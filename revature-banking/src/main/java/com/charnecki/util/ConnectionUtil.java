package com.charnecki.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		
		boolean isTest = Boolean.valueOf(System.getenv("DB_TEST"));
		if(isTest) 
			return getH2Connection();
		
		String url = System.getenv("PRJ0_DB_HOST");
		String username = System.getenv("PRJ0_DB_USER");
		String password = System.getenv("PRJ0_DB_PASS");
		
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
