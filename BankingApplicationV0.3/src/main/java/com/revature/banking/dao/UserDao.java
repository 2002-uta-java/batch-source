package com.revature.banking.dao;

import java.util.List;
import java.util.Set;

import com.revature.banking.services.security.models.EncryptedBankAccount;
import com.revature.banking.services.security.models.EncryptedUser;

/**
 * Interface for interacting with data. This is intended to be used by the
 * service layer.
 * 
 * @author Jared F Bennatt
 *
 */
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
	 * Sets the {@link BankAccountDao} to be uses by this object.
	 * 
	 * @param baDao {@link BankAccountDao} to be used by this object.
	 */
	public void setBankAccountDao(final BankAccountDao baDao);

	/**
	 * Returns a set of all (encrypted) user records.
	 * 
	 * @return {@link Set} of all (encrypted) user records.
	 */
	public List<EncryptedUser> getAllUsers();

	/**
	 * This creates a new user in the database and creates (and links) the given
	 * bank account to their user account.
	 * 
	 * @param eUser The user record to be created (should contain, at least the
	 *              first name, last name, tax id, user name, and password).
	 * @param eba   Bank Account information to be added to the database.
	 * @return Wether or not this action was successful.
	 */
	public boolean createNewUser(final EncryptedUser eUser, final EncryptedBankAccount eba);

	/**
	 * This is intended to be used when a User already exists but has no open
	 * accounts. This method will update the user's record (which means eUser must
	 * contain a valid user_key) and create a new bank account for them.
	 * 
	 * @param eUser The user record to be updated (should contain every field:
	 *              user_key, first name, last name, tax id, user name, and
	 *              password).
	 * @param eba   The new account to be added and linked to this user.
	 * @return Whether or not this was successful.
	 */
	public boolean updateUserCreateNewAccount(final EncryptedUser eUser, final EncryptedBankAccount eba);

	/**
	 * Completely deletes user from the system. This deletes the record from the
	 * database.
	 * 
	 * @param eUser User to be deleted. This needs to have, at least, the user_key
	 *              set.
	 * @return Whether or not this action was successful.
	 * @see {@link #removeUser(EncryptedUser)}
	 */
	public boolean deleteUser(final EncryptedUser eUser);

	/**
	 * This removes the user from the system. This does <i>not</i> remove the user's
	 * record, it only invalidates their login (by deleting the username and
	 * password).
	 * 
	 * @param eUser User to be removed. This needs to have, at least, the user_key
	 *              set.
	 * @return Whether or not this action was successful.
	 * @see {@link #deleteUser(EncryptedUser)}
	 */
	public boolean removeUser(final EncryptedUser eUser);

	/**
	 * Retrieves the information for this user from their username (which should be
	 * unique). The returned user needs to set (at least) the user_key and the
	 * password (so that it can be checked). The other fields are not necessary.
	 * 
	 * @param username Unique username of user trying to log in.
	 * @return An {@link EncryptedUser} object of the found record or null if no
	 *         such record exists.
	 */
	public EncryptedUser getUserByUserName(String username);
}
