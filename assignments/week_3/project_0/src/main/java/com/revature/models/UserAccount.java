package com.revature.models;

import java.io.Serializable;

public class UserAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int clientId;
	private String userName;
	private String email;
	private String password;
	
	public UserAccount() {
		super();
	}
	
	public UserAccount(int clientId, String userName, String email, String password) {
		this.clientId = clientId;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	public String toString() {
		return "UserAccount [id=" + id + ", clientId=" + clientId + ", userName=" + userName + ", email=" + email
				+ ", password=" + password + "]";
	}

}
