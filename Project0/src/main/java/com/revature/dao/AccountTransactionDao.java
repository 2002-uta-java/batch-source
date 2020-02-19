package com.revature.dao;

import com.revature.model.AccountTransaction;

public interface AccountTransactionDao {

	public int getaccountTransactionById (int id);
	public AccountTransaction getAccountStatement (AccountTransaction t);
	public AccountTransaction createAccountTransactionByFunction(AccountTransaction t);

}

