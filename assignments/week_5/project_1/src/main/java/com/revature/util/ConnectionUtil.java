package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException{
		String url = System.getenv("DB_HOST_URL");
		String userName = System.getenv("USER_NAME");
		String password = System.getenv("USER_PASSWORD");
		
		if (connection == null || connection.isClosed()) {
			
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(url, userName, password);
		}
		
		return connection;
	}

}
