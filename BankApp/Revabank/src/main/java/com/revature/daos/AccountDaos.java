package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDaos {
	
	//This will be implemented once I get multiple accounts
	public List<Account> getAccounts();
	
	public Account getAccount(int id);
	public int createAccount(Account a);
	public int updateAccount(Account a);
	public int deleteAccount(Account a);
}
