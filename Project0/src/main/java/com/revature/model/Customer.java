package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String username;
	private String password;
	private String name;
	private String lastname;
	private String address;
	private int zipcode;
	private String email;
	
	public Customer() {
		super();
	}
	
	public Customer(int id, String username, String password, String name, String lastname, String address, int zipcode, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.zipcode = zipcode;
		this.email = email;
	}


	public Customer(String username, String password, String name, String lastName, String address, int zipcode,
			String email) {
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastName;
		this.address = address;
		this.zipcode = zipcode;
		this.email = email;
	}
	
	public Customer(String username, String password) {
		
	}

	public Customer(String password) {
	
	}

	public int customerId(int id) {
		return id;

	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public String setPassword(String password) {
		return this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, lastname, name, password, username, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(lastname, other.lastname) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username)
				&& zipcode == other.zipcode;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", lastname=" + lastname + ", address=" + address + ", zipcode=" + zipcode + ", email=" + email
				+ ", getLastname()=" + getLastname() + ", getId()=" + getId() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getName()=" + getName() + ", getAddress()=" + getAddress()
				+ ", getZipcode()=" + getZipcode() + ", getEmail()=" + getEmail() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}

	
	
	
}
