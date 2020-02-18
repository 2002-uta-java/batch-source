package com.revature.service;

import com.revature.dao.UserToBankAccountDao;
import com.revature.dao.UserToBankAccountDaoImpl;

public class UserToBankAccountService {
	
	private UserToBankAccountDao utbad = new UserToBankAccountDaoImpl();
	
	public void establishAssociation(int userAccountId, int bankAccountId) {
		utbad.establishAssociation(userAccountId, bankAccountId);
	}

}
