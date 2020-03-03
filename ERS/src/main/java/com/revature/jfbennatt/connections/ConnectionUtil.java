package com.revature.jfbennatt.connections;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.postgresql.Driver;

/**
 * Utility class for getting a connection to my database.
 * 
 * @author Jared F Bennatt
 *
 */
public class ConnectionUtil {
	private static Connection con = null;
	private static final String AWS_URL = System.getenv("AWS_POSTGRESQL_URL");
	private static final String URL = "jdbc:postgresql://" + AWS_URL + "/postgres";
	private static final String USERNAME = System.getenv("JDBC_USERNAME");
	private static final String PASSWORD = System.getenv("JDBC_PASSWORD");
	private static final boolean IS_TEST = Boolean.parseBoolean(System.getenv("DB_TEST"));

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

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			Logger.getRootLogger().fatal("Failed to load Postgresql JDBC driver: " + e.getMessage());
			System.out.println("Failed to load Postgresql JDBC driver: " + e.getMessage());
		}
	}

	/**
	 * Returns a {@link Connection} object that can be used to access the database.
	 * 
	 * @return {@link Connection} object to access the database.
	 */
	public static Connection getConnection() {
		if (IS_TEST) {
			return getH2Connection();
		} else {
			try {
				if (con == null || con.isClosed())
					con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				Logger.getRootLogger().fatal(e.getStackTrace());
			}
		}

		return con;
	}

	/**
	 * Returns a connection to the local H2 database (as opposed to the AWS
	 * Postgresql one)
	 * 
	 * @return {@link Connection} object to access the database.
	 */
	public static Connection getH2Connection() {
		try {
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection("jdbc:h2:~/test");
			}
		} catch (SQLException e) {
			Logger.getRootLogger().fatal(e.getMessage());
		}

		return con;
	}
}
