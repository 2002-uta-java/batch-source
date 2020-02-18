package com.revature.service;

import com.revature.dao.UserAccountDao;
import com.revature.dao.UserAccountDaoImpl;
import com.revature.models.UserAccount;

public class UserAccountService {
	
	
	private UserAccountDao uad = new UserAccountDaoImpl();
	
//	ClientService cs = null;
	
//	public static void createUserAccount(String uName, String email, String password) {
//		
//	}
//	
//	public static void createUserAccount(String fName, String lName, String uName, String email, String password) {
//		
//		cs.createClient(fname, lName);
//		createUserAccount()
//		
//	}
	
	public UserAccount createUserAccount(UserAccount ua) {
		return uad.createUserAccount(ua);
		
	}
	
	public UserAccount getUserAccount(String identifier, String password) {
		
		return uad.getUserAccount(identifier, password);
		
	}

}
