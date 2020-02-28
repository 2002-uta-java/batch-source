package com.hylicmerit.models;

import java.io.Serializable;

public class Reimbursement implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String employee_email;
	private String manager_email;
	private String status;
	private String description;
	
	public Reimbursement() {
		super();
	}
	public Reimbursement(int id, String employee_email, String manager_email, String status, String description) {
		super();
		this.id = id;
		this.employee_email = employee_email;
		this.manager_email = manager_email;
		this.status = status;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployee_email() {
		return employee_email;
	}
	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}
	public String getManager_email() {
		return manager_email;
	}
	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee_email == null) ? 0 : employee_email.hashCode());
		result = prime * result + id;
		result = prime * result + ((manager_email == null) ? 0 : manager_email.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (employee_email == null) {
			if (other.employee_email != null)
				return false;
		} else if (!employee_email.equals(other.employee_email))
			return false;
		if (id != other.id)
			return false;
		if (manager_email == null) {
			if (other.manager_email != null)
				return false;
		} else if (!manager_email.equals(other.manager_email))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employee_email=" + employee_email + ", manager_email=" + manager_email
				+ ", status=" + status + "]";
	}
	
	
}
