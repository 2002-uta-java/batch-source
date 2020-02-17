package com.chrandle.models;

public class Account {
/*
 * -------------------------------
 * Members	
 * -------------------------------
 */
	private long accountid = 0;
	private double balance = 0;
	private String type = "Checking";

/*
 * -------------------------------
 * Constructors	
 * -------------------------------
 */
	Account(){
		super();
	}
	
/*
 * -------------------------------
 * Getters and Setters	
 * -------------------------------
 */
	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


/*
 * -------------------------------
 * toString
 * -------------------------------
 */

	@Override
	public String toString() {
		return "Account [accountid=" + accountid + ", balance=" + balance + ", type=" + type + "]";
	}
	
	
	
}
