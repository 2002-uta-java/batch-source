package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.BankAccountDao;
import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceTest {
	
	@InjectMocks
	private BankAccountService bas;

	@Mock
	private BankAccountDao bad;
	
	@Test
	public void createBankAccountSuccessfullyTest() {
		BankAccount expected = new BankAccount(1, new UserAccount(), "checking", 0);
		BankAccount ba = new BankAccount(1, new UserAccount(), "checking", 0);
		when(bad.createBankAccount(ba)).thenReturn(new BankAccount(1, new UserAccount(), "checking", 0));
		assertEquals(expected, bas.createBankAccount(ba));
	}
	
	@Test
	public void createBankAccountUnsuccessfullyTest() {
		BankAccount ba = null;
		when(bad.createBankAccount(ba)).thenReturn(null);
		assertNull(bas.createBankAccount(ba));
	}
	
	@Test
	public void getAllBankAccountsEmptyListTest() {
		List<BankAccount> expected = new ArrayList<>();
		UserAccount ua = new UserAccount(1, "user", "email", "pw");
		when(bad.getAllBankAccounts(ua)).thenReturn(new ArrayList<BankAccount>());
		assertEquals(expected, bas.getAllBankAccounts(ua));
	}
	
	@Test
	public void getAllBankAccountsListOfOneTest() {
		List<BankAccount> expected = new ArrayList<>();
		expected.add(new BankAccount(1, new UserAccount(), "checking", 0));
		UserAccount ua = new UserAccount();
		when(bad.getAllBankAccounts(ua)).thenReturn(new ArrayList<BankAccount>(Arrays.asList(new BankAccount(1, new UserAccount(), "checking", 0))));
		assertEquals(expected, bas.getAllBankAccounts(ua));
	}
	
	@Test
	public void getAllBankAccountsListOfManyTest() {
		List<BankAccount> expected = new ArrayList<>();
		expected.add(new BankAccount(1, new UserAccount(), "checking", 0));
		expected.add(new BankAccount(2, new UserAccount(), "checking", 0));
		expected.add(new BankAccount(3, new UserAccount(), "savings", 0));
		UserAccount ua = new UserAccount();
		when(bad.getAllBankAccounts(ua)).thenReturn(new ArrayList<BankAccount>(Arrays.asList(new BankAccount(1, new UserAccount(), "checking", 0), new BankAccount(2, new UserAccount(), "checking", 0), new BankAccount(3, new UserAccount(), "savings", 0))));
		assertEquals(expected, bas.getAllBankAccounts(ua));
	}
	
	@Test
	public void getBankAccountByIdSuccessfullyTest() {
		BankAccount expected = new BankAccount(1, new UserAccount(), "checking", 0);
		when(bad.getBankAccountById(1)).thenReturn(new BankAccount(1, new UserAccount(), "checking", 0));
		assertEquals(expected, bas.getBankAccountById(1));
	}
	
	@Test
	public void getBankAccountByIdUnsuccessfullyTest() {
		when(bad.getBankAccountById(0)).thenReturn(null);
		assertNull(bas.getBankAccountById(0));
	}
	
	@Test
	public void updateBankAccountSuccessfullyTest() {
		int expected = 1;
		BankAccount ba = new BankAccount();
		when(bad.updateBankAccountBalance(ba)).thenReturn(1);
		assertEquals(expected, bas.updateBankAccount(ba));
	}
	
	@Test
	public void updateBankAccountUnsuccessfullyTest() {
		int expected = 0;
		BankAccount ba = new BankAccount();
		when(bad.updateBankAccountBalance(ba)).thenReturn(0);
		assertEquals(expected, bas.updateBankAccount(ba));
	}
}
