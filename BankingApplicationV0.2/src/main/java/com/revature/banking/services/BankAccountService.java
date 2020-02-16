package com.revature.banking.services;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.frontend.models.BankAccount;
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
	public EncryptedBankAccount createNewAccount(final List<String> encryptedBankAccountNos) {
		final BankAccount ba = new BankAccount();
		final Set<String> decryptedBankAccountNos = decryptAccountNos(encryptedBankAccountNos);
		ba.setAccountNo(getUniqueAccountNo(decryptedBankAccountNos));
		ba.setBalance(0);

		return super.ss.encrypt(ba);
	}

	private String getUniqueAccountNo(Set<String> encryptedBankAccountNos) {
		while (true) {
			final String newNo = randomBankAccountNo();
			if (!encryptedBankAccountNos.contains(newNo))
				return newNo;
		}
	}

	private String randomBankAccountNo() {
		final char[] string = new char[BANK_ACCOUNT_NO_LENGTH];
		for (int i = 0; i < BANK_ACCOUNT_NO_LENGTH; ++i)
			string[i] = (char) ('0' + rand.nextInt(10));

		return new String(string);
	}

	private Set<String> decryptAccountNos(List<String> encryptedBankAccountNos) {
		final Set<String> decrypted = new HashSet<>(encryptedBankAccountNos.size());
		for (final String encrypted : encryptedBankAccountNos) {
			decrypted.add(super.ss.decrypt(encrypted));
		}

		return decrypted;
	}

}
