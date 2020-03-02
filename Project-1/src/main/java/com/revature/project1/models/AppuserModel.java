package com.revature.project1.models;

import java.io.Serializable;

public class Appuser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private boolean isManager;
	private String uname;
	private String pass;
	private String firstname;
	private String lastname;
	private String email;
	
	public Appuser() {
		super();
	}

}
