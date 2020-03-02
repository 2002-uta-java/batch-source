package com.hylicmerit.models;

import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private String employee_email;
	private String password;
	private String name;
	private String bio;
	private String birthday;
	private String role;
	private String manager;

	public Employee() {
		super();
	}

	public Employee (
			String employee_email, String password, String name, 
			String bio, String birthday, String role, String manager) {
		super();
		this.employee_email = employee_email;
		this.password = password;
		this.name = name;
		this.bio = bio;
		this.birthday = birthday;
		this.role = role;
		this.manager = manager;
	}

	public String getEmployee_email() {
		return employee_email;
	}

	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employee_email=" + employee_email + ", manager=" + manager + ", password=" + password
				+ ", name=" + name + ", bio=" + bio + ", birthday=" + birthday + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee_email == null) ? 0 : employee_email.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
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
		if (employee_email == null) {
			if (other.employee_email != null)
				return false;
		} else if (!employee_email.equals(other.employee_email))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		return true;
	}
	
	
}
