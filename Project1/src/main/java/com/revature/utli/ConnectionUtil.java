package com.revature.utli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {

	private static Connection connection;
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("JDBC_DB_HOST");
		String username = System.getenv("JDBC_DB_USER");
		String password = System.getenv("JDBC_DB_PASS");

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
	} 
		return connection;
	}

}

