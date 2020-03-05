package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {

	public List<Reimbursement> getReimbursement();
	public List<Reimbursement> getReimbursementId(int id);
	public Reimbursement createReimbursementByFunction(Reimbursement r1);
	public Reimbursement updateReimbursementByFunction(Reimbursement r1);
	public int deleteReimbursement(int id);
	public String getStatus(int id);
	public String getReciept(int id);
	public String updateReciept(String url, int id);
	public List<Reimbursement> managerStatus(int id);
	public List<Reimbursement> employeeStatus(int id);
	public List<Reimbursement> reimbursementByType(String type1);
	
}
