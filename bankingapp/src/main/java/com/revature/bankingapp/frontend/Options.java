package com.revature.bankingapp.frontend;

import java.util.List;
import java.util.Scanner;

import com.revature.bankingapp.dao.BankAccountDaoImpl;
import com.revature.bankingapp.dao.TransactionDaoImpl;
import com.revature.bankingapp.dao.UserAccountDaoImpl;
import com.revature.bankingapp.model.BankAccount;
import com.revature.bankingapp.model.Transaction;
import com.revature.bankingapp.model.UserAccount;

public class Options {
	
	private static Scanner intsc = new Scanner(System.in);
	private static Scanner strsc = new Scanner(System.in);
	private static BankAccountDaoImpl badao = new BankAccountDaoImpl();
	private static TransactionDaoImpl tdao  = new TransactionDaoImpl();
	private static UserAccountDaoImpl uadao = new UserAccountDaoImpl();
	
	void createTransaction() {

		List<Transaction> transactions = tdao.getTransaction();
		int transactionId = transactions.size();
		
		System.out.print("Amount: ");
		Double amount = intsc.nextDouble();
		
		System.out.print("Account From: ");
		int accountFrom = intsc.nextInt();
		
		System.out.print("Account To: ");
		int accountTo = intsc.nextInt();
		
		Transaction t = new Transaction(transactionId, amount, accountFrom, accountTo);
		
		// add transaction to database
		
		tdao.createTransaction(t);
	}

	void createBankAccount() {
		List<BankAccount> bankAccounts = badao.getBankAccounts();
		int bankAccountId = bankAccounts.size();
		double amount = 0;
		BankAccount ba = new BankAccount(bankAccountId, amount);
		badao.createBankAccount(ba);
	}
	
	void createAccount() {
		
		List<UserAccount> userAccounts = uadao.getUserAccount();
		int uaId = 0;
		int baId = 0;
		if (userAccounts.size() != 0)
			uaId = userAccounts.size();
			
		List<BankAccount> bankAccounts = badao.getBankAccounts();
		if (bankAccounts.size() != 0)
			baId = bankAccounts.size();
		
		System.out.print("Username: ");
		String uName = strsc.nextLine();
		
		System.out.print("Password: ");
		String password = strsc.nextLine();
		
		System.out.print("Email: ");
		String email = strsc.nextLine();
		
		System.out.print("Phone number: ");
		String phoneNumber = strsc.nextLine();
		
		BankAccount account = new BankAccount(baId, 0);
		
		UserAccount creation = new UserAccount(uaId, uName, password, email, phoneNumber);
		
		// add user to the database
		uadao.createUserAccount(creation);
		badao.createBankAccount(account);
		
	}
	
	void displayBalance() {
		List<BankAccount> bankAccounts = badao.getBankAccounts();
		
		for (BankAccount ba: bankAccounts) {
			System.out.println(ba.toString());
		}
	}
	
	void displayBankAccounts() {
		List<BankAccount> bankAccounts = badao.getBankAccounts();
		
		for (BankAccount ba: bankAccounts) {
			System.out.println(ba.toString());
		}
	}
	
	void depositMoney() {
		
		System.out.print("Amount to deposit: ");
		double amount = intsc.nextDouble();
		
		System.out.print("BankAccount: ");
		int id = intsc.nextInt();
		
		BankAccount ba = badao.getBankAccountById(id);
		ba.addToBalance(amount);
		badao.updateBankAccount(ba);
	}
	
	void withdrawMoney() {
		System.out.print("Amount to deposit: ");
		double amount = intsc.nextDouble();
		
		System.out.print("BankAccount: ");
		int id = intsc.nextInt();
		
		BankAccount ba = badao.getBankAccountById(id);
		ba.subtractFromBalance(amount);
		badao.updateBankAccount(ba);
	}
	
}
