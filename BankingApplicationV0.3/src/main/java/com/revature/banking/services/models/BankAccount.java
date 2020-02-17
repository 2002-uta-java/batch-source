package com.revature.banking.services.models;

/**
 * This is the model used by the front end to interact with a user. The service
 * layer will provide these objects to the front end and will also transform
 * them to EncryptedXXX for insertion into the database.
 * 
 * @author Jared F Bennatt
 *
 */
public class BankAccount {
	private int accountKey;
	private String accountNo;
	private double balance;

	public BankAccount() {
		super();
	}

	public BankAccount(int accountKey, String accountNo, double balance) {
		super();
		this.accountKey = accountKey;
		this.accountNo = accountNo;
		this.balance = balance;
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
		if (accountKey != other.accountKey)
			return false;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		return true;
	}

	public int getAccountKey() {
		return accountKey;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountKey;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public void setAccountKey(int accountKey) {
		this.accountKey = accountKey;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccount [accountKey=" + accountKey + ", accountNo=" + accountNo + ", balance=" + balance + "]";
	}
}
