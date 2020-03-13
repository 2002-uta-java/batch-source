package com.revature.banking.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidationTest {
	@Test
	public void testFriendlyUserName() {
		final Validation validate = new Validation();
		final String username = "jaredBennatt";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testUnderscoreFirstUserName() {
		final Validation validate = new Validation();
		final String username = "_jaredBennatt";
		assertFalse(username, validate.validateUserName(username));
	}

	@Test
	public void testDigitFirstUserName() {
		final Validation validate = new Validation();
		final String username = "1jaredBennatt";
		assertFalse(username, validate.validateUserName(username));
	}

	@Test
	public void testUnderscoreInsideUserName() {
		final Validation validate = new Validation();
		final String username = "jared_Bennatt";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testTwoUnderscoresValidUserName() {
		final Validation validate = new Validation();
		final String username = "jared_Ben_natt";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testMultipleUnderscoresValidUserName() {
		final Validation validate = new Validation();
		final String username = "j_ared_Ben_n_att";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testMultipleUnderscoresInsideInvalidUserName() {
		final Validation validate = new Validation();
		final String username = "jared__Bennatt";
		assertFalse(username, validate.validateUserName(username));
	}

	@Test
	public void testUnderscoreAtEndInvalidUserName() {
		final Validation validate = new Validation();
		final String username = "jaredBennatt_";
		assertFalse(username, validate.validateUserName(username));
	}

	@Test
	public void testDigitsInsideUserName() {
		final Validation validate = new Validation();
		final String username = "jared1bennatt";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testMultipleDigitsInsideUserName() {
		final Validation validate = new Validation();
		final String username = "jaredb123ennatt";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testMultipleDigitsInsideMultiplePlacesUserName() {
		final Validation validate = new Validation();
		final String username = "jar1ed2b3ennatt";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testDigitsAtEndUserName() {
		final Validation validate = new Validation();
		final String username = "jaredbennatt3";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testMultipleDigitsAtEndUserName() {
		final Validation validate = new Validation();
		final String username = "jaredbennatt321";
		assertTrue(username, validate.validateUserName(username));
	}

	@Test
	public void testShortPassword() {
		final Validation validate = new Validation();
		final String password = "1t!Tt";
		assertFalse(password, validate.validatePassword(password));
	}

	@Test
	public void testLength6Password() {
		final Validation validate = new Validation();
		final String password = "1t!Tt5";
		assertTrue(password, validate.validatePassword(password));
	}

	@Test
	public void testLength17Password() {
		final Validation validate = new Validation();
		final String password = "123456789!asdASD!";
		assertFalse(password, validate.validatePassword(password));
	}

	@Test
	public void testNoNumPassword() {
		final Validation validate = new Validation();
		final String password = "abcdEFG!";
		assertFalse(password, validate.validatePassword(password));
	}

	@Test
	public void testNoNumFixedPassword() {
		final Validation validate = new Validation();
		final String password = "abcdEFG!2";
		assertTrue(password, validate.validatePassword(password));
	}

	@Test
	public void testNoCapitalPassword() {
		final Validation validate = new Validation();
		final String password = "2abcdefg!";
		assertFalse(password, validate.validatePassword(password));
	}

	@Test
	public void testNoCapitalFixedPassword() {
		final Validation validate = new Validation();
		final String password = "2abCdefg!";
		assertTrue(password, validate.validatePassword(password));
	}

	@Test
	public void testNoLowerPassword() {
		final Validation validate = new Validation();
		final String password = "2ABCDEFG!";
		assertFalse(password, validate.validatePassword(password));
	}

	@Test
	public void testNoLowerFixedPassword() {
		final Validation validate = new Validation();
		final String password = "a2BCDEFG!";
		assertTrue(password, validate.validatePassword(password));
	}

	@Test
	public void testNoSpecialPassword() {
		final Validation validate = new Validation();
		final String password = "2ABCDEFg";
		assertFalse(password, validate.validatePassword(password));
	}

	@Test
	public void testAllSpecialPassword() {
		final Validation validate = new Validation();
		final String password = "2aBCDEFG@#$^%!&*";
		assertTrue(password, validate.validatePassword(password));
	}
}
