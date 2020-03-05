package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class ConnectionUtility {
	
	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error: unable to load driver class!");
		}


		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "fortune@";

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		
		
		return connection;

	}

}


