package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		boolean isTest = Boolean.valueOf(System.getenv("DB_TEST"));
		if(isTest) {
			return getH2Connection();
		}
		else {
			String url = System.getenv("JDBC_DB_HOST");
			String username = System.getenv("JDBC_DB_USER");
			String password = System.getenv("JDBC_DB_PASS");

			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(url, username, password);
			}

			return connection;
		}
	}	

	public static Connection getH2Connection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:h2:~/test");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
