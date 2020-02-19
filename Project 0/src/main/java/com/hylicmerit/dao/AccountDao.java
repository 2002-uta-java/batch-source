package com.hylicmerit.dao;

import java.util.List;

import com.hylicmerit.models.Account;
import com.hylicmerit.models.User;

public interface AccountDao {
	public List<Account> getAssociatedAccounts(User u);

	public int createAccount(Account a, User u);

	public int updateAccount(Account a);

	public int deleteAccount(Account a);
}
