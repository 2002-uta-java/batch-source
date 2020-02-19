package com.revature.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.models.BankAccount;
import com.revature.models.Client;
import com.revature.models.UserAccount;

public class UserToBankAccountServiceTest {
	
	private static UserToBankAccountService utbas = new UserToBankAccountService();
	private static ClientService cs = new ClientService();
	private static UserAccountService uas = new UserAccountService();
	private static BankAccountService bas = new BankAccountService();
	
	@Test
	public void establishAssociationTest() {
		
		Client c = new Client();
		cs.createClient(c);
		
		UserAccount ua = new UserAccount("test@email.com", "testPassword");
		ua.setClientId(c.getId());
		uas.createUserAccount(ua);
		
		BankAccount ba1 = new BankAccount(1, 777.77);
		bas.createBankAccount(ba1);
				
		assertTrue(utbas.establishAssociation(ua.getId(), ba1.getId()));
		
	}

}
