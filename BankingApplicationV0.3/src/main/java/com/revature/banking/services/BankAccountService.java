package com.revature.banking.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;
import com.revature.banking.services.security.models.EncryptedBankAccount;

public class BankAccountService extends Service {

	public static final double DEPOSIT_LIMIT = 10000.0;
	public static final int ADD_FUNDS_SUCCESS = 0;
	public static final int ADD_FUNDS_DEPOSIT_LIMIT = 1;
	public static final int ADD_FUNDS_FAILURE = 2;

	public static final int WITHDRAWAL_INSUFFICIENT_FUNDS = 0;
	public static final int WITHDRAWAL_SUCCESS = 1;
	public static final int WITHDRAWAL_FAILURE = 2;

	private final Random rand = new Random();
	private BankAccountDao baDao = null;

	public BankAccountService() {
		super();
	}

	public void setBankAccountDao(final BankAccountDao baDao) {
		this.baDao = baDao;
	}

	/**
	 * This creates a new account but <i>does not</i> add it to the database.
	 * 
	 * @return
	 */
	protected EncryptedBankAccount getNewAccount() {
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

	public List<BankAccount> getAccounts(User user) {
		final List<EncryptedBankAccount> encryptedAccounts = baDao.getAccountsByUserKey(user.getUserKey());
		final List<BankAccount> accounts = new ArrayList<BankAccount>();

		for (final EncryptedBankAccount eba : encryptedAccounts)
			accounts.add(secService.decrypt(eba));

		return accounts;
	}

	public boolean updateAccount(final BankAccount account) {
		final EncryptedBankAccount eba = secService.encrypt(account);
		return baDao.updateAccount(eba);
	}

	public int addFundsToAccount(BankAccount account, double amt) {
		if (amt > DEPOSIT_LIMIT)
			return ADD_FUNDS_DEPOSIT_LIMIT;

		// else add the amoun to the account and update account
		account.addFunds(amt);
		if (updateAccount(account)) {
			return ADD_FUNDS_SUCCESS;
		} else {
			// need to undo addFUnds
			account.withdraw(amt);
			return ADD_FUNDS_FAILURE;
		}

	}

	public int withdrawFromAccount(BankAccount account, double amount) {
		if (amount > account.getBalance())
			return WITHDRAWAL_INSUFFICIENT_FUNDS;

		// withdraw from account
		account.withdraw(amount);
		if (updateAccount(account)) {
			return WITHDRAWAL_SUCCESS;
		} else {
			// need to add back funds
			account.addFunds(amount);
			return WITHDRAWAL_FAILURE;
		}
	}

	public boolean addUserToAccount(final int userKey, final BankAccount account) {
		return baDao.addUserToAccount(userKey, secService.encrypt(account));
	}

	public BankAccount addAccountToUser(User user) {
		final EncryptedBankAccount eba = this.getNewAccount();
		if (baDao.createNewAccount(eba)) {
			// successfully created account
			// now add user to it
			if (baDao.addUserToAccount(user.getUserKey(), eba)) {
				return secService.decrypt(eba);
			} else {
				// attempt to delete newly created account
				baDao.deleteAccount(eba);
				return null;
			}
		} else
			return null;
	}

	public BankAccount createNewAccount() {
		final EncryptedBankAccount eba = getNewAccount();
		if (baDao.createNewAccount(eba)) {
			return secService.decrypt(eba);
		}
		// else it failed
		return null;
	}

	public boolean deleteAccount(BankAccount account) {
		return baDao.deleteAccount(secService.encrypt(account));
	}
}
