package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {

	public List<Reimbursement> getReimbursements();
	public List<Reimbursement> getReimbursementsByUsername(String username);
	public int createReimbursement(Reimbursement r);
	public int updateReimbursement(Reimbursement r, String managerUsername);
	public int deleteReimbursement(Reimbursement r);
	public Reimbursement createReimbursementWithDefaultManager(Reimbursement r);
}
