package com.revature.services;

import com.revature.daos.AccountDaos;
import com.revature.daos.AccountDaosImplementation;
import com.revature.models.Account;


public class AccountServices {
	
	AccountDaos accountDaos = new AccountDaosImplementation();

	public Account getAccount(int id) {
		return accountDaos.getAccount(id);
	}

	public boolean createAccount(Account a) {
		int accountCreated = accountDaos.createAccount(a);
		if(accountCreated != 0) {
			System.out.println("Account creation successful");
			return true;
		}
		System.out.println("Unable to create, please try again\n");
		return false;
	}

	public boolean updateAccount(Account a) {
		int accountUpdated = accountDaos.updateAccount(a);
		if(accountUpdated != 0) {
			System.out.println("Account updated successful");
			return true;
		}
		System.out.println("Unable to update, please try again\n");
		return false;
	}

	public boolean deleteAccount(Account a) {
		int accountDeleted = accountDaos.deleteAccount(a);
		if(accountDeleted != 0) {
			System.out.println("Account deletion successful");
			return true;
		}
		System.out.println("Unable to delete, please try again\n");
		return false;
	}
	
	public boolean deposit(Account a, long amount) {
		a.setBalance(a.getBalance() + amount);
		int accountUpdated = accountDaos.updateAccount(a);
		if(accountUpdated != 0) {
			System.out.println("Deposit successful");
			return true;
		}
		System.out.println("Unable to deposit, please try again\n");
		return false;
	}
	
	public boolean withdraw(Account a, long amount) {
		Account account = accountDaos.getAccount(a.getAccount_id());
		if(account.getBalance() >= amount) {
			account.setBalance(account.getBalance() - amount);
			int accountUpdated = accountDaos.updateAccount(a);
			if(accountUpdated != 0) {
				System.out.println("Withdrawl successful");
				return true;
			}
			System.out.println("Unable to withdraw, please try again\n");
			return false;
		}
		System.out.println("Insuficient Funds");
		return false;
	}
}
