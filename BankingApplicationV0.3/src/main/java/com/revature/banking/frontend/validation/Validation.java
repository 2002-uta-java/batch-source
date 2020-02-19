package com.revature.banking.frontend.validation;

import java.util.regex.Pattern;

import com.revature.banking.services.UserService;

public class Validation {
	/**
	 * Taken from StackOverflow and slightly modified:
	 * https://stackoverflow.com/questions/1221985/how-to-validate-a-user-name-with-regex
	 * 
	 * I require the first character to be an alpha (non-numeric) and I only allow
	 * underscores (one at a time) in between, no dashes or spaces.
	 * 
	 * The ?: marks the parenthesis as a non-capturing group
	 * 
	 * This should require alphanumeric names, starting with an alphabetic
	 * character, no special symbols, and only underscores in between and must end
	 * in an alphanumeric character.
	 */
	public static final String USERNAME_REGEX = "^\\p{Alpha}+(?:_?\\p{Alnum}+)*$";
	private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);

	public static final String PASSWORD_SPECIAL = "@#$^%!&*";
	/**
	 * Taken from
	 * https://howtodoinjava.com/regex/how-to-build-regex-based-password-validator-in-java/
	 * 
	 * This should require a lower case letter, upper case letter, digit, and
	 * special character (specifically one of @, #, $, %, !, &amp;, *). And the
	 * length should be at least 6 but no more than 16.
	 */
	public static final String PASSWORD_REGEX = "(^(?=.*\\p{Lower})(?=.*\\p{Digit})(?=.*[" + PASSWORD_SPECIAL
			+ "])(?=.*\\p{Upper}).{6,16})$";
	public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

	public static final String TAXID_REGEX = "^\\d{10}$";
	public static final Pattern TAXID_PATTERN = Pattern.compile(TAXID_REGEX);

	public static final String NATURAL_NUMBER_REGEX = "^[1-9]\\d*$";
	public static final Pattern NATURAL_NUMBER_PATTERN = Pattern.compile(NATURAL_NUMBER_REGEX);

	/**
	 * Regex for decimals with at most two decimal places
	 */
	public static final String AMOUNT_REGEX = "^(\\d+(\\.\\d{1,2})?|\\.\\d{1,2})$";
	public static final Pattern AMOUNT_PATTERN = Pattern.compile(AMOUNT_REGEX);

	public static final String DECIMAL_REGEX = "^-?(\\d+(\\.\\d+)?|\\.\\d{1,2})$";
	public static final Pattern DECIMAL_PATTERN = Pattern.compile(DECIMAL_REGEX);

	public static final String ACCOUNT_NO_REGEX = "^\\d{10}$";
	public static final Pattern ACCOUN_NO_PATTERN = Pattern.compile(ACCOUNT_NO_REGEX);

	public static boolean validateUserName(final String username) {
		if (USERNAME_PATTERN.matcher(username).matches()) {
			final int length = username.length();
			return length >= UserService.USER_NAME_MIN_LENGTH && length <= UserService.USER_NAME_MAX_LENGTH;
		} else
			return false;
	}

	public static boolean validatePassword(final String password) {
		if (PASSWORD_PATTERN.matcher(password).matches()) {
			final int length = password.length();
			return length >= UserService.PASSWORD_MIN_LENGTH && length <= UserService.PASSWORD_MAX_LENGTH;
		} else
			return false;
	}

	public static boolean validateName(final String name) {
		final int length = name.length();
		return length != 0 && length <= UserService.NAME_LENGTH;
	}

	public static boolean validateTaxid(final String taxid) {
		return TAXID_PATTERN.matcher(taxid).matches();
	}

	public static boolean isNaturalNumber(String option) {
		return NATURAL_NUMBER_PATTERN.matcher(option).matches();
	}

	public static boolean validateAmount(String amountString) {
		return AMOUNT_PATTERN.matcher(amountString).matches();
	}

	public static boolean validateDecimal(final String decimal) {
		return DECIMAL_PATTERN.matcher(decimal).matches();
	}

	public static boolean validateAccountNo(final String accountNo) {
		return ACCOUN_NO_PATTERN.matcher(accountNo).matches();
	}
}