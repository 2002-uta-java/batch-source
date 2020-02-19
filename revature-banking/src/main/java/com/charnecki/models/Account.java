package com.charnecki.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private double balance;
	private List<Integer> holders = new ArrayList<Integer>();
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	public Account() {
		super();
	}
	
	

	public Account(int id, double balance, List<Integer> holders) {
		super();
		this.id = id;
		this.balance = balance;
		this.holders = holders;
//		this.transactions = transactions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}
	
	public String getBalanceFormatted() {
		return String.format("%.2f",balance);
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Integer> getHolders() {
		return new ArrayList<Integer>(holders);
	}

	public void setHolders(List<Integer> holders) {
		this.holders = holders;
	}

	public List<Transaction> getHistory() {
		return transactions;
	}

	public void setHistory(List<Transaction> history) {
		this.transactions = history;
	}
	
	public String getAccountType() {
		if (this instanceof Checking) {
			return "checking";
		} else if (this instanceof Savings) {
			return "savings";
		}
		
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
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
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", holders=" + holders + ", transactions=" + transactions
				+ "]";
	}
	
	

}
