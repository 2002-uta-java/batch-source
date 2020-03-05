package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.AccountDao;
import com.revature.models.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	
	@InjectMocks
	private AccountService as;
	
	@Mock
	private AccountDao ad;
	
	@Test
	public void findAccountByValidEmailAndPassword() {
		
		LocalDateTime testDate = LocalDateTime.now();
		
		when(ad.getAccountByEmail("test@test.com")).thenReturn(new Account(testDate, "Test_Name", "test@test.com", "StrongPassword", "EMPLOYEE", 0));
		
		Account a = new Account(testDate, "Test_Name", "test@test.com", "StrongPassword", "EMPLOYEE", 0);
		
		assertEquals(a, as.getAccountByEmailAndPassword("test@test.com", "StrongPassword"));
		
	}
	
	@Test
	public void findAccountByEmailAndInvalidPassword() {
		
		LocalDateTime testDate = LocalDateTime.now();
		
		when(ad.getAccountByEmail("test@test.com")).thenReturn(new Account(testDate, "Test_Name", "test@test.com", "StrongPassword", "EMPLOYEE", 0));
		
		assertNull(as.getAccountByEmailAndPassword("test@test.com", "DifferentPass"));
		
	}
	
}
