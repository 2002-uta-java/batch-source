package com.revature.models;

public class Reimbursement {

	private int id;
	private String purpose;
	private float amount;
	private int idEmployee;
	private int idManager;
	private String status;
	
	public Reimbursement(int id, String purpose, float amount, int idEmployee, int idManager, String status){
		this.id = id;
		this.purpose = purpose;
		this.amount = amount;
		this.idEmployee = idEmployee;
		this.idManager = idManager;
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setEmailEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public int getIdManager() {
		return idManager;
	}

	public void setIdManager(int idManager) {
		this.idManager = idManager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
