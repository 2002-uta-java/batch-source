package com.revature.dao;

import java.util.List;

import com.revature.models.BankAccount;

public interface BankAccountDao {
	
	public BankAccount createBankAccount(BankAccount ba);
	public int updateBankAccount();
	public List<BankAccount> getAccounts(int userAccountId);
//	public void depositAmount(int accountNumber, int newBalance);
	
//	public int getBalance(int accountId);
	public int updateBalance(BankAccount ba, int newBalance);

}
