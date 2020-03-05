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

import com.revature.daos.UserAccountDao;
import com.revature.models.UserAccount;

@RunWith(MockitoJUnitRunner.class)
public class UserAccountServiceTest {

	@InjectMocks
	private UserAccountService uas;

	@Mock
	private UserAccountDao uad;
	
	@Test
	public void createUserAccountSuccessfullyTest() {
		UserAccount expected = new UserAccount(1, "username", "email", "pw");
		UserAccount ua = new UserAccount(1, "username", "email", "pw");
		when(uad.createUserAccount(ua)).thenReturn(new UserAccount(1, "username", "email", "pw"));
		assertEquals(expected, uas.createUserAccount(ua));
	}

	@Test
	public void createUserAccountUnsuccessfullyTest() {
		UserAccount ua = null;
		when(uad.createUserAccount(ua)).thenReturn(null);
		assertNull(uas.createUserAccount(ua));
	}
	
	@Test
	public void getAllUserAccountsEmptyListTest() {
		List<UserAccount> expected = new ArrayList<>();
		when(uad.getAllUserAccounts()).thenReturn(new ArrayList<UserAccount>());
		assertEquals(expected, uas.getAllUserAccounts());
	}
	
	@Test
	public void getAllUserAccountsListOfOneTest() {
		List<UserAccount> expected = new ArrayList<>();
		expected.add(new UserAccount(1, "username", "email", "pw"));
		when(uad.getAllUserAccounts()).thenReturn(new ArrayList<UserAccount>(Arrays.asList(new UserAccount(1, "username", "email", "pw"))));
		assertEquals(expected, uas.getAllUserAccounts());
	}
	
	@Test
	public void getAllUserAccountsListOfManyTest() {
		List<UserAccount> expected = new ArrayList<>();
		expected.add(new UserAccount(1, "username", "email", "pw"));
		expected.add(new UserAccount(2, "user", "email@google.com", "pw"));
		expected.add(new UserAccount(3, "apple", "", "pw"));
		when(uad.getAllUserAccounts()).thenReturn(new ArrayList<UserAccount>(Arrays.asList(new UserAccount(1, "username", "email", "pw"), new UserAccount(2, "user", "email@google.com", "pw"), new UserAccount(3, "apple", "", "pw"))));
		assertEquals(expected, uas.getAllUserAccounts());
	}
	
	@Test
	public void getUserAccountByUsernameSuccessfullyTest() {
		UserAccount expected = new UserAccount(1, "username", "email", "pw");
		when(uad.getUserAccountByUsername("username")).thenReturn(new UserAccount(1, "username", "email", "pw"));
		assertEquals(expected, uas.getUserAccountByUsername("username"));
	}
	
	@Test
	public void getUserAccountByUsernameUnsuccessfullyTest() {;
		when(uad.getUserAccountByUsername("username")).thenReturn(null);
		assertNull(uas.getUserAccountByUsername("username"));
	}
	
	@Test
	public void getUserAccountByEmailSuccessfullyTest() {
		UserAccount expected = new UserAccount(1, "username", "email", "pw");
		when(uad.getUserAccountByUsername("email")).thenReturn(new UserAccount(1, "username", "email", "pw"));
		assertEquals(expected, uas.getUserAccountByUsername("email"));
	}
	
	@Test
	public void getUserAccountByEmailUnsuccessfullyTest() {;
		when(uad.getUserAccountByUsername("email")).thenReturn(null);
		assertNull(uas.getUserAccountByUsername("email"));
	}
}
