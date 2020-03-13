package com.revature.banking.validation;

import java.util.regex.Pattern;

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
	 * This should require alphanumeric names, starting with an alphabetic character,
	 * no special symbols, and only underscores in between and must end in an
	 * alphanumeric character.
	 */
	public static final String USERNAME_REGEX = "^\\p{Alpha}+(?:_?\\p{Alnum}+)*$";
	private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);

	/**
	 * Taken from
	 * https://howtodoinjava.com/regex/how-to-build-regex-based-password-validator-in-java/
	 * 
	 * This should require a lower case letter, upper case letter, digit, and
	 * special character (specifically one of @, #, $, %, !, &, *). And the length
	 * should be at least 6 but no more than 16.
	 */
	public static final String PASSWORD_REGEX = "((?=.*\\p{Lower})(?=.*\\p{Digit})(?=.*[@#$^%!&*])(?=.*\\p{Upper}).{6,16})";
	public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

	private final Pattern userPattern, passwordPattern;

	public Validation() {
		this(USERNAME_PATTERN, PASSWORD_PATTERN);
	}

	/**
	 * Creates a Validation given the regex's.
	 * 
	 * @param userRegex     Regex for username creation
	 * @param passwordRegex Regex for password creation.
	 */
	public Validation(final String userRegex, final String passwordRegex) {
		this(Pattern.compile(userRegex), Pattern.compile(passwordRegex));
	}

	/**
	 * Creates a Validation object given the Pattern objects used for validation.
	 * 
	 * @param userPattern     Pattern used for user's validation.
	 * @param passwordPattern Pattern used for password's validation.
	 */
	public Validation(final Pattern userPattern, final Pattern passwordPattern) {
		this.userPattern = userPattern;
		this.passwordPattern = passwordPattern;
	}

	public boolean validateUserName(final String username) {
		return userPattern.matcher(username).matches();
	}

	public boolean validatePassword(final String password) {
		return passwordPattern.matcher(password).matches();
	}
}
