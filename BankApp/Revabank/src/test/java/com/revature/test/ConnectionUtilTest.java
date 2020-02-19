package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.revature.util.ConnectionUtil;

public class ConnectionUtilTest {
	
	@Test
	public void testConnection() throws SQLException {
		Connection c = ConnectionUtil.getConnection();
		String driverName = c.getMetaData().getDriverName();
		assertEquals("PostgreSQL JDBC Driver", driverName);
	}
}
