package com.revature.dao;

public interface UserToBankAccountDao {
	
	public boolean establishAssociation(int userAccountId, int bankAccountId);

}
