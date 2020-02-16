package com.revature.banking.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.jasypt.util.text.StrongTextEncryptor;

public class WriteEncryptedStrings {
	public static final String[] strings = { "hello", "alsdkjfasd", "another", "somemore",
			"p129385uoirqwejfkvncxz 8ry4i" };
	public static final String FILENAME = "encryptiontest.txt";

	public static void main(String[] args) {
		final StrongTextEncryptor ste = new StrongTextEncryptor();

		ste.setPassword(System.getenv("DB_KEY"));

		try (PrintWriter pw = new PrintWriter(new File(FILENAME));) {

			for (final String string : strings) {
				pw.println(ste.encrypt(string));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
