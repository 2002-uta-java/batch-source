package com.revature.bankingapp.model;

public class Transaction {
	double amount   = 0.0d;
	int accountFrom = 0;
	int accountTo   = 0;
	
	public Transaction() {}
	
	public Transaction(double amount, int accountFrom, int accountTo) {
		this.amount = amount;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountFrom;
		result = prime * result + accountTo;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Transaction other = (Transaction) obj;
		if (accountFrom != other.accountFrom)
			return false;
		if (accountTo != other.accountTo)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", accountFrom=" + accountFrom + ", accountTo=" + accountTo + "]";
	}
}
