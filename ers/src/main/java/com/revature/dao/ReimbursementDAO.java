package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDAO {
	List<Reimbursement> getAllReimbursements();
	Reimbursement getReimbursement(int id);
	void addReimbursement(Reimbursement reimbursement) throws Exception;
	void deleteReimbursement(int id) throws Exception;
	void updateReimbursement(Reimbursement reimbursement) throws Exception;
}
