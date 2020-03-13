package com.revature.jfbennatt.ers.models.hbm;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {
	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "empl_id")
	private Employee employee;

	@Column(name = "reimb_date")
	private Date reimbDate;

	@Id
	@SequenceGenerator(name = "reimbursements_reimb_id_seq", sequenceName = "reimbursements_reimb_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reimbursements_reimb_id_seq")
	@Column(name = "reimb_id")
	private int reimbId;

	@Column(name = "reply_date")
	private Date replyDate;

	@Column(name = "reimb_status")
	private int status;

	@Transient
	private String statusString;

	public Reimbursement() {
		super();
	}

	@Override
	public String toString() {
		return "Reimbursement [amount=" + amount + ", description=" + description + ", employee=" + employee
				+ ", reimbDate=" + reimbDate + ", reimbId=" + reimbId + ", replyDate=" + replyDate + ", status="
				+ status + ", statusString=" + statusString + ", submitDate=" + submitDate + ", manager=" + manager
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((reimbDate == null) ? 0 : reimbDate.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((replyDate == null) ? 0 : replyDate.hashCode());
		result = prime * result + status;
		result = prime * result + ((statusString == null) ? 0 : statusString.hashCode());
		result = prime * result + ((submitDate == null) ? 0 : submitDate.hashCode());
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee)) {
			return false;
		}
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager)) {
			return false;
		}
		if (reimbDate == null) {
			if (other.reimbDate != null)
				return false;
		} else if (!reimbDate.equals(other.reimbDate)) {
			return false;
		}
		if (reimbId != other.reimbId)
			return false;
		if (replyDate == null) {
			if (other.replyDate != null)
				return false;
		} else if (!replyDate.equals(other.replyDate)) {
			return false;
		}
		if (status != other.status)
			return false;
		if (statusString == null) {
			if (other.statusString != null)
				return false;
		} else if (!statusString.equals(other.statusString)) {
			return false;
		}
		if (submitDate == null) {
			if (other.submitDate != null)
				return false;
		} else if (!submitDate.equals(other.submitDate)) {
			return false;
		}
		return true;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getReimbDate() {
		return reimbDate;
	}

	public void setReimbDate(Date reimbDate) {
		this.reimbDate = reimbDate;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	@Column(name = "submit_date")
	private Date submitDate;

	@ManyToOne
	@JoinColumn(name = "reimb_man_id")
	private Employee manager;
}
