package com.revature.banking.services.security.models;

/**
 * Everything in user gets encrypted except the username and user_key. This
 * model is used as a bridge between the service layer and the database layer.
 * The DAO's will only deal with these encrypted models and the service layer
 * will decipher them and transform them into Users/BankAccount models suitable
 * for the frontend.
 * 
 * @author Jared F Bennatt
 *
 */
public class EncryptedUser {
	private String firstName;
	private String lastName;
	private String password;
	private String taxId;
	private int userKey;
	private String userName;

	public EncryptedUser() {
		super();
	}

	public EncryptedUser(int userKey, String taxId, String firstName, String lastName, String userName,
			String password) {
		super();
		this.userKey = userKey;
		this.taxId = taxId;
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
		EncryptedUser other = (EncryptedUser) obj;
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
		if (taxId == null) {
			if (other.taxId != null)
				return false;
		} else if (!taxId.equals(other.taxId))
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

	public String getTaxId() {
		return taxId;
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
		result = prime * result + ((taxId == null) ? 0 : taxId.hashCode());
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

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "EncryptedUser [userKey=" + userKey + ", taxId=" + taxId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userName=" + userName + ", password=" + password + "]";
	}
}
