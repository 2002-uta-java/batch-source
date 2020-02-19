package com.dean.dao;

import java.util.List;

import com.dean.models.Transaction;

public interface TransactionDao {
	public List<Transaction> getTransactionsByClientId(int id);
}
