package com.revature.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private enum AccountType {EMPLOYEE, MANAGER}
	
	private int id;
	private LocalDateTime accountCreated;
	private String name;
	private String email;
	private String password;
	private AccountType acctType;
	private int managerId;
	
	public Account() {
		super();
	}

	public Account(LocalDateTime accountCreated, String name, String email, String password, AccountType acctType,
			int managerId) {
		super();
		this.accountCreated = accountCreated;
		this.name = name;
		this.email = email;
		this.password = password;
		this.acctType = acctType;
		this.managerId = managerId;
	}
	
	public Account(LocalDateTime accountCreated, String name, String email, String password, String acctType,
			int managerId) {
		super();
		this.accountCreated = accountCreated;
		this.name = name;
		this.email = email;
		this.password = password;
		this.acctType = AccountType.valueOf(acctType);
		this.managerId = managerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getAccountCreated() {
		return accountCreated;
	}

	public void setAccountCreated(LocalDateTime accountCreated) {
		this.accountCreated = accountCreated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAcctType() {
		return acctType.toString();
	}

	public void setAcctType(String acctType) {
		this.acctType = AccountType.valueOf(acctType);
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountCreated == null) ? 0 : accountCreated.hashCode());
		result = prime * result + ((acctType == null) ? 0 : acctType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + managerId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Account other = (Account) obj;
		if (accountCreated == null) {
			if (other.accountCreated != null)
				return false;
		} else if (!accountCreated.equals(other.accountCreated))
			return false;
		if (acctType != other.acctType)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (managerId != other.managerId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountCreated=" + accountCreated + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", acctType=" + acctType + ", managerId=" + managerId + "]";
	}

}
