package com.revature.banking.services;

import com.revature.banking.dao.UserDao;

public class UserService {
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

	private UserDao ud = null;

	public UserService() {
		super();
	}

	public UserService(final UserDao ud) {
		super();
		this.setDao(ud);
	}

	public void setDao(final UserDao ud) {
		this.ud = ud;
	}

	public boolean checkTaxId(final User user) {
		// TODO
		return false;
	}
}
