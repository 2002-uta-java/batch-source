package com.revature.jfbennatt.passwords;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * This is a helper class used to generate encrypted passwords (for testing).
 * 
 * @author Jared F Bennatt
 *
 */
public class PasswordGen {
	/**
	 * Reads a single line of text (the raw password) and logs the encrypted
	 * password.
	 * 
	 * @param args does not take any arguments.
	 */
	public static void main(String[] args) {
		final PasswordEncryptor encryptor = new StrongPasswordEncryptor();
		try (final Scanner scanner = new Scanner(System.in)) {
			final String password = scanner.nextLine();
			Logger.getRootLogger().debug(encryptor.encryptPassword(password));
		}
	}
}
