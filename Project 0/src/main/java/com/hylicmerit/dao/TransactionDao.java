package com.hylicmerit.dao;

import java.util.List;

import com.hylicmerit.models.Account;
import com.hylicmerit.models.Transaction;
import com.hylicmerit.models.User;

public interface TransactionDao {
	List<Transaction> getAllTransactions(User u);
	
	int createTransaction(Account a, User u, double amount);
}
