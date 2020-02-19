package com.charnecki.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.charnecki.models.Account;
import com.charnecki.util.ConnectionUtil;

public class AccountDaoImplTest {
	
	private AccountDao ad = new AccountDaoImpl();
	
	@BeforeClass
	public static void setupMockData() throws SQLException, FileNotFoundException {
		try (Connection c = ConnectionUtil.getConnection()) {
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void getAllAccounts() {
		List<Account> aList = ad.getAllAccounts();
		
//		for (Account a: aList) {
//			System.out.println(a);
//		}

		assertNotNull(aList);
	}
	
	@Test
	public void getValidId() {
		Account a = ad.getAccountById(5);
		
//		System.out.println(a);

		assertNotNull(a);
	}
	
//	@Test
//	public void getInvalidId() {
//		Transaction t = td.getTransactionById(100);
//
//		assertNull(t);
//	}
//	
//	@Test
//	public void createNewTransaction() {
//		Transaction t = new Deposit(105, 500, 4, "This is a test");
//		
//		assertEquals(32, td.createTransaction(t));
//	}
//	
//	@Test
//	public void getNewTransaction() {
//		Transaction t = new Deposit(31, 500, 4, "This is a test");
//		td.createTransaction(t);
//		
//		assertEquals(t, td.getTransactionById(31));
//	}
//	
//	@Test
//	public void updateTransaction() {
//		Transaction t = new Deposit(25, 500, 4, "This is a test");
//		
//		assertEquals(1, td.updateTransaction(t));
//	}
//	
//	@Test
//	public void updateInvalidTransaction() {
//		Transaction t = new Deposit(100, 500, 4, "This is a test");
//		
//		assertEquals(0, td.updateTransaction(t));
//	}
//	
//	@Test
//	public void deleteTransaction() {
//		Transaction t = new Deposit(20, 500, 4, "This is a test");
//		assertEquals(1, td.deleteTransaction(t));
//	}
	
	@AfterClass
	public static void teardownMockData() throws SQLException, FileNotFoundException {
		try (Connection c = ConnectionUtil.getConnection()) {
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}

}
