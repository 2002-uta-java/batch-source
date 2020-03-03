package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.Account;

public class AccountService {
	
	private AccountDao ad = new AccountDaoImpl();
	
	public Account getAccountByEmailAndPassword(String email, String password) {
		
		Account a = ad.getAccountByEmail(email);
		
		if (a != null && a.getPassword().equals(password)) {
			return a;
		}
		
		return null;
	}
	
	public List<Account> getAllAccounts() {
		return ad.getAllAccounts();
	}
	
	public Account getAccountById(int id) {
		return ad.getAccountById(id);
	}

}
