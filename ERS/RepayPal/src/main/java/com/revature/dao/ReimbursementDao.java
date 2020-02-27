package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {

	public List<Reimbursement> getReimbursements();
	public Reimbursement getReimbursementByUsername(String username);
	public int createReimbursement(Reimbursement r);
	public int updateAccount(Reimbursement r);
	public int deleteAccount(Reimbursement r);
	public Reimbursement createReimbursementtWithDefaultManager(Reimbursement r);
}
