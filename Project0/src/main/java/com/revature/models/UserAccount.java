package com.revature.models;

public class UserAccount {

	private String username;
	private String password;
	private int bankId;
	
	public UserAccount(String username, String password, int bankId) {
		this.username = username;
		this.password = password;
		this.bankId = bankId;
		
		// TODO: ADD useraccount to db, assign create bank account and unique bankId in response.
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setUsername(String password) {
		this.password = password;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
	
	// TODO: should not need get password, only if theres a password match (true/false)
	
	// NOTE: ONLY uses this when RETRIEVING a user account from the db, NOT ENTERING.
}
