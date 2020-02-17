package com.revature.banking.services;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.security.models.EncryptedBankAccount;

public class BankAccountService extends Service {

	private final Random rand = new Random();
	private BankAccountDao baDao = null;

	public BankAccountService() {
		super();
	}

	public void setBankAccountDao(final BankAccountDao baDao) {
		this.baDao = baDao;
	}

	public EncryptedBankAccount createNewAccount() {
		// grab all account from database so that I can decrypt the account numbers and
		// generate a new one
		final Set<EncryptedBankAccount> encryptedAccounts = baDao.getAllAccounts();
		final Set<String> accountNos = new HashSet<String>(encryptedAccounts.size());

		// decrypt account numbers
		for (final EncryptedBankAccount eba : encryptedAccounts)
			accountNos.add(secService.decrypt(eba.getAccountNo()));

		// get a new account number
		final String newAccountNo = getNextAccountNo(accountNos);

		// create new bank account with 0 balance
		final BankAccount newAccount = new BankAccount();
		newAccount.setAccountNo(newAccountNo);
		newAccount.setBalance(0);

		// return encrypted bank account
		return secService.encrypt(newAccount);
	}

	private String getNextAccountNo(final Set<String> accountNos) {
		return getRandomAccountNo(accountNos);
	}

	private String getRandomAccountNo(Set<String> accountNos) {
		while (true) {
			final String newAccountNo = getRandomAccountNo();
			if (!accountNos.contains(newAccountNo))
				return newAccountNo;
		}
	}

	private String getRandomAccountNo() {
		final char[] accountNo = new char[BankAccountDao.BANK_ACCOUNT_NO_LENGTH];
		for (int i = 0; i < BankAccountDao.BANK_ACCOUNT_NO_LENGTH; ++i)
			accountNo[i] = (char) ('0' + rand.nextInt(10));

		return new String(accountNo);
	}
}
