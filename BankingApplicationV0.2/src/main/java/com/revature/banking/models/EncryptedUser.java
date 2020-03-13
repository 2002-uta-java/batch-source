package com.revature.banking.models;

public class EncryptedUser {

	private String password;
	private String firstName;
	private String lastName;
	private int rowKey;
	private String taxId;
	private String username;

	public EncryptedUser() {
		super();
	}

	public EncryptedUser(String encryptedPassword, String firstName, String lastName, String taxId, String username,
			int rowKey) {
		super();
		this.password = encryptedPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.taxId = taxId;
		this.username = username;
		this.rowKey = rowKey;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRowKey() {
		return rowKey;
	}

	public String getTaxId() {
		return taxId;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String encryptedPassword) {
		this.password = encryptedPassword;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public void setUsername(String username) {
		this.username = username;
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
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
		if (rowKey != other.rowKey)
			return false;
		if (taxId == null) {
			if (other.taxId != null)
				return false;
		} else if (!taxId.equals(other.taxId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + rowKey;
		result = prime * result + ((taxId == null) ? 0 : taxId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "EncryptedUser [encryptedPassword=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", taxId=" + taxId + ", username=" + username + ", rowKey=" + rowKey + "]";
	}
}
