package com.revature.models;

import java.io.Serializable;

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int profile;
	private String department;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String address;
	
	
	public Employee() {
		super();
	}
	
	public Employee(int profile, String department, String firstName, String lastName, String phone, String email, String address) {
		this.profile = profile;
		this.department = department;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getProfile() {
		return profile;
	}


	public void setProfile(int profile) {
		this.profile = profile;
	}


	public String getDepartment() {
		return department;
	}
	
	public int getDepartmentId() {
		switch (department) {
		case "Human Resources":
			return 1;
		case "Accounting":
			return 2;
		case "Payroll":
			return 3;
		case "Marketing":
			return 4;
		case "Sales":
			return 5;
		case "Customer Success":
			return 6;
		case "Development":
			return 7;
		case "Design":
			return 8;
		case "Quality Assurance":
			return 9;
		case "Information Technology":
			return 10;
		case "Facilities":
			return 11;
		default:
			return 0;
		}
	}

	public void setDepartmentById(int department) {
		switch (department) {
		case 1:
			this.department = "Human Resources";
			break;
		case 2:
			this.department = "Accounting";
			break;
		case 3:
			this.department = "Payroll";
			break;
		case 4:
			this.department = "Marketing";
			break;
		case 5:
			this.department = "Sales";
			break;
		case 6:
			this.department = "Customer Success";
			break;
		case 7:
			this.department = "Development";
			break;
		case 8:
			this.department = "Design";
			break;
		case 9:
			this.department = "Quality Assurance";
			break;
		case 10:
			this.department = "Information Technology";
			break;
		case 11:
			this.department = "Facilities";
			break;
		default:
			this.department = "ERROR";
		}
	}

	public void setDepartment(String department) {
		this.department = department;
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + profile;
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
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (profile != other.profile)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", profile=" + profile + ", department=" + department + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", address=" + address + "]";
	}

}
