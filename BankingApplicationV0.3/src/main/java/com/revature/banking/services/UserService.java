package com.revature.banking.services;

import java.util.Set;

import com.revature.banking.dao.UserDao;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;
import com.revature.banking.services.security.models.EncryptedBankAccount;
import com.revature.banking.services.security.models.EncryptedUser;

public class UserService extends Service {
	/**
	 * Maximum length of user's first and last names (separately). This value should
	 * correspond with the value in UserDao.
	 */
	public static final int NAME_LENGTH = UserDao.NAME_LENGTH;
	/**
	 * Maximum length for a user's name. This value should correspond with the value
	 * in UserDao.
	 */
	public static final int USER_NAME_MAX_LENGTH = UserDao.USER_NAME_MAX_LENGTH;
	/**
	 * Minimum length for a user's name. This value should correspond with the value
	 * in UserDao.
	 */
	public static final int USER_NAME_MIN_LENGTH = UserDao.USER_NAME_MIN_LENGTH;
	/**
	 * Maximum length of user's password. This value should correspond with the
	 * value in UserDao.
	 */
	public static final int PASSWORD_MAX_LENGTH = UserDao.PASSWORD_MAX_LENGTH;
	/**
	 * Minimum length of user's password. This value should correspond with the
	 * value in UserDao.
	 */
	public static final int PASSWORD_MIN_LENGTH = UserDao.PASSWORD_MIN_LENGTH;
	/**
	 * Length of tax id. This value should correspond with the value in UserDao.
	 */
	public static final int TAXID_LENGTH = UserDao.TAXID_LENGTH;

	/**
	 * The new user is brand new to the system and new record should be created in
	 * the database.
	 */
	public static final int CHECK_NEW_USER_BRAND_NEW = 0;
	/**
	 * This "new user" has an open account (and should not be added).
	 */
	public static final int CHECK_NEW_USER_HAS_OPEN_ACCOUNT = 1;
	/**
	 * This new user's first and last don't match the existing tax id.
	 */
	public static final int CHECK_NEW_USER_TAXID_MISMATCH = 2;
	/**
	 * This "new user" is already in the system but has no open accounts so they can
	 * create a new login and attach it to a new account.
	 */
	public static final int CHECK_NEW_USER_VALID_ALREADY_EXISTS = 3;

	/**
	 * This means the passed tax id was invalid (the front end should really check
	 * this before ever calling this method.
	 */
	public static final int CHECK_NEW_USER_INVALID_TAX_ID = 4;

	public UserService() {
		super();
	}

	/**
	 * This method is intended to be used to check whether or not a new user is
	 * actually a new user <i>and</i> is a "valid" new user (e.g. not an old user
	 * that closed an account, but their tax id matches the information on file).
	 * This method returns an integer value explaining the status of this new user's
	 * information.
	 * 
	 * Note: if the user is already in the system but they have no open accounts,
	 * then they can re-open an account. In this case this method will set the
	 * user's user_key so that the next call to the database does not have to pull
	 * every user and search by tax id again.
	 * 
	 * @param user Checks whether the new user's taxid is consistent with what's
	 *             already in the database.
	 * @return An integer value that explains whether or not this user's information
	 *         is valid and if not, why.
	 * @see {@link #CHECK_NEW_USER_BRAND_NEW},
	 *      {@link #CHECK_NEW_USER_HAS_OPEN_ACCOUNT},
	 *      {@link #CHECK_NEW_USER_TAXID_MISMATCH},
	 *      {@link #CHECK_NEW_USER_VALID_ALREADY_EXISTS}
	 */
	public int checkNewUserTaxid(User newUser) {
		// pull entire list of users from database and search for a matching tax id
		final Set<EncryptedUser> currentUsers = uDao.getAllUsers();

		for (final EncryptedUser eUser : currentUsers) {
			final User user = secService.decrypt(eUser);
			if (newUser.getTaxID().equals(user.getTaxID())) {
				// check to make sure this user doesn't already have an account
				if (user.getUserName() != null) {
					return CHECK_NEW_USER_HAS_OPEN_ACCOUNT;
				}
				// check to make sure the first and last name match
				if (!user.getFirstName().equals(newUser.getFirstName())
						|| !user.getLastName().equals(newUser.getLastName())) {
					return CHECK_NEW_USER_TAXID_MISMATCH;
				}
				// at this point the new user exists in the system, does not have an open
				// account, AND the first and last name match our records. Give the user the
				// userKey so that they can access directly access this record (without having
				// to do this search again).
				newUser.setUserKey(user.getUserKey());
				return CHECK_NEW_USER_VALID_ALREADY_EXISTS;
			}
		}
		// we never found a matching tax id, so this is a brand spanking new user
		return CHECK_NEW_USER_BRAND_NEW;
	}

	/**
	 * This method will create a new user in the system and create a new bank
	 * account for them. It returns the new bank account object.
	 * 
	 * @param newUser User object holding the information required to create a new
	 *                user (first name, last name, tax id, user name, and password).
	 * @return The newly created BankAccount object associated with this user.
	 */
	public BankAccount createNewUser(User newUser) {
		final EncryptedUser eUser = secService.encrypt(newUser);
		final EncryptedBankAccount eba = uDao.createNewUser(eUser);
		return secService.decrypt(eba);
	}

	/**
	 * This updates a user already in the system and creates a new bank account for
	 * them. The newly created BankAccount is then returned.
	 * 
	 * @param updatedUser A User object holding the new information which should
	 *                    include the username, password, and user_key (the user_key
	 *                    is used to find the record in the database).
	 * @return A plain text BankAccount that has been created for the user.
	 */
	public BankAccount updateUserCreateNewAccount(User updatedUser) {
		final EncryptedUser eUser = secService.encrypt(updatedUser);
		final EncryptedBankAccount eba = uDao.updateUserCreateNewAccount(eUser);
		return secService.decrypt(eba);
	}
}
