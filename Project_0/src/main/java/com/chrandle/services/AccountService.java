package com.chrandle.services;
import java.util.List;

import com.chrandle.models.Account;
import com.chrandle.daos.AccountDao;
import com.chrandle.daos.AccountDaoImp;

public class AccountService {
	private AccountDao aDao = new AccountDaoImp();
	
	public List<Account> getAccounts(){
		return aDao.getAccounts();
	}
	
	public Account getAccountById(long id) {
		return aDao.getAccountById(id);
	}

	public long createAccount(long userid, double i, String ty) {
		return aDao.createAccount( userid,i, ty);
	}
}
