package com.charnecki.models;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private double amount;
	private int accountId;
	private String note;
	
	public Transaction() {
		super();
	}
	
	public Transaction(int id, double amount, int accountId, String note) {
		super();
		this.id = id;
		this.amount = amount;
		this.accountId = accountId;
		this.note = note;
	}

	public abstract int transact();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccount(int accountId) {
		this.accountId = accountId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTransactionType() {
		if (this instanceof Deposit) {
			return "deposit";
		} else if (this instanceof Withdrawal) {
			return "withdrawal";
		} else if (this instanceof Transfer) {
			return "transfer";
		}
		
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
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
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (id != other.id)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", accountId=" + accountId + ", note=" + note + "]";
	}

}
