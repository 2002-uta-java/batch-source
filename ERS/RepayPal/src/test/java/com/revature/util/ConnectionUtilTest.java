package com.revature.util;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionUtilTest {

	@Test
	public void testConnection() throws SQLException, ClassNotFoundException {
		Connection c = ConnectionUtil.getConnection();
		String driverName = c.getMetaData().getDriverName();
		assertEquals("PostgreSQL JDBC Driver", driverName);
	}
}
