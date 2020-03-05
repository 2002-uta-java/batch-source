package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.Account;

public class AccountService {
	
	private Logger log = Logger.getRootLogger();
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

	public boolean updateUser(int id, String name, String email, String password) {
		
		Account a = ad.getAccountById(id);
		
		a.setName(name);
		a.setEmail(email);
		
		if (!"".equals(password) && !"********".equals(password) && !a.getPassword().equals(password)) {
			a.setPassword(password);			
		}
		
		log.info(a);
		
		return ad.updateAccount(a) > 0;
	}

}
