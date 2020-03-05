package com.project0.services;

import java.util.List;

import com.project0.daos.AccountDao;
import com.project0.daos.AccountDaoImpl;
import com.project0.models.Account;

public class AccountService {
	private AccountDao accountDao = new AccountDaoImpl();
	
	public List<Account> getAllAccounts(){
		return accountDao.getAccounts();
	}
	
	public Account getAccountByAccId(int id) {
		return accountDao.getAccountByAccId(id);
	}
	
	public Account getAccountByCustId(int id) {
		return accountDao.getAccountByCustId(id);
	}
	
	public Account createAccount(Account a) {
		return accountDao.createAccountWithFunction(a);
	}
	
	public boolean updateAccount(Account a) {
		int accUpdated = accountDao.updateAccount(a);
		if (accUpdated !=0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteAccount(Account a) {
		int accDeleted = accountDao.deleteAccount(a);
		if (accDeleted !=0) {
			return true;
		}
		return false;
	}
}
