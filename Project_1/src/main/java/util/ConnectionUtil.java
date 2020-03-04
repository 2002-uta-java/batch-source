package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionUtil {

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException{


		String username = "postgres";
		String password = "Geckobox34+";

		if (connection == null || connection.isClosed()){
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/Project_1",
			username,password);
		} else {
			throw new SQLException("Connection in use");
		}
		
		return connection;
	}
	

	
}