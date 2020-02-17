package com.revature.banking.models;

public class BankAccount {
	private String accountNo;
	private double balance;
	private int rowKey;

	public BankAccount() {
		super();
	}

	public BankAccount(String accountNo, double balance, int rowKey) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.rowKey = rowKey;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public int getRowKey() {
		return rowKey;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
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
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (rowKey != other.rowKey)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rowKey;
		return result;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNo=" + accountNo + ", balance=" + balance + ", rowKey=" + rowKey + "]";
	}
}
