package com.revature.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;
import com.revature.util.ConnectionUtil;

public class BankAccountDaoImplTest {

	private BankAccountDao bad = new BankAccountDaoImpl();
	
	@BeforeClass
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void getAllBankAccountsFilledListTest() {
		List<BankAccount> expected = new ArrayList<>();
		UserAccount ua = new UserAccount(1, "username", "email", "pw");
		expected.add(new BankAccount(1, new UserAccount(1, "username", "email", "pw"), "checking", 1000));
		expected.add(new BankAccount(2, new UserAccount(1, "username", "email", "pw"), "savings", 260.87));
		expected.add(new BankAccount(6, new UserAccount(1, "username", "email", "pw"), "checking", 984781234.00));
		assertEquals(expected, bad.getAllBankAccounts(ua));
	}
	
	@Test
	public void getAllBankAccountsEmptyListTest() {
		List<BankAccount> expected = new ArrayList<>();
		UserAccount ua = new UserAccount(4, "gordon", "chefzeff@yahoo.com", "4banana");
		assertEquals(expected, bad.getAllBankAccounts(ua));
	}
	
	@Test
	public void getBankAccountByIdSuccessfulTest() {
		BankAccount expected = new BankAccount(1, new UserAccount(1, "username", "email", "pw"), "checking", 1000);
		assertEquals(expected, bad.getBankAccountById(1));
	}
	
	@Test
	public void getBankAccountByIdUnsuccessfulTest() {
		assertNull(bad.getBankAccountById(8));
	}
	
	@Test
	public void updateBankAccountBalanceTest() {
		int expected = 1;
		BankAccount ba = new BankAccount(1, new UserAccount(1, "username", "email", "pw"), "checking", 1000);
		assertEquals(expected, bad.updateBankAccountBalance(ba));
	}
	
	@AfterClass
	public static void tearDown() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}
}
