package com.revature.project1.models;

import java.io.Serializable;

public class Appuser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private boolean isManager;
	private String email;
	private String pass;
	private String firstname;
	private String lastname;
	
	public Appuser() {
		super();
	}
	
	
	public Appuser(int id, boolean isManager, String email, String pass, String firstname, String lastname) {
		super();
		this.id = id;
		this.isManager = isManager;
		this.email = email;
		this.pass = pass;
		this.firstname = firstname;
		this.lastname = lastname;
	}


	//getters and setters
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isManager() {
		return isManager;
	}


	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	// hashcode and equals

	
	

}
