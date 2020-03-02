package com.revature.jfbennatt.ers.services;

import java.util.Random;

import org.apache.log4j.Logger;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

import com.revature.jfbennatt.ers.daos.EmployeeDao;
import com.revature.jfbennatt.ers.models.Employee;

public class EmployeeService {

	private EmployeeDao empDao;
	private final PasswordEncryptor passEnc = new StrongPasswordEncryptor();
	private final Random rand = new Random();
	private final StrongTextEncryptor textEnc = new StrongTextEncryptor();

	public EmployeeService() {
		textEnc.setPassword(System.getenv("DB_KEY"));
	}

	public void setEmployeeDao(final EmployeeDao empDao) {
		this.empDao = empDao;
	}

	/**
	 * Get/authenticate an employee from their (encrypted) session token.
	 * 
	 * @param encryptedToken This is the encrypted session token that the user
	 *                       provides (the token is not encrypted in the database).
	 * @return The authenticated Employee object (all fields will be set except for
	 *         the password). The token in the Employee object will be encrypted so
	 *         that the client cannot know the actual value of the token.
	 */
	public Employee getEmployeeByToken(final String encryptedToken) {
		if (encryptedToken != null) {
			try {
				final String token = textEnc.decrypt(encryptedToken);
				final Employee emp = empDao.getEmployeeByToken(token);
				// the dao gives an un-encrypted token, need to re-encrypt it (this will change
				// what the client has locally)
				if (emp != null) {
					emp.setToken(textEnc.encrypt(emp.getToken()));
					return emp;
				}
			} catch (EncryptionOperationNotPossibleException eonpe) {
				Logger.getRootLogger().error("There was a bad encrypted token: " + encryptedToken);
				System.out.println("There was a bad encrypted token: " + encryptedToken);
				return null;
			}
		}
		// else either no token was given or the token didn't match a value in the
		// database, so return null
		return null;
	}

	/**
	 * Attempts to login an employee. This will return an {@link Employee} object
	 * that will have (at least) the employee id, session token, and first and last
	 * name set.
	 * 
	 * @param email    Email of the employee trying to log in.
	 * @param password Password of the Employee trying to log in.
	 * @return An {@link Employee} object representing the logged in employee or
	 *         null if the employee doesn't exist or isn't authenticated.
	 */
	public Employee loginEmployee(final String email, final String password) {
		final Employee emp = empDao.getEmployeeByEmail(email);

		Logger.getRootLogger().debug("Looking for employee by email: " + email);
		System.out.println("Looking for employee by email: " + email);
		if (emp != null) {
			Logger.getRootLogger().debug("Found employee: " + emp);
			System.out.println("Found employee: " + emp);
			// we found an employee, authenticate their password
			if (passEnc.checkPassword(password, emp.getPassword())) {
				final String token = generateToken();

				// attempt to set the session token in the database
				if (empDao.setTokenById(emp.getEmpId(), token)) {
					final String encryptedToken = textEnc.encrypt(token);
					Logger.getRootLogger().debug("Setting employee's token: " + token + ", " + encryptedToken);
					// the token was set, so update the found employee's token (encrypted)
					emp.setToken(encryptedToken);
					return emp;
				}
			}
			Logger.getRootLogger().debug("Password didn't match: " + password + " vs. " + emp.getPassword());
			System.out.println("Password didn't match: " + password + " vs. " + emp.getPassword());
		} else {
			Logger.getRootLogger().debug("emp was null");
			System.out.println("emp was null");
		}
		// either the employee wasn't found, wasn't authenticated, or setting the token
		// failed, so return null signaling a failure to log in.
		return null;
	}

	private String generateToken() {
		final char[] token = new char[empDao.getTokenLength()];

		for (int i = 0; i < token.length; ++i) {
			token[i] = randomChar();
		}

		return new String(token);
	}

	private char randomChar() {
		// there are 26 alpha characters and 10 digits, so there are 2*26 + 10 = 62
		// possible characters. This value is used to determine which character to use.
		final int c = rand.nextInt(62);

		if (c < 26) {
			// return a lower case letter
			return (char) ('a' + c);
		} else if (c < 52) {
			// return an upper case letter
			return (char) ('A' + (c - 26));
		} else {
			// return a digit
			return (char) ('0' + (c - 52));
		}
	}

	public boolean deleteSessionToken(String encryptedToken) {
		if (encryptedToken != null) {
			final String token = textEnc.decrypt(encryptedToken);
			return empDao.deleteSessionToken(token);
		}

		// there was no token to delete
		return false;
	}
}
