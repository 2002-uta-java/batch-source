package com.charnecki.daos;

import java.util.List;

import com.charnecki.models.Transaction;

public interface TransactionDao {
	
	public List<Transaction> getAllTransactions();
	public Transaction getTransactionById(int id);
	public int createTransaction(Transaction t);
	public int updateTransaction(Transaction t);
	public int deleteTransaction(Transaction t);

	public List<Transaction> getAllWithAccountId(int id);
}
