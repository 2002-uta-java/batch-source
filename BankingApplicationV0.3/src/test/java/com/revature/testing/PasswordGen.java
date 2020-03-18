package com.revature.testing;

public class PasswordGen {
	public static void main(String[] args) {
		System.out.println(genPassword(15));
	}

	public static String genPassword(final int len) {
		final char[] pass = new char[len];

		for (int i = 0; i < len; ++i)
			pass[i] = randomChar();

		return new String(pass);
	}

	public static char randomChar() {
		final int select = (int) (Math.round(Math.random() * 94));

		return (char) ('!' + select);
	}
}
