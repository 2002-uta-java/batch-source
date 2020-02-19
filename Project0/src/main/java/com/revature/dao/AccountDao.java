package com.revature.dao;

import java.util.List;

import com.revature.model.Account;

public interface AccountDao {

	public List<Account> getAccount();
	public int getAccountById (int id);
	public int getAccountNumber (int num);
	public double getAccountBalance (int num);
	public Account createAccountByFunction(Account a);
	public Account updateAccountByFunctionAdd(int num, double amount);
	public Account updateAccountByFunctionSub(int num, double amount);
}
