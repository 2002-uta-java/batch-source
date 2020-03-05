package com.dean.models;

import java.io.Serializable;
import java.util.Date;

public class Reimbursement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int reimbursementId;
	private String reimbursementDescription;
	private float reimbursementAmount;
	private String reimbursementStatus;
	private int managerId;
	private int employeeId;
	private Employee employee;
	private Employee manager;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbursementId, String reimbursementDescription, float reimbursementAmount,
			String reimbursementStatus, int managerId, int employeeId) {
		super();
		this.reimbursementId = reimbursementId;
		this.reimbursementDescription = reimbursementDescription;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementStatus = reimbursementStatus;
		this.managerId = managerId;
		this.employeeId = employeeId;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public String getReimbursementDescription() {
		return reimbursementDescription;
	}

	public void setReimbursementDescription(String reimbursementDescription) {
		this.reimbursementDescription = reimbursementDescription;
	}

	public float getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(float reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public String getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + managerId;
		result = prime * result + Float.floatToIntBits(reimbursementAmount);
		result = prime * result + ((reimbursementDescription == null) ? 0 : reimbursementDescription.hashCode());
		result = prime * result + reimbursementId;
		result = prime * result + ((reimbursementStatus == null) ? 0 : reimbursementStatus.hashCode());
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
		if (employeeId != other.employeeId)
			return false;
		if (managerId != other.managerId)
			return false;
		if (Float.floatToIntBits(reimbursementAmount) != Float.floatToIntBits(other.reimbursementAmount))
			return false;
		if (reimbursementDescription == null) {
			if (other.reimbursementDescription != null)
				return false;
		} else if (!reimbursementDescription.equals(other.reimbursementDescription))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (reimbursementStatus == null) {
			if (other.reimbursementStatus != null)
				return false;
		} else if (!reimbursementStatus.equals(other.reimbursementStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", reimbursementDescription="
				+ reimbursementDescription + ", reimbursementAmount=" + reimbursementAmount + ", reimbursementStatus="
				+ reimbursementStatus + ", managerId=" + managerId + ", employeeId=" + employeeId + "]";
	}
	
	
	
}
