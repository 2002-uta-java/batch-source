package com.revature.bank;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomBankAccountGenerator implements BankAccountGenerator {
	public static final int DEF_CAP = 100;
	public static final int DEF_DIGITS = 10;

	private final Set<String> accountSet;
	private final Random rand = new Random();
	private final int digits;
	private final StringBuilder sb;

	/**
	 * Default constructor which uses the default number of digits and the default
	 * capacity
	 */
	public RandomBankAccountGenerator() {
		this(DEF_DIGITS);
	}

	/**
	 * Constructor which specifies the number of digits for this account and uses
	 * the default capacity.
	 * 
	 * @param digits The number of digits for the generated account numbers.
	 */
	public RandomBankAccountGenerator(final int digits) {
		this(digits, DEF_CAP);
	}

	/**
	 * Constructor which specifies both the number of digits and the initial
	 * capacity.
	 * 
	 * @param digits The number of digits for the generated account numbers.
	 * @param cap    The initial capacity for the number of accounts that will be
	 *               generated.
	 */
	public RandomBankAccountGenerator(final int digits, final int cap) {
		this.digits = digits;
		this.accountSet = new HashSet<String>(cap);
		sb = new StringBuilder(digits);
	}

	@Override
	public synchronized String nextAccount() {
		// keeps generating a random account number until it finds one not already in
		// the set of account numbers.
		while (true) {
			final String nextAccount = getRandomAccount();

			// if the set doesn't contain nextAccount, add it to the set and return that
			// account string.
			if (!accountSet.contains(nextAccount)) {
				accountSet.add(nextAccount);
				return nextAccount;
			}
		}
	}

	private String getRandomAccount() {
		// clears the internal StingBuilder
		sb.replace(0, sb.length(), "");

		// adds a single digit at a time
		for (int i = 0; i < digits; ++i)
			sb.append(randomDigit());

		return sb.toString();
	}

	private char randomDigit() {
		// gets a random integer from [0, 10) (so 0-9) then adds to the '0' to get as a
		// char
		return (char) ('0' + rand.nextInt(10));
	}
}
