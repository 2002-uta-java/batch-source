package com.project1.models;

import java.io.Serializable;

public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int reimbId;
	private int empId;
	private double amount;
	private String purpose;
	private int approveBy;
	private String reimbStatus;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbId, int empId, int approveBy, double amount, String purpose, String reimbStatus) {
		super();
		this.reimbId = reimbId;
		this.empId = empId;
		this.approveBy = approveBy;
		this.amount = amount;
		this.purpose = purpose;
		this.reimbStatus = reimbStatus;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getApproveBy() {
		return approveBy;
	}

	public void setApproveBy(int approveBy) {
		this.approveBy = approveBy;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + approveBy;
		result = prime * result + empId;
		result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
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
		if (approveBy != other.approveBy)
			return false;
		if (empId != other.empId)
			return false;
		if (purpose == null) {
			if (other.purpose != null)
				return false;
		} else if (!purpose.equals(other.purpose))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", empId=" + empId + ", amount=" + amount + ", purpose=" + purpose
				+ ", approveBy=" + approveBy + ", reimbStatus=" + reimbStatus + "]";
	}

	

	

	
}
