package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int accountNumber;
	private double accountBalance;
	
	public Account() {
		super();
	}
	
	public Account(int id, int accountNumber, double accountBalance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
    
	public Account(int accountNumber, double accountBalance, int id) {
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.id = id;
	}
	
	public Account(int accountNumber, double a) {
		return;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, accountNumber, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Double.doubleToLongBits(accountBalance) == Double.doubleToLongBits(other.accountBalance)
				&& accountNumber == other.accountNumber && id == other.id;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountBalance=" + accountBalance
				+ ", getId()=" + getId() + ", getAccountNumber()=" + getAccountNumber() + ", getAccountBalance()="
				+ getAccountBalance() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}

