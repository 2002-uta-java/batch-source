package com.project0.daos;

import java.util.List;

import com.project0.models.Account;

public interface AccountDao {
	public List<Account> getAccounts();
	public Account getAccountByAccId(int id);
	public Account getAccountByCustId(int id);
//	public int createAccount(Account a);
	public int updateAccount(Account a);
	public int deleteAccount(Account a);
	public Account createAccountWithFunction(Account a);
}
