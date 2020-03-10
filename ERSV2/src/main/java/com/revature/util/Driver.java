package com.revature.util;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

	public static void main(String[] args) {

		try {
			Connection c = ConnectionUtil.getConnection();
			String driverName = c.getMetaData().getDriverName();
			System.out.println(driverName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}