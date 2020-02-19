package com.revature.service;

import com.revature.dao.UserToBankAccountDao;
import com.revature.dao.UserToBankAccountDaoImpl;

public class UserToBankAccountService {
	
	private UserToBankAccountDao utbad = new UserToBankAccountDaoImpl();
	
	public boolean establishAssociation(int userAccountId, int bankAccountId) {
		
		boolean didItExecute = false;
		
		didItExecute = utbad.establishAssociation(userAccountId, bankAccountId);
		
		return didItExecute;
	}

}
