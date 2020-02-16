package com.revature.models;

public class BankAccount {

	private int bankId;
	private int balance;
	
	public int getBankId() {
		return this.bankId;
	}
	
	public void setBankId(int newBankId) {
		bankId = newBankId;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	// TODO: do i need get bankId? how to go from user -> bank account based on bankid?
	
	public void setBalance(int newBalance) { // probably handle negative balance check in bank account services.
		balance = newBalance;
	}
	
}
