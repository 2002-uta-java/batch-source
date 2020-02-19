package com.revature.banking.frontend.validation;

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

	@Test
	public void testValidTaxId() {
		final String taxid = "9464957244";
		assertTrue(Validation.validateTaxid(taxid));
	}

	@Test
	public void testValidTaxIdAll0s() {
		final String taxid = "0000000000";
		assertTrue(Validation.validateTaxid(taxid));
	}

	@Test
	public void test10DigitTaxIdWithCharacterAtBeginning() {
		final String taxid = "a000000000";
		assertFalse(Validation.validateTaxid(taxid));
	}

	@Test
	public void testTaxID9DigitAllNumbers() {
		final String taxid = "123456789";
		assertFalse(Validation.validateTaxid(taxid));
	}

	@Test
	public void testNaturalNumberLarge() {
		for (int i = 1; i < 100; ++i)
			assertTrue(Validation.isNaturalNumber("" + i));
	}

	@Test
	public void testNaturalNumbersStartingWith0() {
		for (int i = 0; i > -100; --i)
			assertFalse(Validation.isNaturalNumber("" + i));
	}

	@Test
	public void testNaturalNumberWithLetters() {
		final String number = "0xDF";
		assertFalse(Validation.isNaturalNumber(number));
	}

	@Test
	public void test0Amount() {
		final String number = "0";
		assertTrue(Validation.validateAmount(number));
	}

	@Test
	public void test0AmountWith1DecimalNoTrailingZeros() {
		final String number = "0.";
		assertFalse(Validation.validateAmount(number));
	}

	@Test
	public void test0AmountWithDecimal1Trailing() {
		final String number = "0.0";
		assertTrue(Validation.validateAmount(number));
	}

	@Test
	public void test0AmountWithDecimal2Trailing() {
		final String number = "0.00";
		assertTrue(Validation.validateAmount(number));
	}

	@Test
	public void test0AmountWIthDecimal3Trailing() {
		final String number = "0.000";
		assertFalse(Validation.validateAmount(number));
	}

	@Test
	public void testAmountWithCharacters() {
		final String number = "0f";
		assertFalse(Validation.validateAmount(number));
	}

	@Test
	public void testNegativeAmount() {
		final String number = "-3";
		assertFalse(Validation.validateAmount(number));
	}

	@Test
	public void testNegativeWithDecimal() {
		final String number = "-3.4";
		assertFalse(Validation.validateAmount(number));
	}

	@Test
	public void testAmountWithMultipleDecimals() {
		final String number = "1.2.2";
		assertFalse(Validation.validateAmount(number));
	}

	@Test
	public void testLargeNumber() {
		final String number = "1234567890875";
		assertTrue(Validation.validateAmount(number));
	}

	@Test
	public void validateDecimal() {
		final String number = "123";
		assertTrue(Validation.validateDecimal(number));
	}

	@Test
	public void validateDecimalWithDecimals() {
		final String number = "123.";
		assertFalse(Validation.validateDecimal(number));
	}

	@Test
	public void validateDecimalWith1Decimal() {
		final String number = "123.1";
		assertTrue(Validation.validateDecimal(number));
	}

	@Test
	public void validateDecimalWithManyDecimals() {
		final String number = "123.12345";
		assertTrue(Validation.validateDecimal(number));
	}

	@Test
	public void validateDecimalNegative() {
		final String number = "-34";
		assertTrue(Validation.validateDecimal(number));
	}

	@Test
	public void validateDecimalNegativeWithDecimals() {
		final String number = "-34.2";
		assertTrue(Validation.validateDecimal(number));
	}

	@Test
	public void validateDecimalWithLeadingZero() {
		final String number = "00023";
		assertTrue(Validation.validateDecimal(number));
	}
}
