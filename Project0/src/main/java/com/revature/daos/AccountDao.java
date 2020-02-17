package com.revature.daos;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public interface AccountDao {
	
	public List<UserAccount> getUserAccounts();
	public UserAccount createAccount(UserAccount u);
	UserAccount getUserAccount(String username);
	BankAccount getBankAccount(UserAccount u);
	void updateBankAccount(BankAccount b);
	
	
}
