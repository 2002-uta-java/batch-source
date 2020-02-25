package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String title;
	private int managerId;
	private int reportsToId;
	
	public Employee() {
		super();
	}

	public Employee(int employeeId, String firstName, String lastName, String email, String title, int managerId,
			int reportsToId) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.title = title;
		this.managerId = managerId;
		this.reportsToId = reportsToId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public int setEmployeeId(int employeeId) {
		return this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String setFirstName(String firstName) {
		return this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String setLastName(String lastName) {
		return this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public String setEmail(String email) {
		return this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public String setTitle(String title) {
		return this.title = title;
	}

	public int getManagerId() {
		return managerId;
	}

	public int setManagerId(int managerId) {
		return this.managerId = managerId;
	}

	public int getReportsToId() {
		return reportsToId;
	}

	public int setReportsToId(int reportsToId) {
		return this.reportsToId = reportsToId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, employeeId, firstName, lastName, managerId, reportsToId, title);
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
		return Objects.equals(email, other.email) && employeeId == other.employeeId
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& managerId == other.managerId && reportsToId == other.reportsToId
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", title=" + title + ", managerId=" + managerId + ", reportsToId=" + reportsToId
				+ ", getEmployeeId()=" + getEmployeeId() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getEmail()=" + getEmail() + ", getTitle()=" + getTitle() + ", getManagerId()="
				+ getManagerId() + ", getReportsToId()=" + getReportsToId() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}

}
