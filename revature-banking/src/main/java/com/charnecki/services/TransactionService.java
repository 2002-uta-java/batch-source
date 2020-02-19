package com.charnecki.services;

import java.util.List;

import com.charnecki.daos.TransactionDao;
import com.charnecki.daos.TransactionDaoImpl;
import com.charnecki.models.Transaction;


public class TransactionService {
	
	private TransactionDao td = new TransactionDaoImpl();
	
	public List<Transaction> getAllTransactions() {
		return td.getAllTransactions();
	}
	
	public Transaction getUserById(int id) {
		return td.getTransactionById(id);
	}
	
	public List<Transaction> getAllWithAccountId(int id) {
		return td.getAllWithAccountId(id);
	}
	
	public int createTransaction(Transaction t) { // change to bool later
		return td.createTransaction(t);
	}
	
	public boolean updateTransaction(Transaction t) {
		int affectedRows = td.updateTransaction(t);
		if (affectedRows == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteTransaction(Transaction t) {
		int affectedRows = td.deleteTransaction(t);
		if (affectedRows == 1) {
			return true;
		}
		return false;
	}
}
