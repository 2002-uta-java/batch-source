package com.revature.services;

import java.util.Random;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.revature.jfbennatt.reimbursement.dao.EmployeeDao;
import com.revature.jfbennatt.reimbursement.dao.models.Employee;

public class ReimbursementService {

	private final Random rand = new Random();
	private EmployeeDao eDao;
	private final PasswordEncryptor passEnc = new StrongPasswordEncryptor();

	public void setEmployeeDao(final EmployeeDao eDao) {
		this.eDao = eDao;
	}

	/**
	 * Attempts to login the user. Returns a session token that can be used by the
	 * client to continue accessing this user's information in the database.
	 * 
	 * @param email    Email of user attempting to log in.
	 * @param password password of user attempting to log in.
	 * @return A session token that can be used to communicate with the database
	 *         from here on out. If the email and password don't match, then null is
	 *         returned signaling the login attempt failed.
	 */
	public String login(String email, String password) {
		final Employee emp = eDao.getEmployee(email);

		// check this employee's password
		if (passEnc.checkPassword(password, emp.getPassword())) {
			// everything checked out, send back a session token
			return createSessionId();
		}
		// else, password didn't match, send back null
		return null;
	}

	private String createSessionId() {
		final char[] sessionId = new char[EmployeeDao.SESSION_ID_LENGTH];

		for (int i = 0; i < EmployeeDao.SESSION_ID_LENGTH; ++i)
			sessionId[i] = randomChar();

		return new String(sessionId);
	}

	private char randomChar() {
		// generate random lower case, upper case, or number
		switch (rand.nextInt(3)) {
		case 0:
			return (char) ('a' + rand.nextInt(26));
		case 1:
			return (char) ('A' + rand.nextInt(26));
		case 2:
			return (char) ('0' + rand.nextInt(10));
		}

		Logger.getRootLogger().error("Random number generator created a number not, 0, 1, or 2");
		throw new RuntimeException("Random number generator created a number not, 0, 1, or 2");
	}

	public static String getValue(final String name, final Cookie[] cookies) {
		for (final Cookie cookie : cookies) {
			if (cookie.getName().equals(name))
				return cookie.getValue();
		}
		Logger.getRootLogger().error(name + " property wasn't found in cookies");

		return null;
	}

}
