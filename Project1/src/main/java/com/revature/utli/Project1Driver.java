package com.revature.utli;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Project1Driver {

	private static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		
		try {
		Connection c = ConnectionUtil.getConnection();
		String driverName = c.getMetaData().getDriverName();
		System.out.println(driverName);
	} catch (SQLException e) {
		log.error("Sorry no connection", e);
	}
	}

}
