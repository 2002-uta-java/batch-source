package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.models.User;
import com.revature.services.UserServices;

public class UserServicesTest {
	UserServices us = new UserServices();
	@Test
	public void getUserWithValidUsername() {
		User u = new User(1, "israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd");
		assertEquals(u, us.getUserByUsername("israel"));
	}
	
	@Test
	public void getUserWithInvalidUsername() {
		assertEquals(null, us.getUserByUsername("usero"));
	}
	
	@Test
	public void getUserWithWhiteSpace() {
		assertEquals(null, us.getUserByUsername(" "));
	}
	
	@Test
	public void getUserWithEmptyUsername() {
		assertEquals(null, us.getUserByUsername(""));
	}
	
	@Test
	public void validateUserWithValidInfo() {
		User u = new User(1, "israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd");
		assertEquals(u, us.validateUser("israel", "48346fe92ce9f17f99666a9760715d5cfcac31fd"));
	}
	
	@Test
	public void validateWithInvalidUsername() {
		assertEquals(null, us.validateUser("usero", "password"));
	}
	
	@Test
	public void validateWithWhiteSpaceUsername() {
		assertEquals(null, us.validateUser(" ", "password"));
	}
	
	@Test
	public void validateWithEmptyUsername() {
		assertEquals(null, us.validateUser("", "password"));
	}
	
	@Test
	public void validateWithInvalidPassword() {
		assertEquals(null, us.validateUser("israel", "password"));
	}
	
	@Test
	public void validateWithWhiteSpacePassword() {
		assertEquals(null, us.validateUser("israel", " "));
	}
	
	@Test
	public void validateWithEmptyPassword() {
		assertEquals(null, us.validateUser("israel", ""));
	}
	
	
}
