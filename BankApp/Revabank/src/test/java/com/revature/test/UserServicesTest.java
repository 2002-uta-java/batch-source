package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.UserDaos;
import com.revature.models.User;
import com.revature.services.UserServices;

@RunWith(MockitoJUnitRunner.class)
public class UserServicesTest {
	
	@InjectMocks
	private UserServices us = new UserServices();
	
	@Mock
	private UserDaos ud;
	
	@Test
	public void getUserWithValidUsername() {
		when(ud.getUserByUsername("israel")).thenReturn(new User(1, "israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd"));
		User u = new User(1, "israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd");
		
		assertEquals(u, us.getUserByUsername("israel"));
	}
	
	@Test
	public void getUserWithInvalidUsername() {
		when(ud.getUserByUsername("usero")).thenReturn(null);
		assertNull(us.getUserByUsername("usero"));
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
		when(us.validateUser("israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd")).thenReturn(new User(1, "israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd"));
		User u = new User(1, "israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd");
		
		assertEquals(u, us.validateUser("israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd"));
	}
	
	@Test
	public void validateWithInvalidUsername() {
		when(us.validateUser("usero", "password")).thenReturn(null);
		assertNull(us.validateUser("usero", "password"));
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
		when(us.validateUser("israel", "password")).thenReturn(null);
		assertNull(us.validateUser("israel", "password"));
	}
	
	@Test
	public void validateWithWhiteSpacePassword() {
		when(us.validateUser("israel", " ")).thenReturn(null);
		assertNull(us.validateUser("israel", " "));
	}
	
	@Test
	public void validateWithEmptyPassword() {
		when(us.validateUser("israel", "")).thenReturn(null);
		assertNull(us.validateUser("israel", ""));
	}
	
	
}
