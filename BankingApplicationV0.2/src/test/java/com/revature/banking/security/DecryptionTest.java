package com.revature.banking.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jasypt.util.text.StrongTextEncryptor;

public class DecryptionTest {
	public static final String FILENAME = WriteEncryptedStrings.FILENAME;

	public static void main(String[] args) throws IOException {
		final StrongTextEncryptor encryptor = new StrongTextEncryptor();
		encryptor.setPassword(System.getenv("DB_KEY"));

		final BufferedReader br = new BufferedReader(new FileReader(new File(FILENAME)));
		String line = null;

		while ((line = br.readLine()) != null) {
			System.out.println(encryptor.decrypt(line));
		}
	}
}
