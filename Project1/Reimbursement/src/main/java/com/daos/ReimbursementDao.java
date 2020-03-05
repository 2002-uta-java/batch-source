package com.daos;

import java.util.List;

import com.models.Reimbursement;
	
public interface ReimbursementDao {

	public Reimbursement getReimbursementByRId(int RId);
	public List<Reimbursement> getReimbursements();
	public List<Reimbursement> getReimbursementsByEmpId(int empId);
	public int createReimbursement(Reimbursement reimb);
	public int updateReimbursement(Reimbursement reimb);
}