package com.revature.banking.services;

import com.revature.banking.dao.BankAccountDao;

public class BankAccountService {
	private BankAccountDao bad = null;

	public BankAccountService() {
		super();
	}

	public BankAccountService(final BankAccountDao bad) {
		super();
		setDao(bad);
	}

	public void setDao(final BankAccountDao bad) {
		this.bad = bad;
	}
}
