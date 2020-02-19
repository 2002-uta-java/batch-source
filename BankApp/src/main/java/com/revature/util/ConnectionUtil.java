package com.revature.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection connection;
	
//	public static Connection getHardCodedConnection() throws SQLException {
//		
//		/*
//		 * for more information on formatting the connection url or connecting to your db w JDBC
//		 * https://jdbc.postgresql.org/documentation/80/connect.html
//		 */
//		
//		String url = "jdbc:postgresql://[url]:5432/postgres";
//		String username = "username";
//		String password = "password";
//		
//		if(connection == null || connection.isClosed()) {
//			connection = DriverManager.getConnection(url, username, password);
//		}
//		
//		return connection;
//		
//	}

		/* Setting up environment variables:
		 * --- (Option 1) In your system's environment variables ---
		 * 
		 * --- (Option 2) In STS ---
		 * 1. Right click on the project folder in the "Package Explorer"
		 * 		go to "Run As" and "Run Configurations"
		 * 2. Click on the "Environment" tab and configure your name value pairs
		 * 
		 * 
		 * Using System.getenv("key") returns the value of the environment 
		 * 	variable, whether set in the system or in STS
		 */
	
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("JDBC_DB_HOST");
		String username = System.getenv("JDBC_DB_USER");
		String password = System.getenv("JDBC_DB_PASS");
		
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}
	
}