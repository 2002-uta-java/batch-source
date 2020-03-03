package com.revature.jfbennatt.ers.services;

import java.util.Random;

import org.apache.log4j.Logger;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

import com.revature.jfbennatt.ers.daos.EmployeeDao;
import com.revature.jfbennatt.ers.models.Employee;

/**
 * Service layer that acts as an intermediary between the front end and the
 * database. The main functionality provided is encryption of the password and
 * session token.
 * 
 * <a href=
 * "http://www.jasypt.org/api/jasypt/1.9.3/org/jasypt/util/password/PasswordEncryptor.html">PasswordEncryptor</a>
 * and <a href=
 * "http://www.jasypt.org/api/jasypt/1.9.3/org/jasypt/util/text/StrongTextEncryptor.html">StrongTextEncryptor</a>
 * are used for encryption. This class will not be functional without the
 * environment variable DB_KEY.
 * 
 * @author Jared F Bennatt
 *
 */
public class EmployeeService {

	/**
	 * DAO used to communicate with the database.
	 */
	private EmployeeDao empDao;
	/**
	 * Password encryptor used to perform one-way encryption on the stored password.
	 */
	private final PasswordEncryptor passEnc = new StrongPasswordEncryptor();
	/**
	 * Random object used to generate random session tokens
	 */
	private final Random rand = new Random();
	/**
	 * Text encryptor used for encrypting and decrypting the session token.
	 */
	private final StrongTextEncryptor textEnc = new StrongTextEncryptor();

	/**
	 * Sets up text encryptor.
	 */
	public EmployeeService() {
		super();
		textEnc.setPassword(System.getenv("DB_KEY"));
	}

	/**
	 * Removes the session token for the user (specified by their current session
	 * token).
	 * 
	 * @param encryptedToken The encrypted token given by the client.
	 * @return Whether or not a session token was actually deleted.
	 */
	public boolean deleteSessionTokenByToken(String encryptedToken) {
		if (encryptedToken != null) {
			final String token = textEnc.decrypt(encryptedToken);
			return empDao.deleteSessionToken(token);
		}

		// there was no token to delete
		return false;
	}

	/**
	 * Generates a random session token based on the length expected by
	 * {@link EmployeeDao#getTokenLength()}. The token is an alphanumeric string.
	 * 
	 * @return An alphanumeric token of the length specified by the internal
	 *         {@link EmployeeDao}.
	 */
	private String generateToken() {
		final char[] token = new char[empDao.getTokenLength()];

		for (int i = 0; i < token.length; ++i) {
			token[i] = randomChar();
		}

		return new String(token);
	}

	/**
	 * Get/authenticate an employee from their (encrypted) session token. Although
	 * technically unnecessary, this method re-encrypts the unencrypted token from
	 * the database, so the the client should update their session token (because it
	 * will change&#8212;although the old encrypted token would still work unless
	 * the token is actually changed in the database).
	 * 
	 * @param encryptedToken This is the encrypted session token that the user
	 *                       provides (the token is not encrypted in the database).
	 * @return The authenticated {@link Employee} object (all fields will be set
	 *         except for the password) or <code>null</code> if the employee isn't
	 *         found. The token in the Employee object will be encrypted so that the
	 *         client cannot know the actual value of the token.
	 * @see EmployeeDao#getEmployeeByToken(String)
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
	 *         <code>null</code> if the employee doesn't exist or isn't
	 *         authenticated.
	 */
	public Employee loginEmployee(final String email, final String password) {
		final Employee emp = empDao.getEmployeeByEmail(email);

		Logger.getRootLogger().debug("Looking for employee by email: " + email);
		if (emp != null) {
			Logger.getRootLogger().debug("Found employee: " + emp);
			// we found an employee, authenticate their password
			if (passEnc.checkPassword(password, emp.getPassword())) {
				// generate a token for the user
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
		} else {
			Logger.getRootLogger().debug("emp was null");
		}
		// either the employee wasn't found, wasn't authenticated, or setting the token
		// failed, so return null signaling a failure to log in.
		return null;
	}

	/**
	 * Gets a random alphanumeric character.
	 * 
	 * @return A random alphanumeric character.
	 */
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

	/**
	 * Sets the {@link EmployeeDao} to be used by this service.
	 * 
	 * @param empDao {@link EmployeeDao} to be used by this service.
	 */
	public void setEmployeeDao(final EmployeeDao empDao) {
		this.empDao = empDao;
	}
}
