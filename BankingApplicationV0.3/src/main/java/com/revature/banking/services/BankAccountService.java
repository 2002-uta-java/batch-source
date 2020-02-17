package com.revature.banking.services;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.services.security.models.EncryptedBankAccount;

public class BankAccountService extends Service {

	private BankAccountDao baDao = null;

	public BankAccountService() {
		super();
	}

	public void setBankAccountDao(final BankAccountDao baDao) {
		this.baDao = baDao;
	}

	public EncryptedBankAccount createNewAccount() {
		// TODO Auto-generated method stub
		return null;
	}
}
