package com.chrandle.models;



public class User {

/*
 * -------------------------------
 * Members	
 * -------------------------------
 */
	private String username = "";
	private long userid;
	private String password = null;
	private String email = "";
	private Account account = null;

/*
 * -------------------------------
 * Constructors
 * -------------------------------
 */

	public User() {
		super();
	}

	public User(String username, long userid) {
		super();
		this.username = username;
		this.userid = userid;
	}

//Constructor for new account	
	public User(String username, long userid, String password, String email) {
		super();
		this.username = username;
		this.userid = userid;
		this.password = password;
		this.email = email;
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
		return "User [username=" + username + ", userid=" + userid + ", email=" + email
				+ "]";
	}

	
/*
 * -------------------------------
 * hashCode and Equals
 * -------------------------------
 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
