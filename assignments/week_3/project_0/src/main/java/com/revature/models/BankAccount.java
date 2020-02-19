package com.revature.models;

import java.io.Serializable;

public class BankAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String accountType;
	private double balance;
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
	
	public BankAccount(int accountType, double balance) {
		if(accountType == 1) {
			this.accountType = "CHECKING";
		} else if (accountType == 2) {
			this.accountType = "SAVINGS";
		} else {
			System.out.println("invalid account type");
		}
		this.balance = balance;
	}
	
	public BankAccount(int id, String accountType, double balance, int accountNumber) {
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


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
