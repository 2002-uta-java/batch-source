package com.hylicmerit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
}
