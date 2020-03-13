package com.revature.banking.frontend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;

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
			assertEquals(name, cnu.getName("", io));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNameValidWithSpaces() {
		final String name = "Jared B";
		try {
			when(io.readLine()).thenReturn(name);
			assertEquals(name, cnu.getName("", io));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNameWithNullString() {
		final String name = "";

		// need to mock retry() method (don't retry)
		cnu = spy(CreateNewUser.class);

		try {
			when(io.readLine()).thenReturn(name);
			assertNull(cnu.getName("", io));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNameWithLongString() {
		final String name = getRandomString(UserService.NAME_LENGTH);

		try {
			when(io.readLine()).thenReturn(name);
			assertEquals(name, cnu.getName("", io));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNameWithTooLongByOneString() {
		final String name = getRandomString(UserService.NAME_LENGTH + 1);

		// need to mock retry() method (don't retry)
		cnu = spy(CreateNewUser.class);

		try {
			when(io.readLine()).thenReturn(name);
			doReturn(false).when((BankInteraction) cnu).retry(io);
			assertNull(cnu.getName("", io));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getRandomString(final int length) {
		final char[] string = new char[length];
		for (int i = 0; i < length; ++i)
			string[i] = (char) ('a' + (int) (26 * Math.random()));
		return new String(string);
	}
}
