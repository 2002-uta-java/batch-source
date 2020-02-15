package com.revature.banking.frontend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewUserTest {
	// this is what I'm testing
	@InjectMocks
	private CreateNewUser cnu;

	// need to mock these
	@Mock
	private CLI io;
	@Mock
	private BankAccountService bas;
	@Mock
	private UserService us;

	@Test
	public void testGetNameValid() {
		final String name = "Jared";
		try {
			when(io.readLine()).thenReturn(name);
		} catch (IOException e) {
			// this won't happen because I'm mocking io
		}
		try {
			assertEquals(name, cnu.getName("", io));
		} catch (IOException e) {
			// this shouldn't happen because I'm mocking io
		}
	}

	@Test
	public void testGetNameValidWithSpaces() {
		final String name = "Jared B";
		try {
			when(io.readLine()).thenReturn(name);
		} catch (IOException e) {
			// this won't happen because I'm mocking io
		}
		try {
			assertEquals(name, cnu.getName("", io));
		} catch (IOException e) {
			// this shouldn't happen because I'm mocking io
		}
	}
}
