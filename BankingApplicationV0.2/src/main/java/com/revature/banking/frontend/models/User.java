package com.revature.banking.frontend.models;

import java.util.List;

public class User {
	private List<BankAccount> accounts;
	private String encryptedPassword;
	private String firstName;
	private String lastName;
	private String taxId;
	private String username;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String taxId, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.taxId = taxId;
		this.username = username;
		this.encryptedPassword = password;
	}

	public void setAccounts(final List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return encryptedPassword;
	}

	public String getTaxId() {
		return taxId;
	}

	public String getUsername() {
		return username;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.encryptedPassword = password;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taxId == null) ? 0 : taxId.hashCode());
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
		User other = (User) obj;
		if (taxId == null) {
			if (other.taxId != null)
				return false;
		} else if (!taxId.equals(other.taxId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", taxId=" + taxId + ", username=" + username
				+ ", password=" + encryptedPassword + "]";
	}
}
