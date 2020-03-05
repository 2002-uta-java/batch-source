package com.dean.daos;

import java.util.List;

import com.dean.models.Reimbursement;

public interface ReimbursementDao {
	int createReimbursement(Reimbursement reimbursement);
	int approveReimbursementByErbId(int id);
	int denyReimbursementByErbId(int id);
	
	Reimbursement getReimbursementById(int id);
	

	List<Reimbursement> getAllDeniedReimbursements();
	List<Reimbursement> getAllPendingReimbursementsByEmployeeId(int id);
	List<Reimbursement> getAllResolvedReimbursementsEmployeeId(int id);
	List<Reimbursement> getReimbursementsByEmployeeId(int id);
	List<Reimbursement> getAllPendingReimbursements();
	List<Reimbursement> getAllResolvedReimbursements();
	
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getReimbursementsByEmployeeUsername(String username);
}
