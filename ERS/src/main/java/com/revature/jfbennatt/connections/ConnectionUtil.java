package com.revature.jfbennatt.connections;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.postgresql.Driver;

public class ConnectionUtil {
	private static Connection con = null;
	private static final String AWS_URL = System.getenv("AWS_POSTGRESQL_URL");
	private static final String URL = "jdbc:postgresql://" + AWS_URL + "/postgres";
	private static final String USERNAME = System.getenv("JDBC_USERNAME");
	private static final String PASSWORD = System.getenv("JDBC_PASSWORD");

	private ConnectionUtil() {
		super();
	}

	// load the postgres JDBC driver
	static {
		try {
			Class.forName(Driver.class.getName());
		} catch (ClassNotFoundException e) {
			Logger.getRootLogger().fatal("Failed to load Postgresql JDBC driver: " + e.getMessage());
		}
	}

	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed())
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			Logger.getRootLogger().fatal(e.getStackTrace());
		}

		return con;
	}
}
