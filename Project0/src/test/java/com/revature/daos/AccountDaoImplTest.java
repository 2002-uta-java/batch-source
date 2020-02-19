package com.revature.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;
import com.revature.services.BankAccountService;
import com.revature.services.LoginService;
import com.revature.services.UserAccountService;
import com.revature.util.ConnectionUtil;

import org.h2.tools.RunScript;

public class AccountDaoImplTest {
		
	private UserAccountService uas = new UserAccountService();
	
	private BankAccountService bas = new BankAccountService();
	
	private AccountDao dao = new AccountDaoImpl();
	
	@BeforeClass
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void testExistingUsernames1() {
		List<UserAccount> users = new ArrayList<>();
		
		assertTrue(uas.userAccountExist("user03"));
	}
	
	@Test
	public void testExistingUsernames2() {
		List<UserAccount> users = new ArrayList<>();
		
		assertFalse(uas.userAccountExist("user99"));
	}
	
	@Test
	public void testDepositTenDollars() {
		BankAccount b = dao.getBankAccountByBankId(1);
		System.out.println("TEST: deposit $10");
		bas.deposit(b);
		b = dao.getBankAccountByBankId(1);
		
		assertEquals(b.getBalance(), 60.00f, 0.001);
	}
	
	@Test
	public void testDepositNegativeValue() {
		BankAccount b = dao.getBankAccountByBankId(3);
		System.out.println("TEST: deposit $-10, then cancel.");
		bas.deposit(b);
		b = dao.getBankAccountByBankId(3);
		
		assertEquals(b.getBalance(), 0f, 0.001); // balance should not change.
	}
	
	@Test
	public void testDepositOverMaxValue() {
		BankAccount b = dao.getBankAccountByBankId(2);
		System.out.println("TEST: deposit $1.");
		bas.deposit(b);
		b = dao.getBankAccountByBankId(2);
		
		assertEquals(b.getBalance(), 20000000f, 0.001); // balance should not change.
	}
	
	@Test
	public void testWithdrawTenDollars() {
		BankAccount b = dao.getBankAccountByBankId(5);
		System.out.println("TEST: withdraw $10");
		bas.withdraw(b);
		b = dao.getBankAccountByBankId(5);
		
		assertEquals(b.getBalance(), 0.50f, 0.001);
	}
	
	@Test
	public void testWithdrawTooMuch() {
		BankAccount b = dao.getBankAccountByBankId(3);
		System.out.println("TEST: withdraw $1, then cancel.");
		bas.withdraw(b);
		b = dao.getBankAccountByBankId(3);
		
		assertEquals(b.getBalance(), 0f, 0.001);
	}
	
	@Test
	public void testWithdrawNegativeValue() {
		BankAccount b = dao.getBankAccountByBankId(3);
		System.out.println("TEST: withdraw $-1, then cancel.");
		bas.withdraw(b);
		b = dao.getBankAccountByBankId(3);
		
		assertEquals(b.getBalance(), 0f, 0.001);
	}
	
	@Test
	public void testTransferOneHundredDollars() {
		BankAccount b1 = dao.getBankAccountByBankId(6);
		System.out.println("TEST: transfer $100 to bankId: 7");
		bas.transferFunds(b1);
		b1 = dao.getBankAccountByBankId(6);
		BankAccount b2 = dao.getBankAccountByBankId(7);
		
		assertEquals(b1.getBalance(), 0f, 0.001);
		assertEquals(b2.getBalance(), 100f, 0.001);
	}
	
	@Test
	public void testCreateNewUserAccount() throws NoSuchAlgorithmException {
		System.out.println("TEST: username -> 'test' ; password -> anything");
		uas.userAccountMenu();
		
		assertTrue(uas.userAccountExist("test"));
	}
	
	@AfterClass
	public static void tearDown() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}
	
	
}
