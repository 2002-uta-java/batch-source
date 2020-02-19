package com.dean.dao;

import com.dean.models.Account;

public interface AccountDao {
	public Account getAccountById(int id);
	public Account getAccountByClientId(int id);
	public int createAccount(Account account);
	public void deposit(int id);
	public void withdraw(int id);
	public void transfer(int fromId);
}
