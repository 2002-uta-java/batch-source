package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Reimbursement extends Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int remId;
	private String remType;
	private double remRequestedAmount;
	private String remRequestDate;
	private String remReciept;
	private String remNotes;
	private String remApprovedDate;
	private double remApprovedAmount;
	private String remComment;
	private String remStatus;
	
	public Reimbursement() {
		super();
		
	}

	public Reimbursement(int remId, String remType, double remRequestedAmount, String remRequestDate, String remReciept,
			String remNotes, String remApprovedDate, double remApprovedAmount, String remComment, String remStatus) {
		super();
		this.remId = remId;
		this.remType = remType;
		this.remRequestedAmount = remRequestedAmount;
		this.remRequestDate = remRequestDate;
		this.remReciept = remReciept;
		this.remNotes = remNotes;
		this.remApprovedDate = remApprovedDate;
		this.remApprovedAmount = remApprovedAmount;
		this.remComment = remComment;
		this.remStatus = remStatus;
	}

	public Reimbursement(int id, String type, double ramount, String rdate, String reciept, String notes, int eId,
			int mId, String adate, double amount, String comment, String status) {
		
	}

	public Reimbursement(String type, String rdate, double ramount, String reciept, String notes, int emplId,
			int managerId, String adate, double amount, String comment, String status) {
	}

	public Reimbursement(String type, String adate, double amount, String comment, String status) {
		
	}

	public int getRemId() {
		return remId;
	}

	public int setRemId(int remId) {
		return this.remId = remId;
	}

	public String getRemType() {
		return remType;
	}

	public String setRemType(String remType) {
		return this.remType = remType;
	}

	public double getRemRequestedAmount() {
		return remRequestedAmount;
	}

	public double setRemRequestedAmount(double remRequestedAmount) {
		return this.remRequestedAmount = remRequestedAmount;
	}

	public String getRemRequestDate() {
		return remRequestDate;
	}

	public String setRemRequestDate(String remRequestDate) {
		return this.remRequestDate = remRequestDate;
	}

	public String getRemReciept() {
		return remReciept;
	}

	public String setRemReciept(String remReciept) {
		return this.remReciept = remReciept;
	}

	public String getRemNotes() {
		return remNotes;
	}

	public String setRemNotes(String remNotes) {
		return this.remNotes = remNotes;
	}

	public String getRemApprovedDate() {
		return remApprovedDate;
	}

	public String setRemApprovedDate(String remApprovedDate) {
		return this.remApprovedDate = remApprovedDate;
	}

	public double getRemApprovedAmount() {
		return remApprovedAmount;
	}

	public double setRemApprovedAmount(double remApprovedAmount) {
		return this.remApprovedAmount = remApprovedAmount;
	}

	public String getRemComment() {
		return remComment;
	}

	public String setRemComment(String remComment) {
		return this.remComment = remComment;
	}

	public String getRemStatus() {
		return remStatus;
	}

	public String setRemStatus(String remStatus) {
		return this.remStatus = remStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(remApprovedAmount, remApprovedDate, remComment, remId, remNotes, remReciept, remRequestDate,
				remRequestedAmount, remStatus, remType);
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
		return Double.doubleToLongBits(remApprovedAmount) == Double.doubleToLongBits(other.remApprovedAmount)
				&& Objects.equals(remApprovedDate, other.remApprovedDate)
				&& Objects.equals(remComment, other.remComment) && remId == other.remId
				&& Objects.equals(remNotes, other.remNotes) && Objects.equals(remReciept, other.remReciept)
				&& Objects.equals(remRequestDate, other.remRequestDate)
				&& Double.doubleToLongBits(remRequestedAmount) == Double.doubleToLongBits(other.remRequestedAmount)
				&& Objects.equals(remStatus, other.remStatus) && Objects.equals(remType, other.remType);
	}

	@Override
	public String toString() {
		return "Reimbursement [remId=" + remId + ", remType=" + remType + ", remRequestedAmount=" + remRequestedAmount
				+ ", remRequestDate=" + remRequestDate + ", remReciept=" + remReciept + ", remNotes=" + remNotes
				+ ", remApprovedDate=" + remApprovedDate + ", remApprovedAmount=" + remApprovedAmount + ", remComment="
				+ remComment + ", remStatus=" + remStatus + ", getRemId()=" + getRemId() + ", getRemType()="
				+ getRemType() + ", getRemRequestedAmount()=" + getRemRequestedAmount() + ", getRemRequestDate()="
				+ getRemRequestDate() + ", getRemReciept()=" + getRemReciept() + ", getRemNotes()=" + getRemNotes()
				+ ", getRemApprovedDate()=" + getRemApprovedDate() + ", getRemApprovedAmount()="
				+ getRemApprovedAmount() + ", getRemComment()=" + getRemComment() + ", getRemStatus()=" + getRemStatus()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}

}
