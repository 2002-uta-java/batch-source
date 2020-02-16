package com.revature.banking.models;

public class EncryptedBankAccount {

	private String accountNo;
	private String balance;

	public EncryptedBankAccount() {
		super();
	}

	public EncryptedBankAccount(String accountNo, String balance) {
		super();
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
		EncryptedBankAccount other = (EncryptedBankAccount) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public String getBalance() {
		return balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		return result;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "EncryptedBankAccount [accountNo=" + accountNo + ", balance=" + balance + "]";
	}
}
