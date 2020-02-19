package com.hylicmerit.service;

import java.util.List;

import com.hylicmerit.dao.TransactionDao;
import com.hylicmerit.dao.TransactionDaoImpl;
import com.hylicmerit.models.Account;
import com.hylicmerit.models.Transaction;
import com.hylicmerit.models.User;

public class TransactionService {
	private TransactionDao transDao = new TransactionDaoImpl();
	
	public List<Transaction> getAssociatedTransactions(User u){
		if(u != null) {
			return transDao.getAllTransactions(u);
		}
		else {
			return null;
		}
	}
	
	public boolean createTransaction(Account a, User u, double amount) {
		if(a == null || u == null) {
			return false;
		}
		else {
			if(transDao.createTransaction(a, u, amount) != 0) {
				return true;
			} else {
				return false;
			}
		}
	}
}
