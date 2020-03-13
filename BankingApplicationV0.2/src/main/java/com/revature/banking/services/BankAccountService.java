package com.revature.banking.services;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.models.BankAccount;
import com.revature.banking.models.EncryptedBankAccount;

public class BankAccountService extends Service {
	public static final int BANK_ACCOUNT_NO_LENGTH = 10;

	private BankAccountDao bad = null;
	private final Random rand = new Random();

	public BankAccountService() {
		super();
	}

	public BankAccountService(final BankAccountDao bad) {
		super();
		this.setDao(bad);
	}

	public void setDao(final BankAccountDao bad) {
		this.bad = bad;
	}

	/**
	 * This is a method that the Dao uses to get a new account (this method makes
	 * sure bank account numbers are unique).
	 * 
	 * @param encryptedBankAccountNos List of current bank accounts (needed to make
	 *                                sure new account number is unique).
	 * @return Returns an EncryptedBankAccount that is read to be updloaded to db by
	 *         dao.
	 */
	public BankAccount createNewAccount(final Set<String> bankAccountNos) {
		final BankAccount ba = new BankAccount();
		ba.setAccountNo(getUniqueAccountNo(bankAccountNos));
		ba.setBalance(0);

		return ba;
	}

	private String getUniqueAccountNo(Set<String> bankAccountNos) {
		while (true) {
			final String newNo = randomBankAccountNo();
			if (!bankAccountNos.contains(newNo))
				return newNo;
		}
	}

	private String randomBankAccountNo() {
		final char[] string = new char[BANK_ACCOUNT_NO_LENGTH];
		for (int i = 0; i < BANK_ACCOUNT_NO_LENGTH; ++i)
			string[i] = (char) ('0' + rand.nextInt(10));

		return new String(string);
	}

}
