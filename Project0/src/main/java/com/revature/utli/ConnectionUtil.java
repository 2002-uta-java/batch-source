package com.revature.utli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "username";
		String password = "password";

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
	} 
		return connection;
	}

}
