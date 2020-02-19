package com.revature.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.models.Client;
import com.revature.models.UserAccount;

public class UserAccountServiceTest {
	
	private static UserAccountService uas = new UserAccountService();
	private static ClientService cs = new ClientService();
	
	@Test
	public void createUserAccountTest() {
		Client c = new Client();
		cs.createClient(c);
		
		UserAccount expected = new UserAccount("test@email.com", "testPassword");
		expected.setId(1);
		expected.setClientId(c.getId());
		
		UserAccount ua2 = new UserAccount("test@email.com", "testPassword");
		ua2.setClientId(c.getId());
		UserAccount actual = uas.createUserAccount(ua2);
		actual.setId(1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getUserAccountTest() {
		Client c = new Client();
		cs.createClient(c);
		
		UserAccount ua = new UserAccount("test@email.com", "testPassword");
		ua.setClientId(c.getId());
		UserAccount expected = uas.createUserAccount(ua);
		
		UserAccount actual = uas.getUserAccount("test@email.com", "testPassword");
		
		assertEquals(expected, actual);
	}

}
