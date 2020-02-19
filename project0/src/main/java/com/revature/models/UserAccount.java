package com.revature.models;

import java.io.Serializable;

public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	int uaid;
	String username;
	String email;
	String password;
	
	public UserAccount() {
		super();
	}
	public UserAccount(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	public UserAccount(int uaid, String username, String email, String password) {
		super();
		this.uaid = uaid;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public int getUaid() {
		return uaid;
	}
	public void setUaid(int uaid) {
		this.uaid = uaid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + uaid;
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
		UserAccount other = (UserAccount) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (uaid != other.uaid)
			return false;
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
	
	@Override
	public String toString() {
		return "UserAccount [uaid=" + uaid + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}
}
