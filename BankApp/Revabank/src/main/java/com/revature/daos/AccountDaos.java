package com.revature.daos;

import com.revature.models.Account;

public interface AccountDaos {
	public Account getAccount(int id);
	public int createAccount(Account a);
	public int updateAccount(Account a);
	public int deleteAccount(Account a);
	public Account createDepartmentWithFunction(Account a);
}
