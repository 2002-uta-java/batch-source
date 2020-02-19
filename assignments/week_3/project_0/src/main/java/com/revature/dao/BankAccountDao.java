package com.revature.dao;

import java.util.List;

import com.revature.models.BankAccount;

public interface BankAccountDao {
	
	public BankAccount createBankAccount(BankAccount ba);
	public List<BankAccount> getAccounts(int userAccountId);
	public int updateBalance(BankAccount ba, double newBalance);

}
