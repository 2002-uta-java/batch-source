package com.revature.models;

public class Reimbursement {

	private int id;
	private String purpose;
	private int amount;
	private String emailEmployee;
	private String emailManager;
	private String status;
	
	public Reimbursement(int id, String purpose, int amount, String emailEmployee, String emailManager, String status){
		this.id = id;
		this.purpose = purpose;
		this.amount = amount;
		this.emailEmployee = emailEmployee;
		this.emailManager = emailManager;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getEmailEmployee() {
		return emailEmployee;
	}

	public void setEmailEmployee(String emailEmployee) {
		this.emailEmployee = emailEmployee;
	}

	public String getEmailManager() {
		return emailManager;
	}

	public void setEmailManager(String emailManager) {
		this.emailManager = emailManager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
