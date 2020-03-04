package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionUtil {

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException{


//		String username = "postgres";
//		String password = "Geckobox34+";
//
//		if (connection == null || connection.isClosed()){
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost/Project_1",
//			username,password);
//		} else {
//			throw new SQLException("Connection in use");
//		}
//		
//		return connection;
		
		
		String username = "postgres";
		String password = "Geckobox34+";
		String url = "jdbc:postgresql://localhost/Project_1";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("FLAG");
			System.exit(0);
		}
		
		if (connection == null || connection.isClosed()){
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/Project_1",
			username,password);
		} else {
			throw new SQLException("Connection in use");
		}
		
		return connection;
		
	}
	

	
}