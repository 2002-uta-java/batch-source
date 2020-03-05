package com.revature.models;

import java.io.Serializable;

public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int baid;
	UserAccount ua;
	String accountType;
	double balance;
	
	public BankAccount() {
		super();
	}
	public BankAccount(UserAccount ua, String accountType) {
		super();
		this.ua = ua;
		this.accountType = accountType;
	}
	public BankAccount(int baid, UserAccount ua, String accountType) {
		super();
		this.baid = baid;
		this.ua = ua;
		this.accountType = accountType;
	}
	public BankAccount(int baid, UserAccount ua, String accountType, double balance) {
		super();
		this.baid = baid;
		this.ua = ua;
		this.accountType = accountType;
		this.balance = balance;
	}

	public int getBaid() {
		return baid;
	}
	public void setBaid(int baid) {
		this.baid = baid;
	}
	public UserAccount getUa() {
		return ua;
	}
	public void setUa(UserAccount ua) {
		this.ua = ua;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + baid;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((ua == null) ? 0 : ua.hashCode());
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
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (baid != other.baid)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (ua == null) {
			if (other.ua != null)
				return false;
		} else if (!ua.equals(other.ua))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BankAccount [baid=" + baid + ", ua=" + ua + ", accountType=" + accountType + ", balance=" + balance
				+ "]";
	}
}
