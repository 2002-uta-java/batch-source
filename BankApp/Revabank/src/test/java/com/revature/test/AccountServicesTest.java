package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.AccountDaos;
import com.revature.models.Account;
import com.revature.services.AccountServices;

@RunWith(MockitoJUnitRunner.class)
public class AccountServicesTest {
	
	@InjectMocks
	AccountServices as = new AccountServices();
	
	@Mock
	AccountDaos ad;
	
	@Test
	public void getAccountWithValidId() {
		when(as.getAccount(1)).thenReturn(new Account(1,1,2000));
		Account a = new Account(1,1,2000);
		
		assertEquals(a,as.getAccount(1));
	}
	
	@Test
	public void getAccountWithInvalidId() {
		when(as.getAccount(30)).thenReturn(null);
		assertNull(as.getAccount(30));
	}
	
}
