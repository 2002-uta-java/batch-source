package com.revature.service;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.model.Account;

public class AccountService {

	private AccountDao accountDao = new AccountDaoImpl();
	
	public Account newAccount(Account a) {
		return accountDao.createAccountByFunction(a);
	}
	
	public int getAccountNumber (int num) {
		return accountDao.getAccountNumber(num);
	}

	public Account withdrawFunds(int a, double b) {
		return accountDao.updateAccountByFunctionSub(a,b);
	}
	
	public double getBalance(int num) {
		return accountDao.getAccountBalance(num);
		
	}
	
	public Account depositFunds(int a, double b) {
		return accountDao.updateAccountByFunctionAdd(a,b);
	}
}