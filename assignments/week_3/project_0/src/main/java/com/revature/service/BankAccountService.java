package com.revature.service;

import java.util.List;

import com.revature.dao.BankAccountDao;
import com.revature.dao.BankAccountDaoImpl;
import com.revature.models.BankAccount;

public class BankAccountService {	
	
	private BankAccountDao bad = new BankAccountDaoImpl();
	
	public BankAccount createBankAccount(BankAccount ba) {
		return bad.createBankAccount(ba);
		
	}
	
	public List<BankAccount> getAccounts(int userAccountId) {
		return bad.getAccounts(userAccountId);
	}
	
	public int depositAmount(BankAccount ba, double depositAmount) {
		
		int success = 0;
		
		double newBalance = ba.getBalance() + depositAmount;
		
		success = bad.updateBalance(ba, newBalance);
		
		return success;
		// log in transactions
	}
	
	public int withdrawAmount(BankAccount ba, double withdrawAmount) {
		
		int success = 0;
		
		double newBalance = ba.getBalance() - withdrawAmount;
		
		if (newBalance >= 0) {
			success = bad.updateBalance(ba, newBalance);
		} else {
			success = 3;
		}
		
		// log in transactions
		
		return success;
		
	}
}
