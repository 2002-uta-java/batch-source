package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {

	private static Connection connection;
	private static final Logger log = Logger.getRootLogger();
	private ConnectionUtil() {}
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			log.warn(e);
		}
		
		boolean isTest = Boolean.parseBoolean(System.getenv("DB_TEST"));
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
				connection = DriverManager.getConnection(System.getenv("PRJ1_H2_HOST"));
			}
		} catch (SQLException e) {
			log.warn(e);
		}

		return connection;
	}
	
}
