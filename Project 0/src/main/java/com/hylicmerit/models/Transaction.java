package com.hylicmerit.models;

public class Transaction {
	private int id;
	private String date;
	private double openingBalance;
	private double closingBalance;

	public Transaction() {
		super();
	}

	public Transaction(int id, String date, double openingBalance, double closingBalance) {
		this();
		this.id = id;
		this.date = date;
		this.openingBalance = openingBalance;
		this.closingBalance = closingBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	@Override
	public String toString() {
		return "Transaction [date=" + date + ", openingBalance=" + openingBalance + ", closingBalance="
				+ closingBalance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(closingBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		temp = Double.doubleToLongBits(openingBalance);
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
		if (Double.doubleToLongBits(closingBalance) != Double.doubleToLongBits(other.closingBalance))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(openingBalance) != Double.doubleToLongBits(other.openingBalance))
			return false;
		return true;
	}
}
