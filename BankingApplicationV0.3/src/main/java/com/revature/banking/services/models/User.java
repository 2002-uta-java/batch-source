package com.revature.banking.services.models;

/**
 * This is the model used by the front end to interact with a user. The service
 * layer will provide these objects to the front end and will also transform
 * them to EncryptedXXX for insertion into the database.
 * 
 * @author Jared F Bennatt
 *
 */
public class User {
	private String firstName;
	private String lastName;
	private String password;
	private String taxID;
	private int userKey;
	private String userName;

	public User() {
		super();
	}

	public User(int userKey, String taxID, String firstName, String lastName, String userName, String password) {
		super();
		this.userKey = userKey;
		this.taxID = taxID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (taxID == null) {
			if (other.taxID != null)
				return false;
		} else if (!taxID.equals(other.taxID))
			return false;
		if (userKey != other.userKey)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getTaxID() {
		return taxID;
	}

	public int getUserKey() {
		return userKey;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((taxID == null) ? 0 : taxID.hashCode());
		result = prime * result + userKey;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userKey=" + userKey + ", taxID=" + taxID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + "]";
	}
}
