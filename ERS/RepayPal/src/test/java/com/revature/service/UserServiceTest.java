package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.dao.UserDao;
import com.revature.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService us = new UserService();
	
	@Mock
	private UserDao ud;
	
	@Test
	public void getUserWithValidUsername() {
		when(ud.getUserByUsername("ingarcia")).thenReturn(new User("ingarcia", "Israel", "Garcia", "MyNewPassword", false));
		User u = new User("ingarcia", "Israel", "Garcia", "MyNewPassword", false);
		
		assertEquals(u, us.getUserByUsername("israel"));
	}
	
	@Test
	public void getUserWithInvalidUsername() {
		when(ud.getUserByUsername("user123")).thenReturn(null);
		assertNull(us.getUserByUsername("user123"));
	}
	
	@Test
	public void getUserWithWhiteSpace() {
		when(ud.getUserByUsername(" ")).thenReturn(null);
		assertNull(us.getUserByUsername(" "));
	}
	
	@Test
	public void getUserWithEmptyUsername() {
		when(ud.getUserByUsername("")).thenReturn(null);
		assertNull(us.getUserByUsername(""));
	}
	
	@Test
	public void validateUserWithValidInfo() {
		when(us.validateUser("ingarcia", "MyNewPassword")).thenReturn(new User("ingarcia", "Israel", "Garcia", "MyNewPassword", false));
		User u = new User("ingarcia", "Israel", "Garcia", "MyNewPassword", false);
		
		assertEquals(u, us.validateUser("ingarcia", "MyNewPassword"));
	}
	
	@Test
	public void validateWithInvalidUsername() {
		when(us.validateUser("user123", "password")).thenReturn(null);
		assertNull(us.validateUser("user123", "password"));
	}
	
	@Test
	public void validateWithWhiteSpaceUsername() {
		when(us.validateUser(" ", "password")).thenReturn(null);
		assertNull(us.validateUser(" ", "password"));
	}
	
	@Test
	public void validateWithEmptyUsername() {
		when(us.validateUser("", "password")).thenReturn(null);
		assertNull(us.validateUser("", "password"));
	}
	
	@Test
	public void validateWithInvalidPassword() {
		when(us.validateUser("ingarcia", "password")).thenReturn(null);
		assertNull(us.validateUser("ingarcia", "password"));
	}
	
	@Test
	public void validateWithWhiteSpacePassword() {
		when(us.validateUser("ingarcia", " ")).thenReturn(null);
		assertNull(us.validateUser("ingarcia", " "));
	}
	
	@Test
	public void validateWithEmptyPassword() {
		when(us.validateUser("ingarcia", "")).thenReturn(null);
		assertNull(us.validateUser("ingarcia", ""));
	}
}
