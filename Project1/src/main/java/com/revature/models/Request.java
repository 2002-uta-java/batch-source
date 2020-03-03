package com.revature.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Request implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private enum Status {PENDING, APPROVED, DENIED};
	private enum Category {TRAVEL, EDUCATION, SUPPLIES};
	
	private int id;
	private LocalDateTime submitted;
	private LocalDateTime reviewed;
	private Status status;
	private int emplId;
	private LocalDate reimburseDate;
	private double amount;
	private String description;
	private String imageUrl;
	private Category category;
	
	public Request() {
		super();
	}

	public Request(LocalDateTime submitted, String status, int emplId, LocalDate reimburseDate, double amount, String description,
			String imageUrl, String category) {
		super();
		this.submitted = submitted;
		this.status = Status.valueOf(status);
		this.emplId = emplId;
		this.reimburseDate = reimburseDate;
		this.amount = amount;
		this.description = description;
		this.imageUrl = imageUrl;
		this.category = Category.valueOf(category);
	}

	public Request(LocalDateTime submitted, LocalDateTime reviewed, Status status, int emplId, LocalDate reimburseDate, double amount,
			String description, String imageUrl) {
		super();
		this.submitted = submitted;
		this.reviewed = reviewed;
		this.status = status;
		this.emplId = emplId;
		this.reimburseDate = reimburseDate;
		this.amount = amount;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getSubmitted() {
		return submitted;
	}

	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}

	public LocalDateTime getReviewed() {
		return reviewed;
	}

	public void setReviewed(LocalDateTime reviewed) {
		this.reviewed = reviewed;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}

	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public LocalDate getReimburseDate() {
		return reimburseDate;
	}

	public void setReimburseDate(LocalDate reimburseDate) {
		this.reimburseDate = reimburseDate;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = Category.valueOf(category);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + emplId;
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((reimburseDate == null) ? 0 : reimburseDate.hashCode());
		result = prime * result + ((reviewed == null) ? 0 : reviewed.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
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
		Request other = (Request) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (emplId != other.emplId)
			return false;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (reimburseDate == null) {
			if (other.reimburseDate != null)
				return false;
		} else if (!reimburseDate.equals(other.reimburseDate))
			return false;
		if (reviewed == null) {
			if (other.reviewed != null)
				return false;
		} else if (!reviewed.equals(other.reviewed))
			return false;
		if (status != other.status)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", submitted=" + submitted + ", reviewed=" + reviewed + ", status=" + status
				+ ", emplId=" + emplId + ", reimburseDate=" + reimburseDate + ", amount=" + amount + ", description="
				+ description + ", imageUrl=" + imageUrl + ", category=" + category + "]";
	}
	
}
