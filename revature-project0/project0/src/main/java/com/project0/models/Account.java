package com.project0.models;

public class Account {
private static final long serialVersionUID = 1L;
	
	private int accId;
	private int custId;
	private String accType;
	private double balance;
	
	// no arg constructor
	public Account() {
		super();
	}
	// constructor with id
	public Account(int accId) {
		super();
		this.accId = accId;
	}
	// constructor with custID, accType
	// balance is default 0
	public Account(int custId, String accType) {
		super();
		this.custId = custId;
		this.accType = accType;
		this.balance = 0;
	}
	
	// constructor with custID, accType, balance
	public Account(int custId, String accType, double balance) {
		super();
		this.custId = custId;
		this.accType = accType;
		this.balance = balance;
	}
	// constructor with all parameters pass in
	public Account(int accId, int custId, String accType, double balance) {
		super();
		this.accId = accId;
		this.custId = custId;
		this.accType = accType;
		this.balance = balance;
	}

	// getter and setter----------------------------------
	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accId;
		result = prime * result + ((accType == null) ? 0 : accType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + custId;
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
		Account other = (Account) obj;
		if (accId != other.accId)
			return false;
		if (accType == null) {
			if (other.accType != null)
				return false;
		} else if (!accType.equals(other.accType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (custId != other.custId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accId=" + accId + ", custId=" + custId + ", accType=" + accType + ", balance=" + balance + "]";
	}

	
	
	
	
}
