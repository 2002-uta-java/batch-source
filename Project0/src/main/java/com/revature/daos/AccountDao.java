package com.revature.daos;

import java.util.List;

public interface AccountDao {
	
//	public List<UserAccount> getUserAccounts(); // probably not needed. unless you cant directly extract one username.
	public boolean checkUsernameExists(String username);
	
	public void createUserAccount(String username, String password); 
	public void createBankAccount(int bankId);
	
	public void deposit(int depositAmount, int bankId);
	public void withdraw(int withdrawAmount, int bankId); 
	public void viewBalance(int bankId);
	
}
