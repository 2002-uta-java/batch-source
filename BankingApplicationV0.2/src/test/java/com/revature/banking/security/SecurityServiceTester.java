package com.revature.banking.security;

public class SecurityServiceTester {
	public static void main(String[] args) {
		final SecurityService ss = new SecurityService();

		final String jared = "Jared";
		ss.setKey(System.getenv("DB_KEY"));

		System.out.println(ss.encrypt(jared));
	}
}
