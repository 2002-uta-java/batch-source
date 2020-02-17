package com.chrandle.services;
import java.util.List;

import com.chrandle.models.Account;
import com.chrandle.daos.AccountDao;
import com.chrandle.daos.AccountDaoImp;

public class AccountService {
	private AccountDao aDao = new AccountDaoImp();
	
	public List<Account> getAllAccounts(){
		return aDao.getAccounts();
	}
	
	public Account getAccountrById(long id) {
		return aDao.getAccountById(id);
	}
}
