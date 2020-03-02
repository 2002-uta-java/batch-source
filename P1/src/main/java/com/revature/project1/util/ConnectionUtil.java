package com.revature.project1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class ConnectionUtil {
	
	private ConnectionUtil() {
		super();
	}

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName(Driver.class.getName());
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		
		String url = "jdbc:postgresql://java-2002.cdd1owh7oofi.us-east-2.rds.amazonaws.com:5432/postgres"; 
		String username = System.getenv("JDBC_USER");
		String password = System.getenv("JDBC_PASSWORD");
		
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);		
		}

		return connection;
		
	}
	
	
}
