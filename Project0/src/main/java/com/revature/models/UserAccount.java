package com.revature.models;

public class UserAccount {

	private String username;
	private String password;
	private int bankId;
	
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
		
		// TODO: ADD useraccount to db, assign create bank account and unique bankId in response.
		
	}
	
	// TODO: should not need get password, only if theres a password match (true/false)
	
	// NOTE: ONLY uses this when RETRIEVING a user account from the db, NOT ENTERING.
}
