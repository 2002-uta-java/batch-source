package com.revature.models;

import java.io.Serializable;

public class Reimbursement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int batch;
	private String dateCreated;
	private int employee;
	private String category;
	private double amount;
	private String status;
	private int approvedBy;
	private String employeeName;
	private String approvedByName;
	
	
	public Reimbursement() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBatch() {
		return batch;
	}


	public void setBatch(int batch) {
		this.batch = batch;
	}


	public String getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}


	public int getEmployee() {
		return employee;
	}


	public void setEmployee(int e) {
		this.employee = e;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getCategoryId() {
		switch(this.category) {
			case "Office Supplies":
				return 1;
			case "Office Equipment":
				return 2;
			case "Meal":
				return 3;
			case "Gasoline/Milage":
				return 4;
			case "Transportation":
				return 5;
			case "Lodging":
				return 6;
			case "Proffesional Development":
				return 7;
			case "Relocation":
				return 8;
			case "Miscellaneous":
				return 9;
			default:
				return 0;
		}
	}
	
	public void setCategoryById(int category) {
		switch(category) {
			case 1:
				this.category = "Office Supplies";
				break;
			case 2:
				this.category = "Office Equipment";
				break;
			case 3:
				this.category = "Meal";
				break;
			case 4:
				this.category = "Gasoline/Milage";
				break;
			case 5:
				this.category = "Transportation";
				break;
			case 6:
				this.category = "Lodging";
				break;
			case 7:
				this.category = "Proffesional Development";
				break;
			case 8:
				this.category = "Relocation";
				break;
			case 9:
				this.category = "Miscellaneous";
				break;
			default:
				this.category = "Mistakes were made...";
		}
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getStatusId() {
		switch(this.status) {
			case "pending":
				return 1;
			case "reviewing":
				return 2;
			case "information needed":
				return 3;
			case "approved":
				return 4;
			case "denied":
				return 5;
			default:
				return 0;
		}
	}
	
	public void setStatusById(int status) {
		switch(status) {
			case 1:
				this.status = "pending";
				break;
			case 2:
				this.status = "reviewing";
				break;
			case 3:
				this.status = "information needed";
				break;
			case 4:
				this.status = "approved";
				break;
			case 5:
				this.status = "denied";
				break;
			default:
				this.status = "Mistakes were made";
		}
	}


	public int getApprovedBy() {
		return approvedBy;
	}


	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getApprovedByName() {
		return approvedByName;
	}


	public void setApprovedByName(String approvedByName) {
		this.approvedByName = approvedByName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((approvedByName == null) ? 0 : approvedByName.hashCode());
		result = prime * result + approvedBy;
		result = prime * result + batch;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + employee;
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + id;
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
		if (approvedByName == null) {
			if (other.approvedByName != null)
				return false;
		} else if (!approvedByName.equals(other.approvedByName))
			return false;
		if (approvedBy != other.approvedBy)
			return false;
		if (batch != other.batch)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (employee != other.employee)
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (id != other.id)
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
		return "Reimbursement [id=" + id + ", batch=" + batch + ", dateCreated=" + dateCreated + ", employee="
				+ employee + ", category=" + category + ", amount=" + amount + ", status=" + status + ", approvedBy="
				+ approvedBy + ", employeeName=" + employeeName + ", approveByName=" + approvedByName + "]";
	}

}
