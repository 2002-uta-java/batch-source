package com.revature.jfbennatt.ers.models;

import java.math.BigDecimal;
import java.util.Date;

public class Reimbursement {
	private BigDecimal amount;
	private String description;
	private int emplId;
	private Date reimbDate;
	private int reimbId;
	private Date replyDate;
	private int status;
	private Date submitDate;

	public Reimbursement() {
		super();
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (emplId != other.emplId)
			return false;
		if (reimbDate == null) {
			if (other.reimbDate != null)
				return false;
		} else if (!reimbDate.equals(other.reimbDate))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (replyDate == null) {
			if (other.replyDate != null)
				return false;
		} else if (!replyDate.equals(other.replyDate))
			return false;
		if (status != other.status)
			return false;
		if (submitDate == null) {
			if (other.submitDate != null)
				return false;
		} else if (!submitDate.equals(other.submitDate))
			return false;
		return true;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public int getEmplId() {
		return emplId;
	}

	public Date getReimbDate() {
		return reimbDate;
	}

	public int getReimbId() {
		return reimbId;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public int getStatus() {
		return status;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + emplId;
		result = prime * result + ((reimbDate == null) ? 0 : reimbDate.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((replyDate == null) ? 0 : replyDate.hashCode());
		result = prime * result + status;
		result = prime * result + ((submitDate == null) ? 0 : submitDate.hashCode());
		return result;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public void setReimbDate(Date reimbDate) {
		this.reimbDate = reimbDate;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
}
