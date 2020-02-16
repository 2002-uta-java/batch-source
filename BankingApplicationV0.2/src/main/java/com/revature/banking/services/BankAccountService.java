package com.revature.banking.services;

import com.revature.banking.dao.BankAccountDao;

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
}
