package com.revature.models;

import java.io.Serializable;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int account_id;
	private int user_id;
	private double balance;
	
	//Constructors
	public Account() {
		super();
	}
	public Account(int account_id, int user_id, double balance) {
		super();
		this.account_id = account_id;
		this.user_id = user_id;
		this.balance = balance;
	}

	//Setters and Getters
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//HashCode and Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account_id;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + user_id;
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
		Account other = (Account) obj;
		if (account_id != other.account_id)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	
	//toStringMethod
	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", user_id=" + user_id + ", balance=" + balance + "]";
	}
	
}
