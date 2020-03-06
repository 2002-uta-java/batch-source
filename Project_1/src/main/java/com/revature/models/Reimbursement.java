package com.revature.models;

public class Reimbursement {
	private int reimbersementId;	
	private int employeeId;
	private double amount;
	private String status;
	private String category;
	private String resolver; 
	
	public Reimbursement() {
		super();
		
	}
	public Reimbursement(int reimbersementId, int id, String status, String category, double amount, String resolver) {
		super();
		this.reimbersementId = reimbersementId; 
		this.employeeId = id;
		this.status = status; 
		this.category = category; 
		this.amount = amount;
		this.resolver = resolver;
	}
	
	public Reimbursement(int reimbersementId, int id, String status, double amount, String resolver, String category) {
		super();
		this.reimbersementId = reimbersementId; 
		this.employeeId = id;
		this.status = status; 
		this.category = category; 
		this.amount = amount;
		this.resolver = resolver;
	}
	
	public Reimbursement(int id, String category, double amount) {
		super();
		this.employeeId = id;
		this.category = category;
		this.amount = amount;
	}

	public int getReimbersementId() {
		return reimbersementId;
	}

	public void setReimbersementId(int reimbersementId) {
		this.reimbersementId = reimbersementId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + employeeId;
		result = prime * result + reimbersementId;
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
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
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (reimbersementId != other.reimbersementId)
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
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
		return "Reimbursement [reimbersementId=" + reimbersementId + ", employeeId=" + employeeId + ", amount=" + amount + ", status=" + status + ", category=" + category
				+ ", resolver=" + resolver + "]";
	}
}
