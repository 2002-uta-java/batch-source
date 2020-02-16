package com.revature.banking.services;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.frontend.models.BankAccount;
import com.revature.banking.models.EncryptedBankAccount;

public class BankAccountService extends EncryptedService {
	private BankAccountDao bad = null;

	public BankAccountService() {
		super();
	}

	public BankAccountService(final String dbKey) {
		super(dbKey);
	}

	public BankAccountService(final BankAccountDao bad) {
		super();
		this.setDao(bad);
	}

	public BankAccountService(final BankAccountDao bad, final String dbKey) {
		super(dbKey);
		setDao(bad);
	}

	public void setDao(final BankAccountDao bad) {
		this.bad = bad;
	}

	public BankAccount decrypt(final EncryptedBankAccount eba) {
		if (eba == null)
			return null;
		final BankAccount ba = new BankAccount();
		ba.setAccountNo(super.decrypt(eba.getAccountNo()));
		ba.setBalance(Double.parseDouble(super.decrypt(eba.getBalance())));
		return ba;
	}

	public EncryptedBankAccount encrypt(final BankAccount ba) {
		final EncryptedBankAccount eba = new EncryptedBankAccount();
		eba.setAccountNo(super.encrypt(ba.getAccountNo()));
		eba.setBalance(super.encrypt("" + ba.getBalance()));
		return eba;
	}
}
