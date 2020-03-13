package com.revature.bank.testing;

import java.text.NumberFormat;
import com.revature.bank.Bank;

public class CurrencyTesting {
	public static void main(String[] args) {
		final double v1 = 10;
		final double v2 = 10.234;
		final int v3 = 20;
		final long v4 = 200l;

		final NumberFormat format = NumberFormat.getCurrencyInstance(Bank.DEF_LOCALE);

		System.out.println("v1 = " + format.format(v1));
		System.out.println("v2 = " + format.format(v2));
		System.out.println("v3 = " + format.format(v3));
		System.out.println("v4 = " + format.format(v4));
	}
}
