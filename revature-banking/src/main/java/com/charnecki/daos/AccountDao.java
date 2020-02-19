package com.charnecki.daos;

import java.util.List;

import com.charnecki.models.Account;

public interface AccountDao {
	
	public List<Account> getAllAccounts();
	public Account getAccountById(int id);
	public int createAccount(Account a);
	public int updateAccount(Account a);
	public int deleteAccount(Account a);
	
}
