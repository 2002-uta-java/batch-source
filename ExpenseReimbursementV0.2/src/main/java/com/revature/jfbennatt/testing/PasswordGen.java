package com.revature.jfbennatt.testing;

import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordGen {
	public static final PasswordEncryptor passEnc = new StrongPasswordEncryptor();

	public static void main(String[] args) {
		System.out.println(passEnc.encryptPassword("ddddDDDD1!"));
	}
}
