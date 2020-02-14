package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDaos;
import com.revature.daos.AccountDaosImplementation;
import com.revature.models.Account;


public class AccountServices {
	
	AccountDaos ad = new AccountDaosImplementation();
		
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getAccount(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int createAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void deposit(double amount) {
		ad.updateAccount(null);
	}
	
	public void withdraw(double amount) {
//		if(amount > balance)
//			System.out.println("Not enough funds");
//		if(amount <= balance) {
//			this.balance -= amount;
//			System.out.println("New balance: " + balance);
//		}
		ad.updateAccount(null);
	}
}
