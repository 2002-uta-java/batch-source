package com.revature.jfbennatt.passwords;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordGen {
	public static void main(String[] args) {
		final PasswordEncryptor encryptor = new StrongPasswordEncryptor();
		try (final Scanner scanner = new Scanner(System.in)) {
			final String password = scanner.nextLine();
			Logger.getRootLogger().debug(encryptor.encryptPassword(password));
		}
	}
}
