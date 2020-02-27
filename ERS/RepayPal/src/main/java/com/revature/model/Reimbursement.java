package com.revature.model;

import java.io.Serializable;

public class Reimbursement implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String managerUsername;
	private String employeeUsername;
	private String status;
	private double amount;
	private String description;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int id, String managerUsername, String employeeUsername, String status, double amount,
			String description) {
		super();
		this.id = id;
		this.managerUsername = managerUsername;
		this.employeeUsername = employeeUsername;
		this.status = status;
		this.amount = amount;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}

	public String getEmployeeUsername() {
		return employeeUsername;
	}

	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employeeUsername == null) ? 0 : employeeUsername.hashCode());
		result = prime * result + id;
		result = prime * result + ((managerUsername == null) ? 0 : managerUsername.hashCode());
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
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employeeUsername == null) {
			if (other.employeeUsername != null)
				return false;
		} else if (!employeeUsername.equals(other.employeeUsername))
			return false;
		if (id != other.id)
			return false;
		if (managerUsername == null) {
			if (other.managerUsername != null)
				return false;
		} else if (!managerUsername.equals(other.managerUsername))
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
		return "Reimbursement [id=" + id + ", managerUsername=" + managerUsername + ", employeeUsername="
				+ employeeUsername + ", status=" + status + ", amount=" + amount + ", description=" + description + "]";
	}
	
}
