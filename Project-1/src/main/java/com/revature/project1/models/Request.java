package com.revature.project1.models;

import java.io.Serializable;

public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private String amount;
	private String reason;
	private boolean pending;
	private boolean approval;

	public Request() {
		super();
	}

	public Request(int id, int user_id, String amount, String reason, boolean pending, boolean approval) {
		super();
		this.id = id;
		this.userId = user_id;
		this.amount = amount;
		this.reason = reason;
		this.pending = pending;
		this.approval = approval;
	}

	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int user_id) {
		this.userId = user_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	// hashcode and equals

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + (approval ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + (pending ? 1231 : 1237);
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + userId;
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (approval != other.approval)
			return false;
		if (id != other.id)
			return false;
		if (pending != other.pending)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
