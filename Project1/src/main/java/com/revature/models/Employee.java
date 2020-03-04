package com.revature.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
	
	private int id;
	private String email;
	private String position; // Must be "employee" or "manager".
	private String firstName;
	private String lastName;
	private String gender;
	private String password;
	
//	public Employee(int id, String email, String position, String firstName, String lastName,
//															String gender, String password) {
//		this.id = id;
//		this.email = email;
//		this.position = position;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.gender = gender;
//		this.password = password;
//	}
	
	@JsonCreator 
	public Employee(@JsonProperty("id") int id, @JsonProperty("email") String email, @JsonProperty("position") String position, 
			@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
			@JsonProperty("gender") String gender, @JsonProperty("password") String password){
		this.id = id;
		this.email = email;
		this.position = position;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.password = password;
		}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}