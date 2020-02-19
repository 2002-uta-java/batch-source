package com.hylicmerit.service;

import java.util.List;

import com.hylicmerit.dao.AccountDao;
import com.hylicmerit.dao.AccountDaoImpl;
import com.hylicmerit.models.Account;
import com.hylicmerit.models.User;

public class AccountService {
	private AccountDao accountDao = new AccountDaoImpl();

	public List<Account> getAllAccounts(User u) {
		if (u != null) {
			return accountDao.getAssociatedAccounts(u);
		} else {
			return null;
		}
	}

	public boolean createAccount(Account a, User u) {
		if (a == null || u == null) {
			return false;
		} else {
			if (accountDao.createAccount(a, u) != 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean updateAccount(Account a, int amount) {
		if (a != null) {
			// store previous balance
			double prevBalance = a.getBalance();
			// calculate new balance
			a.setBalance(prevBalance + (amount));
			if ((prevBalance + amount) >= 0) {
				if (accountDao.updateAccount(a) != 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean deleteAccount(Account a) {
		if (a != null) {
			if (accountDao.deleteAccount(a) != 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
