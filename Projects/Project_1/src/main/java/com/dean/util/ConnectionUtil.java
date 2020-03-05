package com.dean.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:postgresql://project-1.c4iyccgfeaec.us-east-2.rds.amazonaws.com:5432/postgres";
		String user = "postgres";
		String pass = "p4ssw0rd";
	
		if(connection == null || connection.isClosed()) {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, pass);
		}
		
		return connection;
	}
}
