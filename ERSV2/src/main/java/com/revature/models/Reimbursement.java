package com.revature.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Reimbursement {

	// List of all the attributes in this entity
	private int reimbursementId;
	private Date date = new Date(System.currentTimeMillis());//Default it to current time
	private String description;
	private String category;
	private String cost;
	private String status;
	private String comments;
	private int employee_id;
	private String reviewed_by;
	private Timestamp reviewed_date;
	
	
	public String getReviewed_by() {
		return reviewed_by;
	}

	public void setReviewed_by(String reviewed_by) {
		this.reviewed_by = reviewed_by;
	}

	public Timestamp getReviewed_date() {
		return reviewed_date;
	}

	public void setReviewed_date(Timestamp reviewed_date) {
		this.reviewed_date = reviewed_date;
	}

	

	// Constructors with parameters
	public Reimbursement(Date date, String description, String category, String cost, String status, String comments,
			int employee_id) {
		this.date = date;
		this.description = description;
		this.category = category;
		this.cost = cost;
		this.status = status;
		this.comments = comments;
		this.employee_id = employee_id;		
	}

	// Constructors without parameters(Default)
	public Reimbursement() {
		super();
	}

	// Setters and Getters
	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public Date getDate() {
		return date;     
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	// toString
	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", date=" + date + ", description=" + description
				+ ", category=" + category + ", cost=" + cost + ", status=" + status + ", comments=" + comments
				+ ", employee_id=" + employee_id + "]";
	}
	
	//HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + employee_id;
		result = prime * result + reimbursementId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	//Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	



}
