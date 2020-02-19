package com.revature.service;

import com.revature.dao.UserAccountDao;
import com.revature.dao.UserAccountDaoImpl;
import com.revature.models.UserAccount;

public class UserAccountService {
	
	private UserAccountDao uad = new UserAccountDaoImpl();
	
	public UserAccount createUserAccount(UserAccount ua) {
		return uad.createUserAccount(ua);	
	}
	
	public UserAccount getUserAccount(String identifier, String password) {
		return uad.getUserAccount(identifier, password);		
	}

}
