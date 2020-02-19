package com.revature.banking.services;

import java.util.List;

import org.apache.log4j.Logger;

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
	 * This "new user"'s username already existed so they need to choose another one
	 */
	public static final int CHECK_NEW_USER_INVALID_USERNAME_EXISTS = 4;

	private BankAccountService baService = null;
	private UserDao uDao = null;

	public UserService() {
		super();
	}

	public void setUserDao(final UserDao uDao) {
		this.uDao = uDao;
	}

	public void setBankAccountService(final BankAccountService baService) {
		this.baService = baService;
	}

	/**
	 * This method is intended to be used to check whether or not a new user is
	 * actually a new user <i>and</i> is a "valid" new user (e.g. not an old user
	 * that closed an account, but their tax id matches the information on file).
	 * This method returns an integer value explaining the status of this new user's
	 * information.
	 * 
	 * This method simultaneously checks whether or not the proposed user name is
	 * not already in the database.
	 * 
	 * Note: if the user is already in the system but they have no open accounts,
	 * then they can re-open an account. In this case this method will set the
	 * user's user_key so that the next call to the database does not have to pull
	 * every user and search by tax id again.
	 * 
	 * @param newUser Checks whether the new user's taxid is consistent with what's
	 *                already in the database <i>and</i> that the proposed user name
	 *                is not already taken.
	 * @return An integer value that explains whether or not this user's information
	 *         is valid and if not, why.
	 * @see #CHECK_NEW_USER_BRAND_NEW
	 * @see #CHECK_NEW_USER_VALID_ALREADY_EXISTS
	 * @see #CHECK_NEW_USER_HAS_OPEN_ACCOUNT
	 * @see #CHECK_NEW_USER_INVALID_USERNAME_EXISTS
	 * @see #CHECK_NEW_USER_TAXID_MISMATCH
	 */
	public int checkNewUserTaxidAndUserName(User newUser) {
		// TODO need to combine this with username to make sure username isn't already
		// taken

		// pull entire list of users from database and search for a matching tax id
		final List<EncryptedUser> currentUsers = uDao.getAllUsers();
		final int numCurrUsers = currentUsers.size();

		for (int i = 0; i < numCurrUsers; ++i) {
			final EncryptedUser eUser = currentUsers.get(i);
			final User user = secService.decrypt(eUser);

			// first check tax id (if that doesn't match, then check username)
			if (newUser.getTaxID().equals(user.getTaxID())) {
				// check name match first (don't tell them the ID they entered has an account.
				// check to make sure the first and last name match
				if (!user.getFirstName().equals(newUser.getFirstName())
						|| !user.getLastName().equals(newUser.getLastName())) {
					Logger.getRootLogger().debug("Found matching tax id, but the name given didn't match");
					return CHECK_NEW_USER_TAXID_MISMATCH;
				}
				// check to make sure this user doesn't already have an account
				if (user.getUserName() != null) {
					Logger.getRootLogger().debug("Found matching tax id and with an open account");
					return CHECK_NEW_USER_HAS_OPEN_ACCOUNT;
				}
				// at this point the new user exists in the system, does not have an open
				// account, AND the first and last name match our records. Give the user the
				// userKey so that they can access directly access this record (without having
				// to do this search again).
				newUser.setUserKey(user.getUserKey());
				Logger.getRootLogger().debug("Found matching tax id and user did not have an open account");

				// Need to check the rest of the users to make sure this username isn't taken
				for (++i; i < numCurrUsers; ++i) {
					final String username = currentUsers.get(i).getUserName();
					if (newUser.getUserName().equals(username)) {
						Logger.getRootLogger().debug("Found matching tax id but also found a duplicate username");
						return CHECK_NEW_USER_INVALID_USERNAME_EXISTS;
					}
				}
				// checked the rest of the usernames, so the proposed username is fine

				return CHECK_NEW_USER_VALID_ALREADY_EXISTS;
			} else {
				// the tax id didn't match. Make sure the username doesn't also
				if (newUser.getUserName().equals(user.getUserName())) {
					Logger.getRootLogger().debug("Found a duplicate username");
					return CHECK_NEW_USER_INVALID_USERNAME_EXISTS;
				}
			}
		}
		// checked every taxid and username

		Logger.getRootLogger().debug(
				"no match was found for taxid or username: " + newUser.getTaxID() + " and " + newUser.getUserName());
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
	public BankAccount createNewUserAndAccount(User newUser) {
		final EncryptedUser eUser = secService.encrypt(newUser);
		final EncryptedBankAccount eba = baService.getNewAccount();

		if (uDao.createNewUserAndAccount(eUser, eba))
			return secService.decrypt(eba);
		// else it failed, return null
		return null;
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
		final EncryptedBankAccount eba = baService.getNewAccount();
		if (uDao.updateUserCreateNewAccount(eUser, eba))
			return secService.decrypt(eba);
		// else it failed, return null
		return null;
	}

	public User getUserByUserName(final String username) {
		final EncryptedUser eUser = uDao.getUserByUserName(username);
		if (eUser != null)
			return secService.decrypt(eUser);
		// else return null, user doesn't exist
		return null;
	}

	public User login(String username, String password) {
		final EncryptedUser eUser = uDao.getUserByUserName(username);
		if (eUser != null) {
			if (secService.checkPassword(password, eUser.getPassword())) {
				return secService.decrypt(eUser);
			}
		}
		// else either the username doesn't exist or the password didn't match (don't
		// tell the user which one it was)
		return null;
	}

	public boolean updateUser(User newUser) {
		return uDao.updateUser(secService.encrypt(newUser));
	}

	public boolean createUser(User newUser) {
		final EncryptedUser eUser = secService.encrypt(newUser);
		if (uDao.createNewUser(eUser)) {
			newUser.setUserKey(eUser.getUserKey());
			return true;
		}
		return false;
	}

	public boolean deleteUser(User newUser) {
		return uDao.deleteUser(secService.encrypt(newUser));
	}
}
