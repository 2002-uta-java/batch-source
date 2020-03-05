package com.revature.services;

import java.util.List;

import com.revature.daos.BankAccountDao;
import com.revature.daos.BankAccountDaoImpl;
import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public class BankAccountService {
	
	private BankAccountDao bADao = new BankAccountDaoImpl();
	
	public BankAccount createBankAccount(BankAccount ba) {
		return bADao.createBankAccount(ba);
	}
	
	public List<BankAccount> getAllBankAccounts(UserAccount ua) {
		return bADao.getAllBankAccounts(ua);
	}

	public BankAccount getBankAccountById(int baid) {
		return bADao.getBankAccountById(baid);
	}
	
	public int updateBankAccount(BankAccount ba) {
		return bADao.updateBankAccountBalance(ba);
	}
}
