package com.revature.BankApp;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.daos.Driver;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class EvaluationServiceTest {

private UserDao ud = new UserDaoImpl();
	
	@Test
	public void testStartingBalanceIsZero() {
		User u = new User("lukadagoat123", "slovenia1", (double) 0.00);
		assertTrue(0 == u.balance);
	}
	
	@Test
	public void testStartingBalanceIsNotZero() {
		User u = new User("getmoney2", "passsword", (double) 5400.20);
		assertFalse(0 == u.balance);
	}
	
//	@Test
//	public void testgetbalance() {
//		User u = new User("getmoney2", "passsword", (double) 5400.20);
//		assertEquals(5400.20, ud.checkBalance());
//	}
//	
//	@Test
//	public void testlogout() {
//		char input = 'd';
//		Driver test = new Driver();
//		assertEquals('d', test.showSwitchMenu());
//	}
//	
//	public Object testAnUnacceptableWithdraw()throws SQLException {
//		Connection myConn= ConnectionUtil.getConnection();
//		CallableStatement myCallable = null;
//		String userEmail = "testcase@gmail.com";
//		String password = "password";
//		Double withdrawAmount = (double) 223;    
//	
//		// prepare the stored procedure call
//		String sql = "{call withdraw(?, ?)}";
//		myCallable = myConn.prepareCall(sql);
//		// set the parameters
//		myCallable.setString(1, userEmail);
//		myCallable.setDouble(2, withdrawAmount);
//		// call the stored procedure
//		myCallable.execute(sql);
//		return null;
//	}
//	
//	@Test
//	public void withdraw_should_throw_SQL_Exception() {
//			   try {
//				assertNull(testAnUnacceptableWithdraw());
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			}
}
