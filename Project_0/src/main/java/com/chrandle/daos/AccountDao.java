package com.chrandle.daos;

import java.util.List;

import com.chrandle.models.Account;
import com.chrandle.util.InvalidTransactionException;

public interface AccountDao {
	public List<Account> getAccounts();
	public Account getAccountById(long id);
	public double updateAccount(Account u,double amount) throws InvalidTransactionException;
	public int deleteAccount(Account u);
	public long createAccount(long uid,double balance, String type);
	
}