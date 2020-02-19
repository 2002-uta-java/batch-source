package com.revature.daos;

import java.util.List;

import com.revature.models.UserAccount;

public interface UserAccountDao {

	public UserAccount createUserAccount(UserAccount a);
	public List<UserAccount> getAllUserAccounts();
	public UserAccount getUserAccountByUsername(String username);
	public UserAccount getUserAccountByEmail(String email);
	public int updateUserAccount(UserAccount a);
	public int deleteUserAccount(UserAccount a);
}
