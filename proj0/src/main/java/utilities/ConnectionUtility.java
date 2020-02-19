package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	
	private static Connection connection;

	public static Connection getConnection() throws SQLException {

		/*
		 * for more information on formatting the connection url or connecting to your
		 * db w JDBC https://jdbc.postgresql.org/documentation/80/connect.html
		 */

		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "fortune@";

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;

	}

}


