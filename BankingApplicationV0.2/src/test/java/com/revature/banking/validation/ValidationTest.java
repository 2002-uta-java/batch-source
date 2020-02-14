package com.revature.banking.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidationTest {
	@Test
	public void testFriendlyUserName() {
		final String username = "jaredBennatt";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testUnderscoreFirstUserName() {
		final String username = "_jaredBennatt";
		assertFalse(username, Validation.validateUserName(username));
	}

	@Test
	public void testDigitFirstUserName() {
		final String username = "1jaredBennatt";
		assertFalse(username, Validation.validateUserName(username));
	}

	@Test
	public void testUnderscoreInsideUserName() {
		final String username = "jared_Bennatt";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testTwoUnderscoresValidUserName() {
		final String username = "jared_Ben_natt";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testMultipleUnderscoresValidUserName() {
		final String username = "j_ared_Ben_n_att";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testMultipleUnderscoresInsideInvalidUserName() {
		final String username = "jared__Bennatt";
		assertFalse(username, Validation.validateUserName(username));
	}

	@Test
	public void testUnderscoreAtEndInvalidUserName() {
		final String username = "jaredBennatt_";
		assertFalse(username, Validation.validateUserName(username));
	}

	@Test
	public void testDigitsInsideUserName() {
		final String username = "jared1bennatt";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testMultipleDigitsInsideUserName() {
		final String username = "jaredb123ennatt";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testMultipleDigitsInsideMultiplePlacesUserName() {
		final String username = "jar1ed2b3ennatt";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testDigitsAtEndUserName() {
		final String username = "jaredbennatt3";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testMultipleDigitsAtEndUserName() {
		final String username = "jaredbennatt321";
		assertTrue(username, Validation.validateUserName(username));
	}

	@Test
	public void testShortPassword() {
		final String password = "1t!Tt";
		assertFalse(password, Validation.validatePassword(password));
	}

	@Test
	public void testLength6Password() {
		final String password = "1t!Tt5";
		assertTrue(password, Validation.validatePassword(password));
	}

	@Test
	public void testLength17Password() {
		final String password = "123456789!asdASD!";
		assertFalse(password, Validation.validatePassword(password));
	}

	@Test
	public void testNoNumPassword() {
		final String password = "abcdEFG!";
		assertFalse(password, Validation.validatePassword(password));
	}

	@Test
	public void testNoNumFixedPassword() {
		final String password = "abcdEFG!2";
		assertTrue(password, Validation.validatePassword(password));
	}

	@Test
	public void testNoCapitalPassword() {
		final String password = "2abcdefg!";
		assertFalse(password, Validation.validatePassword(password));
	}

	@Test
	public void testNoCapitalFixedPassword() {
		final String password = "2abCdefg!";
		assertTrue(password, Validation.validatePassword(password));
	}

	@Test
	public void testNoLowerPassword() {
		final String password = "2ABCDEFG!";
		assertFalse(password, Validation.validatePassword(password));
	}

	@Test
	public void testNoLowerFixedPassword() {
		final String password = "a2BCDEFG!";
		assertTrue(password, Validation.validatePassword(password));
	}

	@Test
	public void testNoSpecialPassword() {
		final String password = "2ABCDEFg";
		assertFalse(password, Validation.validatePassword(password));
	}

	@Test
	public void testAllSpecialPassword() {
		final String password = "2aBCDEFG@#$^%!&*";
		assertTrue(password, Validation.validatePassword(password));
	}
}
