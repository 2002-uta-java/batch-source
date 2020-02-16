package com.revature.banking.services;

import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.revature.banking.dao.UserDao;
import com.revature.banking.frontend.models.BankAccount;
import com.revature.banking.frontend.models.User;
import com.revature.banking.models.EncryptedBankAccount;
import com.revature.banking.models.EncryptedUser;

public class UserService extends EncryptedService {
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
	 * This new user information is fine.
	 */
	public static final int CHECK_NEW_USER_PASS = 0;
	/**
	 * This new user's account already exists.
	 */
	public static final int CHECK_NEW_USER_ACCOUNT_EXISTS = 1;
	/**
	 * This new user's first and last don't match the existing tax id.
	 */
	public static final int CHECK_NEW_USER_TAXID_MISMATCH = 2;

	private UserDao ud = null;
	private final PasswordEncryptor pwEncryptor = new StrongPasswordEncryptor();
	private BankAccountService bas;

	public UserService() {
		super();
	}

	public UserService(final String dbKey) {
		super(dbKey);
	}

	public UserService(final UserDao ud) {
		super();
		this.setDao(ud);
	}

	public UserService(final UserDao ud, final String dbKey) {
		super(dbKey);
		this.setDao(ud);
	}

	public void setDao(final UserDao ud) {
		this.ud = ud;
	}

	public void setBankAccountService(final BankAccountService bas) {
		this.bas = bas;
	}

	/**
	 * Returns the user given their tax id.
	 * 
	 * @param taxId The TaxID object representing the tax id of a user.
	 * @return The user (as a User object) that exists in the db or null if the user
	 *         does not exist.
	 */
	public User getUserByTaxId(final String taxId) {
		final EncryptedUser eu = ud.getUserByTaxId(super.encrypt(taxId));

		return decryptUser(eu);
	}

	public User decryptUser(EncryptedUser eu) {
		if (eu == null)
			return null;
		// else decrypt user
		final User user = new User();
		user.setFirstName(super.decrypt(eu.getFirstName()));
		user.setLastName(super.decrypt(eu.getLastName()));
		user.setTaxId(super.decrypt(eu.getTaxId()));
		if (eu.getUsername() != null) {
			// if the user name exists, the password should also
			user.setUsername(super.decrypt(eu.getUsername()));
			user.setPassword(super.decrypt(eu.getEncryptedPassword()));
		}

		return user;
	}

	/**
	 * This method is intended to be used to check whether or not a new user is
	 * actually a new user <i>and</i> is a "valid" new user (e.g. an old user that
	 * closed an account, but their tax id matches the information on file). This
	 * method returns an integer value explaining the status of this new user's
	 * information.
	 * 
	 * @param user Checks whether the new user's taxid is consistent with the what's
	 *             already in the database.
	 * @return An integer value that explains whether or not this user's information
	 *         is valid and if not, why.
	 * @see {@link #CHECK_NEW_USER_PASS}, {@link #CHECK_NEW_USER_ACCOUNT_EXISTS},
	 *      {@link #CHECK_NEW_USER_TAXID_MISMATCH}
	 */
	public int checkNewUserTaxid(final User user) {
		final User checkUser = this.getUserByTaxId(user.getTaxId());

		// if the "found" user is null, they do not exist in this system. This is
		// definitely a new user.
		if (checkUser == null)
			return UserService.CHECK_NEW_USER_PASS;

		// else this user exists. They should not have an account. If the user deleted
		// their account then their user id should be null.
		if (checkUser.getUsername() != null)
			return UserService.CHECK_NEW_USER_ACCOUNT_EXISTS;

		// check the first and last name on file, they should match
		if (user.getFirstName().equalsIgnoreCase(checkUser.getFirstName())
				&& user.getLastName().equalsIgnoreCase(checkUser.getLastName()))
			return UserService.CHECK_NEW_USER_PASS;

		// else this didn't match what's already in our db
		return UserService.CHECK_NEW_USER_TAXID_MISMATCH;

	}

	public BankAccount createNewUser(User newUser) {
		final EncryptedUser eu = encryptUser(newUser);
		final EncryptedBankAccount eba = ud.createNewUser(eu);
		return bas.decrypt(eba);
	}

	public EncryptedUser encryptUser(User newUser) {
		final EncryptedUser eu = new EncryptedUser();
		eu.setFirstName(super.encrypt(newUser.getFirstName()));
		eu.setLastName(super.encrypt(newUser.getLastName()));
		eu.setTaxId(super.encrypt(newUser.getTaxId()));
		eu.setUsername(super.encrypt(newUser.getUsername()));
		eu.setEncryptedPassword(pwEncryptor.encryptPassword(newUser.getPassword()));

		return eu;
	}
}
