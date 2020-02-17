package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.UserAccount;
import com.revature.util.ConnectionUtil;

public class UserAccountService {

	//TODO: logic
	
	
	// helpers
	
	public boolean checkUsernameUnique(String username) {;
		AccountDao accountDao = new AccountDaoImpl();
		
		List<UserAccount> userAccounts = accountDao.getUserAccounts();
		
		for (UserAccount dbUser: userAccounts) {
			if (username == dbUser.getUsername()) {
				return false;
			}
		}
		
		return true;
	}
	
	public void deposit(UserAccount u) {
		
	}
	
	public void withdraw(UserAccount u) {
		
	}
	
	public void viewBalance(UserAccount u) {
		
	}
}
