package com.revature.bankingapp.model;

public class BankAccount {
	private double accountNumber = 0;
	private double balance = 0;
	
	
	public BankAccount() {}
	
	public BankAccount(double accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public double getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(double accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
