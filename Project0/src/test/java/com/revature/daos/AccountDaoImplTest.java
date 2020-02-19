package com.revature.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.models.UserAccount;
import com.revature.services.LoginService;
import com.revature.services.UserAccountService;
import com.revature.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDaoImplTest {
		
	@InjectMocks
	private UserAccountService uas;
	
	@Mock
	private AccountDao dao;
	
	@Test
	public void testExistingUsernames1() {
		List<UserAccount> users = new ArrayList<>();
		users.add(new UserAccount("elena22", "encrypt", 1));
		users.add(new UserAccount("maxwell", "encrypt", 2));
		users.add(new UserAccount("obama2008", "encrypt", 3));
		
		when(dao.getUserAccounts()).thenReturn(users);
		
		assertTrue(uas.userAccountExist("maxwell"));
	}
	
	@Test
	public void testExistingUsernames2() {
		List<UserAccount> users = new ArrayList<>();
		users.add(new UserAccount("elena22", "encrypt", 1));
		users.add(new UserAccount("maxwell", "encrypt", 2));
		users.add(new UserAccount("obama2008", "encrypt", 3));
		
		when(dao.getUserAccounts()).thenReturn(users);
		
		assertFalse(uas.userAccountExist("maxwell005"));
	}
	
	
}
