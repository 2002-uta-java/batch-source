package com.revature.bank.testing;

import com.revature.bank.application.BankApplication;

public class CurrencyTesting {
	public static void main(String[] args) {
		final double v1 = 10;
		final double v2 = 10.234;
		final int v3 = 20;
		final long v4 = 200l;

		System.out.println("v1 = " + BankApplication.CURRENCY_FORMAT.format(v1));
		System.out.println("v2 = " + BankApplication.CURRENCY_FORMAT.format(v2));
		System.out.println("v3 = " + BankApplication.CURRENCY_FORMAT.format(v3));
		System.out.println("v4 = " + BankApplication.CURRENCY_FORMAT.format(v4));
	}
}
