package com.revature.bankingapp.dao;

import java.util.List;

import com.revature.bankingapp.model.Transaction;

public interface TransactionDAO {
	public List<Transaction> getTransaction();
	public Transaction getTransactionById(int id);
	public int createTransaction(Transaction t);
	public int updateTransaction(Transaction t);
	public int deleteTransaction(Transaction t);
}
