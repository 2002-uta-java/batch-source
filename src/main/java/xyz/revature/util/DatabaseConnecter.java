package xyz.revature.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecter {
	private static Connection connection;
	
	public static Connection hardcodedConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not register driver!");
            e.printStackTrace();

        }
		String url = "url""
		String username = "postgres";
		String password = "password";

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}
	
}


