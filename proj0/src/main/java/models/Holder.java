package models;

import java.io.Serializable;

public class Holder implements Serializable{

	private static final long serialVersionUID = -5618622084465169095L;
	
	private String holderUserName;
	private String holderPassword;
	private double balance;
	
	public Holder() {
		super();
	}
	
	public Holder(String holderUserName, String holderPassword, double balance) {
		super();
		this.holderUserName=holderUserName;
		this.holderPassword=holderPassword;
		this.balance = balance;
	}
	
	//Getters and Setters auto generated
	
	public Holder(String holderUserName, String holderPassword) {
		super();
		this.holderUserName=holderUserName;
		this.holderPassword=holderPassword;
		
	}

	public String getHolderUserName() {
		return holderUserName;
	}

	public void setHolderUserName(String holderUserName) {
		this.holderUserName = holderUserName;
	}

	public String getHolderPassword() {
		return holderPassword;
	}

	public void setHolderPassword(String holderPassword) {
		this.holderPassword = holderPassword;
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
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((holderPassword == null) ? 0 : holderPassword.hashCode());
		result = prime * result + ((holderUserName == null) ? 0 : holderUserName.hashCode());
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
		Holder other = (Holder) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (holderPassword == null) {
			if (other.holderPassword != null)
				return false;
		} else if (!holderPassword.equals(other.holderPassword))
			return false;
		if (holderUserName == null) {
			if (other.holderUserName != null)
				return false;
		} else if (!holderUserName.equals(other.holderUserName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Holder [holderUserName=" + holderUserName + ", holderPassword=" + holderPassword + ", balance="
				+ balance + "]";
	}	
	
}