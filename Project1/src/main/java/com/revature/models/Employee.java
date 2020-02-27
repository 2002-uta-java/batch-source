package com.revature.models;

public class Employee {
	
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String password;
	private String position; // Must be "employee" or "manager".
	
	public Employee(String email, String firstName, String lastName, String gender, String password, String position) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.password = password;
		this.position = position;
	}
	
	
}
