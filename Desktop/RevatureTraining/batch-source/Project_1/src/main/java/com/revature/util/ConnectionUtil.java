package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection connection;
	public static Connection getConnection() throws SQLException {

	String url = "jdbc:postgresql://project-1.cquhd9ue7z5a.us-east-2.rds.amazonaws.com:5432/postgres";
	String username = "*********";
	String password = "*********";

	if (connection == null || connection.isClosed()) {
		connection = DriverManager.getConnection(url, username, password);
	}

	return connection;
	
	}
	
//	public static Connection getConnection() throws SQLException {
//		
//		boolean isTest = Boolean.valueOf(System.getenv("DB_TEST"));
//		if(isTest) {
//			return getH2Connection();
//		} else {
//			// if the DB_TEST variable resolves to "false" or is not present
//			String url = System.getenv("jdbc:postgresql://project-0.cquhd9ue7z5a.us-east-2.rds.amazonaws.com:5432/postgres");
//			String username = System.getenv("postgres");
//			String password = System.getenv("glr031980");
//	
//			if (connection == null || connection.isClosed()) {
//				connection = DriverManager.getConnection(url, username, password);
//			}
//	
//			return connection;
//		}
//	}
	
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