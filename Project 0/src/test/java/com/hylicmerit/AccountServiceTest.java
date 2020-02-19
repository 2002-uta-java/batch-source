package com.hylicmerit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import com.hylicmerit.models.Account;
import com.hylicmerit.models.User;
import com.hylicmerit.service.AccountService;

public class AccountServiceTest {
	AccountService aService = new AccountService();
	
	@Test
	public void testGetAllAccountsNullInput() {
		List<Account> expected = null;
		assertEquals(expected, aService.getAllAccounts(null));
	}
	
	@Test
	public void testGetAccountNullAccount() {
		User u = User.getInstance();
		assertFalse(aService.createAccount(null, u));
	}
	
	@Test
	public void testGetAccountNullUser() {
		Account a = new Account();
		assertFalse(aService.createAccount(a, null));
	}
	
	@Test
	public void testUpdateAccountNotEnoughFunds() {
		Account a = new Account(500.50,"savings");
		assertFalse(aService.updateAccount(a, 600));
	}
	
	@Test
	public void testUpdateAccountNullAccount() {
		assertFalse(aService.updateAccount(null, 600));
	}
	
	@Test
	public void testUpdateAccountNoAmount() {
		Account a = new Account(500.50,"savings");
		assertFalse(aService.updateAccount(a, 0));
	}
	
	@Test
	public void testDeleteAccountNullAccount() {
		assertFalse(aService.deleteAccount(null));
	}
}
