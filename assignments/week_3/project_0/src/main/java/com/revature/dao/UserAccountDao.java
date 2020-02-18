package com.revature.dao;

import com.revature.models.UserAccount;

public interface UserAccountDao {
	
	public int createUserAccount();
	public UserAccount createUserAccount(UserAccount ua);
	public UserAccount getUserAccount(String identifier, String password);
	public int updateUserAccount();
	public int deleteUserAccount();

}
