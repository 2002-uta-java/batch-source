package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection connection;

	public static Connection getHardCodedConnection() throws SQLException {
		/*
		 * Fore more information on formatting the connection url or connecting to your
		 * db with JDBC:
		 * 
		 * http://jdbc.postgresql.org/documentation/80/connect.html
		 */
		final String url = "jdbc:postgresql://java-2002.cfhvkame7ojc.us-east-2.rds.amazonaws.com:5432/postgres";
		final String username = "postgres";
		final String password = "password";

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}

	public static Connection getConnection() throws SQLException {
		final String url = System.getenv("JDBC_DB_HOST");
		final String username = System.getenv("JDBC_DB_USER");
		final String password = System.getenv("JDBC_DB_PASSWORD");

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}
}
