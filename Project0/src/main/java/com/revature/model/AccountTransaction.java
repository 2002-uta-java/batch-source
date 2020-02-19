package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class AccountTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int accountNumber;
	private String transactionType;
	private double transactionAmount;
	private String transactionDate;
	
	
	public AccountTransaction() {
		super();
	}
	
	
	public AccountTransaction(int id, int accountNumber, String transactionType, double transactionAmount,
			String transactionDate) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
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
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, id, transactionAmount, transactionDate, transactionType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountTransaction other = (AccountTransaction) obj;
		return accountNumber == other.accountNumber && id == other.id
				&& Double.doubleToLongBits(transactionAmount) == Double.doubleToLongBits(other.transactionAmount)
				&& Objects.equals(transactionDate, other.transactionDate)
				&& Objects.equals(transactionType, other.transactionType);
	}
	@Override
	public String toString() {
		return "AccountTransaction [id=" + id + ", accountNumber=" + accountNumber + ", transactionType="
				+ transactionType + ", transactionAmount=" + transactionAmount + ", transactionDate=" + transactionDate
				+ ", getId()=" + getId() + ", getAccountNumber()=" + getAccountNumber() + ", getTransactionType()="
				+ getTransactionType() + ", getTransactionAmount()=" + getTransactionAmount()
				+ ", getTransactionDate()=" + getTransactionDate() + ", hashCode()=" + hashCode() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}
	
	
}
