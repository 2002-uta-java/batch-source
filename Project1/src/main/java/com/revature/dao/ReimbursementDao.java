package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {

	public List<Reimbursement> getReimbursement();
	public int getReimbursementId(int id);
	public Reimbursement createReimbursementByFunction(Reimbursement r1);
	public Reimbursement updateReimbursementByFunction(Reimbursement r1);
	public int deleteReimbursement(int id);
	public Reimbursement generateReportReimbursementByEmployee(Reimbursement r1);
	public Reimbursement generateReportReimbursementByManager(Reimbursement r1);
	public String getStatus(int id);
	public String getReciept(int id);
	public String updateReciept(String url, int id);

	
}
