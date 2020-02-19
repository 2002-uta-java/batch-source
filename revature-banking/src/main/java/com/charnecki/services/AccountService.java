package com.charnecki.services;

import java.util.List;

import com.charnecki.daos.AccountDao;
import com.charnecki.daos.AccountDaoImpl;
import com.charnecki.models.Account;


public class AccountService {
	
	private AccountDao ad = new AccountDaoImpl();
	
	public List<Account> getAllAccounts() {
		return ad.getAllAccounts();
	}
	
	public Account getAccountById(int id) {
		return ad.getAccountById(id);
	}
	
	public int createAccount(Account a) {
		int newId = ad.createAccount(a);
		a.setId(newId);
		return newId;
	}
	
	public boolean updateAccount(Account a) {
		int affectedRows = ad.updateAccount(a);
		if (affectedRows == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteAccount(Account a) {
		int affectedRows = ad.deleteAccount(a);
		if (affectedRows == 1) {
			return true;
		}
		return false;
	}
}
