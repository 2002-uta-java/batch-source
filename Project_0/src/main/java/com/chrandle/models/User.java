package com.chrandle.models;

public class User {
/*
 * -------------------------------
 * Members	
 * -------------------------------
 */
	private String username = "";
	private long userid = 0;
	private String password = " "; 

/*
 * -------------------------------
 * Constructors
 * -------------------------------
 */
	public User(String u, String p, long id) {
		super();
		this.username = u;
		this.password = p;
		this.userid = id;
	}
	
	public User(String u, String p) {
		super();
		this.username = u;
		this.password = p;
		//TODO: randomly generate userID
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
	
	

}
