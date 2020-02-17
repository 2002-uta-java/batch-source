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
	public Account(){
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

	public void setAccountid(long a) {
		this.accountid = a;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

	public String getType() {
		return type;
	}

	public void setType(String t) {
		this.type = t;
		
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
