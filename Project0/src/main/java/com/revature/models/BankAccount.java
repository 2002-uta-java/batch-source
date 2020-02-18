package com.revature.models;

public class BankAccount {

	private int bankId;
	private float balance;
	
	public BankAccount (int bankId, float balance) {
		this.bankId = bankId;
		this.balance = balance;
	}
	
	public int getBankId() {
		return this.bankId;
	}
	
	public void setBankId(int newBankId) {
		bankId = newBankId;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public void setBalance(float newBalance) {
		balance = newBalance;
	}
	
}
