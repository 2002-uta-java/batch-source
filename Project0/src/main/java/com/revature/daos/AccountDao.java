package com.revature.daos;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public interface AccountDao {
	
	public List<UserAccount> getUserAccounts();
	public void createAccount(String username, String password);
	UserAccount getUserAccount(String username);
	BankAccount getBankAccount(UserAccount u);
	void updateBankAccount(BankAccount b);
	
	
}
