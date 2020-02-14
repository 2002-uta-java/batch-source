package com.revature.bankingapp.model;

public class UserAccount {
	private String uName = "";
	private String password = "";
	private String email = "";
	private String phoneNumber = "";
	private int bankAccount = 0;
	
	public UserAccount() {}
	
	public UserAccount(String uName, String password, String email, String phoneNumber) {
		this.uName = uName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
