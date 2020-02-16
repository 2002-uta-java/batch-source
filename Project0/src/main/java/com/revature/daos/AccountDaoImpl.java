package com.revature.daos;

import java.util.List;

public class AccountDaoImpl implements AccountDao{

//	@Override
//	public List<UserAccount> getUserAccounts() {
//		// TODO Auto-generated method stub
//		return null;
//	} // Probably not needed.

	@Override
	public boolean checkUsernameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createUserAccount(String username, String password) {
		// TODO: DO NOT CREATE CLASS, simply add the user/pass to db, AND generate bankaccount -> bankId
		// NOTE: no instances of classes seem to be created in this application.
		
	}

	@Override
	public void createBankAccount(int bankId) {
		// TODO DO NOT CREATE CLASS, add a new bank account with generated bankId. set balance to 0.
		
	}

	@Override
	public void deposit(int depositAmount, int bankId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(int withdrawAmount, int bankId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBalance(int bankId) {
		// TODO Auto-generated method stub
		
	}
	
}
