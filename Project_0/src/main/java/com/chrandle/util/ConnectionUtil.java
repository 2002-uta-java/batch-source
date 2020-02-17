package com.chrandle.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionUtil {

	private static Connection connection;
	
	public static Connection GetConnection() throws SQLException{
		String port = "5432";
		String host = "localhost";
		String database = "postgres"; 
		String url = "jdbc:postgresql://";
		String username = "postgres";
		String password = "Geckobox34+";
		
		if (connection == null || connection.isClosed()){
			connection = DriverManager.getConnection((url+host+"/"+port+"/"+database),
			username,password);
		} else {
			throw new SQLException("Connection in use");
		}
		
		return connection;
	}
	
	//TODO: write system path version?
	
}
