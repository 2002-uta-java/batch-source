package com.chrandle.daos;

import java.util.List;

import com.chrandle.models.Account;
import com.chrandle.util.InvalidTransactionException;

public class AccountDaoImp implements AccountDao {

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountrById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createAccount(Account u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double updateAccount(Account u, double amount) throws InvalidTransactionException {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public int deleteAccount(Account u) {
		// TODO Auto-generated method stub
		return 0;
	}

}
