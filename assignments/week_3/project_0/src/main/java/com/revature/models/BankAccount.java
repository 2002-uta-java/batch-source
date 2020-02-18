package com.revature.models;

import java.io.Serializable;

public class BankAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String accountType;
	private int balance;
	private int accountNumber;

	public BankAccount() {
		super();
	}
	
	public BankAccount(int accountType) {
		if(accountType == 1) {
			this.accountType = "CHECKING";
		} else if (accountType == 2) {
			this.accountType = "SAVINGS";
		} else {
			System.out.println("invalid account type");
		}
	}
	
	public BankAccount(int accountType, int balance) {
		if(accountType == 1) {
			this.accountType = "CHECKING";
		} else if (accountType == 2) {
			this.accountType = "SAVINGS";
		} else {
			System.out.println("invalid account type");
		}
		this.balance = balance;
	}
	
	public BankAccount(int id, String accountType, int balance, int accountNumber) {
		this.id = id;
		this.accountType = accountType;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(int accountType) {
		if(accountType == 1) {
			this.accountType = "CHECKING";
		} else if (accountType == 2) {
			this.accountType = "SAVINGS";
		} else {
			System.out.println("invalid account type");
		}
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "# " + accountType +" Account - Balance: " + balance + ", Account Number: " + accountNumber;
	}
	
}
