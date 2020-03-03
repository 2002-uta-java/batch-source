package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {
	
	public List<Account> getAllAccounts();
	public Account getAccountById(int id);
	public Account getAccountByEmail(String email);
	public int createAccount(Account a);
	public int updateAccount(Account a);
	public int deleteAccount(Account a);
	
}
