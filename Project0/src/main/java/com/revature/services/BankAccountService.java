package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public class BankAccountService {

	public void bankAccountMenu(String username) {
		
	}
	
	
	public boolean checkUsernameUnique(String username) {;
		AccountDao accountDao = new AccountDaoImpl();
		
		List<UserAccount> userAccounts = accountDao.getUserAccounts();
		
		for (UserAccount dbUser: userAccounts) {
			if (username == dbUser.getUsername()) {
				return false;
			}
		}
		
		return true;
	}
	
	public void deposit(BankAccount b) {
		// ask for deposit amount, offer cancel
		// if cancel break
		// valid entry (must be float any limit, perhaps test enormous float)
		// if valid, do overflow balance calculation (999billion.99)
		// if overflow return error
		// if valid, perform operation, say deposit successful, return updated balance.
	}
	
	public void withdraw(BankAccount b) {
		// ask for withdrawal amount, offer cancel
		// if cancel break
		// validate entry (must be float any limit)
		// if valid, do negative balance calculation
		// if negative return error
		// if valid, perform operation, say operation successful, return updated balance.
	}
	
	public void viewBalance(BankAccount b) {
		System.out.println("Your balance = " + b.getBalance());
	}
}
