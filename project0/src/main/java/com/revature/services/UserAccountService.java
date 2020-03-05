package com.revature.services;

import java.util.List;

import com.revature.daos.UserAccountDao;
import com.revature.daos.UserAccountDaoImpl;
import com.revature.models.UserAccount;

public class UserAccountService {
	
	private UserAccountDao uADao = new UserAccountDaoImpl(); 

	public UserAccount createUserAccount(UserAccount ua) {
		return uADao.createUserAccount(ua);
	}

	public List<UserAccount> getAllUserAccounts() {
		return uADao.getAllUserAccounts();
	}
	
	public UserAccount getUserAccountByUsername(String username) {
		return uADao.getUserAccountByUsername(username);
	}
	
	public UserAccount getUserAccountByEmail(String email) {
		return uADao.getUserAccountByUsername(email);
	}
}
