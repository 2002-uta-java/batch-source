package com.revature.daos;

import java.util.List;

import com.revature.models.UserAccount;

public interface AccountDao {
	
	public List<UserAccount> getUserAccounts();
	public void createAccount(String username, String password);
	public void updateBalance(UserAccount u);
	public void getBalance(UserAccount u);
	
}
