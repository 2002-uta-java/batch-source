package com.chrandle.daos;

import java.util.List;

import com.chrandle.models.Account;
import com.chrandle.util.InvalidTransactionException;

public interface AccountDao {
	public List<Account> getAccounts();
	public Account getAccountrById(long id);
	public int createAccount(Account u);
	public double updateAccount(Account u,double amount) throws InvalidTransactionException;
	public int deleteAccount(Account u);
}