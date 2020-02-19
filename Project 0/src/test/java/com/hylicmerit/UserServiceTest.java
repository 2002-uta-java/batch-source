package com.hylicmerit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hylicmerit.models.User;
import com.hylicmerit.service.UserService;

public class UserServiceTest {
	UserService uService = new UserService();
	
	@Test
	public void testGetUserNonExistingUser() {
		User expected = null;
		assertEquals(expected, uService.getUserById("nonexistent"));
	}
	
	@Test
	public void testGetUserNoInput() {
		User expected = null;
		assertEquals(expected, uService.getUserById(""));
	}
	
	@Test
	public void testUpdateUserNull() {
		assertFalse(uService.createUser(null));
	}
	
	@Test
	public void testDeleteUserNull() {
		assertFalse(uService.createUser(null));
	}
	
	@Test
	public void checkNonExistingUserTest() {
		assertTrue(uService.checkUsernameAvailability("nonexisting"));
	}
	
	@Test
	public void checkExistingUserTest() {
		assertFalse(uService.checkUsernameAvailability("hylicmerit"));
	}
	
	@Test
	public void checkEmptyStringExistingUserTest() {
		assertFalse(uService.checkUsernameAvailability(""));
	}
	
	@Test
	public void checkNullExistingUserTest() {
		String usr = null;
		assertFalse(uService.checkUsernameAvailability(usr));
	}
	
	@Test
	public void checkNoAtEmailTest() {
		assertFalse(uService.validateEmail("email.com"));
	}
	
	@Test
	public void checkNoComEmailTest() {
		assertFalse(uService.validateEmail("email@email"));
	}
	
	@Test
	public void checkEmptyEmailTest() {
		assertFalse(uService.validateEmail(""));
	}
	
	@Test
	public void checkNullEmailTest() {
		String email = null;
		assertFalse(uService.validateEmail(email));
	}
	
	@Test
	public void checkValidEmailTest() {
		assertTrue(uService.validateEmail("ex@gmail.com"));
	}
}
