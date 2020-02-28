package com.revature.jfbennatt.ers.models;

public class Employee {
	private int empId;
	private String firstName;
	private boolean isManager;
	private String lastName;
	private String password;
	private String token;

	public Employee() {
		super();
		firstName = "Joe";
		lastName = "Blow";
		token = "token";
	}

	public Employee(int empId, String firstName, String lastName, String password, String token, boolean isManager) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.token = token;
		this.isManager = isManager;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empId != other.empId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isManager != other.isManager)
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
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	public int getEmpId() {
		return empId;
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

	public String getToken() {
		return token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empId;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (isManager ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", token=" + token + ", isManager=" + isManager + "]";
	}
}
