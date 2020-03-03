package com.revature.models;

import java.io.Serializable;

public class Discussion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int id;
	int reimbursement;
	String body;
	int employee;
	String date;
	
	public Discussion() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(int reimbursement) {
		this.reimbursement = reimbursement;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + employee;
		result = prime * result + id;
		result = prime * result + reimbursement;
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
		Discussion other = (Discussion) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employee != other.employee)
			return false;
		if (id != other.id)
			return false;
		if (reimbursement != other.reimbursement)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Discussion [id=" + id + ", reimbursement=" + reimbursement + ", body=" + body + ", employee=" + employee
				+ ", date=" + date + "]";
	}
	
}
