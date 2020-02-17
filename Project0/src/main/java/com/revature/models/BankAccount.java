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
	
	public void setBankId(int newBankId) { // is this needed?
		bankId = newBankId;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public void setBalance(float newBalance) { // probably handle negative balance check in bank account services.
		balance = newBalance;
	}
	
}
