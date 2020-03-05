package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class ConnectionUtil {
	static {
		try {
			Class.forName(Driver.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static Connection connection;

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
