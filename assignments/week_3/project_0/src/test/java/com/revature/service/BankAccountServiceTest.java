package com.revature.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.models.BankAccount;
import com.revature.models.Client;
import com.revature.models.UserAccount;

public class BankAccountServiceTest {
	
	private static BankAccountService bas = new BankAccountService();
	private static ClientService cs = new ClientService();
	private static UserAccountService uas = new UserAccountService();
	private static UserToBankAccountService utbas = new UserToBankAccountService();
	
	@Test
	public void createBankAccountTest() {
		BankAccount expected = new BankAccount(1, 777.77);
		expected.setId(1);
		expected.setAccountNumber(1);
		BankAccount ba2 = new BankAccount(1, 777.77);
		BankAccount actual = bas.createBankAccount(ba2);
		actual.setId(1);
		actual.setAccountNumber(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getBankAccountsTest() {
		Client c = new Client();
		cs.createClient(c);
		
		UserAccount ua = new UserAccount("test@email.com", "testPassword");
		ua.setClientId(c.getId());
		uas.createUserAccount(ua);
		
		BankAccount ba1 = new BankAccount(1, 777.77);
		bas.createBankAccount(ba1);
		
		utbas.establishAssociation(ua.getId(), ba1.getId());
		
		List<BankAccount> expected = new ArrayList<BankAccount>();
		
		expected.add(ba1);
		
		List<BankAccount> actual = bas.getAccounts(ua.getId());
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void depositAmountTest() {
		BankAccount ba = new BankAccount(1, 555.55);
		bas.createBankAccount(ba);
		
		double depositAmount = 222.222;
		
		assertEquals(1, bas.depositAmount(ba, depositAmount));
	}
	
	@Test
	public void withdrawAmountTest() {
		BankAccount ba = new BankAccount(1, 777.77);
		bas.createBankAccount(ba);
		
		double withdrawAmount = 222.22;
		
		assertEquals(1, bas.withdrawAmount(ba, withdrawAmount));
	}

}
