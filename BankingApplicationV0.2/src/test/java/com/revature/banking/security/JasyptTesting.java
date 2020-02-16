package com.revature.banking.security;

import java.util.Random;

import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;

/**
 * I'm using this class to test out the functionality of Jasypt. Namely, I'm
 * trying to figure out how long the encrypted strings will be.
 * 
 * @author bennattj
 *
 */
public class JasyptTesting {
	private static final Random rand = new Random();

	public static void main(String[] args) {
		System.out.println("Testing password encryption.");
		for (int i = 0; i < 10; ++i) {
			final String password = getRandomString(20);
			final PasswordEncryptor encryptor = new StrongPasswordEncryptor();
			final String encrypted = encryptor.encryptPassword(password);
			final PasswordEncryptor decryptor = new StrongPasswordEncryptor();

//			System.out.println("password: " + password + " (" + password.length() + ")");
//			System.out.println("encrypted: " + encrypted + " (" + encrypted.length() + ")");
			System.out.println("matches? " + decryptor.checkPassword(password, encrypted));
		}

		System.out.println();
		System.out.println();
		System.out.println("Testing text encryption: ");

		for (int i = 0; i < 10; ++i) {
			final String text = getRandomString(20);
			final String key = getRandomString(1000);
			final StrongTextEncryptor encryptor = new StrongTextEncryptor();
			encryptor.setPassword(key);
			final String encrypted = encryptor.encrypt(text);
			final String decrypted = encryptor.decrypt(encrypted);
			final StrongTextEncryptor decryptor = new StrongTextEncryptor();
			decryptor.setPasswordCharArray(key.toCharArray());

//			System.out.println("text: " + text + " (" + text.length() + ")");
//			System.out.println("encrypted: " + encrypted + " (" + encrypted.length() + ")");
//			System.out.println("decrypted: " + decrypted + ", matched? " + text.equals(decrypted));
//			System.out.println(encrypted.length());
			System.out.println(text.equals(decryptor.decrypt(encrypted)));
		}

		System.out.println(getRandomString(20));
	}

	public static String getRandomString(final int length) {
		final char[] string = new char[1 + rand.nextInt(length)];
		for (int i = 0; i < string.length; ++i)
			string[i] = getRandomChar();

		return new String(string);
	}

	public static char getRandomChar() {
		switch (rand.nextInt(3)) {
		case 0:
			return (char) ('a' + rand.nextInt(26));
		case 1:
			return (char) ('A' + rand.nextInt(26));
		default:
			return (char) ('0' + rand.nextInt(10));
		}
	}
}
