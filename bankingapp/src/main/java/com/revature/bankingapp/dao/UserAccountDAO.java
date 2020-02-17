package com.revature.bankingapp.dao;

import java.util.List;

import com.revature.bankingapp.model.UserAccount;

public interface UserAccountDAO {
	public List<UserAccount> getUserAccount();
	public UserAccount getUserAccountById(int id);
	public int createUserAccount(UserAccount ua);
	public int updateUserAccount(UserAccount ua);
	public int deleteUserAccount(UserAccount ua);
	public UserAccount createUserAccountWithFunction(UserAccount ua);
}
