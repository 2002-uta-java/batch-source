package com.revature.bankingapp.model;

public class Transaction {
	double amount   = 0.0d;
	int accountFrom = 0;
	int accountTo   = 0;
	
	public Transaction() {}
	
	public Transaction(double amount, int accountFrom, int accountTo) {
		this.amount = amount;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
}
