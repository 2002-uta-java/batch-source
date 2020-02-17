package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.UserAccount;

public class UserAccountService {
	
	private AccountDao dao = new AccountDaoImpl();
	
	public void userAccountMenu() {
		
	}
	
	public boolean userAccountExist(String username) {
		List<UserAccount> userAccounts = dao.getUserAccounts();
		
		for (UserAccount u: userAccounts) {
			if (u.getUsername() == username) {
				return true;
			}
		}
		
		return false;
	}
	
}
