package com.chrandle.models;

public class User {
/*
 * -------------------------------
 * Members	
 * -------------------------------
 */
	private String username = "";
	private long userid;
	private String password = " "; 
	private String email = "";
	private Account account;

/*
 * -------------------------------
 * Constructors
 * -------------------------------
 */

	public User() {
		super();
	}

/*
 * -------------------------------
 * Methods
 * -------------------------------
 */
	
	public long deposit(long amount) {
		return 0;
	}
	
	public long Withdraw(long amount) {
		return 0;
	}
			
	
/*
 * -------------------------------
 * Getters and Setters	
 * -------------------------------
 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

/*
 * -------------------------------
 * toString
 * -------------------------------
 */
	@Override
	public String toString() {
		return "User [username=" + username + ", userid=" + userid + ", password=" + password + ", email=" + email
				+ "]";
	}
	
	

}
