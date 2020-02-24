package com.revature.jfbennatt.reimbursement.connections;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	private static Connection con = null;
	private static final String URL = "jdbc:postgresql://java-2002.cfhvkame7ojc.us-east-2.rds.amazonaws.com:5432/postgres";
	private static final String USERNAME = System.getenv("JDBC_USERNAME");
	private static final String PASSWORD = System.getenv("JDBC_PASSWORD");

	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getRootLogger().fatal(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Logger.getRootLogger().fatal("Failed to load postgres driver");
		}

		return con;
	}
}
