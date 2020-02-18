package com.revature.service;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.BankAccountDao;
import com.revature.dao.BankAccountDaoImpl;
import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public class BankAccountService {
	
	private static Scanner sc = new Scanner(System.in);
	
	
	private BankAccountDao bad = new BankAccountDaoImpl();
	
	public BankAccount createBankAccount(BankAccount ba) {
		return bad.createBankAccount(ba);
		
	}
	
	public List<BankAccount> getAccounts(int userAccountId) {
		return bad.getAccounts(userAccountId);
	}
	
	public int depositAmount(BankAccount ba, int depositAmount) {
		
		int success = 0;
		
		int newBalance = ba.getBalance() + depositAmount;
		
		success = bad.updateBalance(ba, newBalance);
		
		return success;
		// log in transactions
	}
	
	public int withdrawAmount(BankAccount ba, int withdrawAmount) {
		
		int success = 0;
		
		int newBalance = ba.getBalance() - withdrawAmount;
		
		if (newBalance >= 0) {
			success = bad.updateBalance(ba, newBalance);
		} else {
			System.out.println("> Insufficient funds.");
		}
		
		// log in transactions
		
		return success;
		
	}
	
	
//	public int getBalance(int accountId) {
//		return 0;
//	}
	
//	public BankAccount createBankAccount(UserAccount ua, BankAccount ba) {
//		return bad.createBankAccount(ba);
//	}

}
