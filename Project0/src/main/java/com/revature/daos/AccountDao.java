package com.revature.daos;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public interface AccountDao {
	
	public List<UserAccount> getUserAccounts();
	public List<Integer> getBankAccounts();
	public UserAccount createAccount(UserAccount u);
	UserAccount getUserAccount(String username);
	BankAccount getBankAccountByUserAccount(UserAccount u);
	BankAccount getBankAccountByBankId(int bankId);
	void updateBankAccount(BankAccount b);
	
}
