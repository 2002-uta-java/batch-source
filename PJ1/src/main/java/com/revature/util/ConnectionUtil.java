package com.revature.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection connection;
	/**
	 * getting our connection its hardcoded for right now
	 * @return connection obj
	 * @throws SQLException
	 */
	public static Connection getHardConnection() throws SQLException{
		String url = "jdbc:postgresql://project0.cfv7w32wqvb8.us-east-2.rds.amazonaws.com:5432/postgres";
		String username = "project0";
		String password = "p4ssw0rd";
		if (connection == null || connection.isClosed()) {
//			registering our jdbc driver
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
			}
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}

	
}
