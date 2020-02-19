package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.revature.daos.AccountDaos;
import com.revature.daos.AccountDaosImplementation;
import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountServicesTest {
	AccountDaos as = new AccountDaosImplementation();
	
	@Test
	public void testConnection() throws SQLException {
		Connection c = ConnectionUtil.getConnection();
		String driverName = c.getMetaData().getDriverName();
		assertEquals("PostgreSQL JDBC Driver", driverName);
	}
	
	@Test
	public void getAccountWithValidId() {
		Account a = new Account(1,1,2000);
		assertEquals(a,as.getAccount(1));
	}
	
	@Test
	public void getAccountWithInvalidId() {
		assertEquals(null, as.getAccount(30));
	}
	
	
}
