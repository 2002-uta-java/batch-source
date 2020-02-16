package com.revature.banking.dao;

import com.revature.banking.frontend.models.User;
import com.revature.banking.services.models.EncryptedUser;

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

	/**
	 * Returns the user given by their tax id. If the user exists, this should, at a
	 * minimum, give the tax id, first name, and last name. It does <i>not</i>
	 * populated the bank accounts. If the user has an account, it should also
	 * return the username and encrypted password.
	 * 
	 * @param taxId unique tax id of a user.
	 * @return The user, given, the taxId, or null if the user does not exist in
	 *         this system.
	 */
	public EncryptedUser getUserByTaxId(String encryptedTaxId);
}
