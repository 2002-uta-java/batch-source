package com.revature.banking.dao;

public interface UserDao {
	/**
	 * Maximum length of user's first and last names (separately).
	 */
	public static final int NAME_LENGTH = 20;
	/**
	 * Maximum length for a user's name.
	 */
	public static final int USER_NAME_MAX_LENGTH = 16;
	/**
	 * Minimum length for a user's name.
	 */
	public static final int USER_NAME_MIN_LENGTH = 4;
	/**
	 * Maximum length of user's password.
	 */
	public static final int PASSWORD_MAX_LENGTH = 16;
	/**
	 * Minimum length of user's password.
	 */
	public static final int PASSWORD_MIN_LENGTH = 6;
	/**
	 * Length of tax id.
	 */
	public static final int TAXID_LENGTH = 10;
}
