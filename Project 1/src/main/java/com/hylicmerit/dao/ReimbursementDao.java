package com.hylicmerit.dao;

import java.util.List;

import com.hylicmerit.models.Reimbursement;

public interface ReimbursementDao {
	List<Reimbursement> getAll();
	
	List<Reimbursement> getAllByEmployee(String email);
	
	List<Reimbursement> getAllByManager(String email);
	
	Reimbursement getById(int id);
	
	int createReimbursement(Reimbursement r);
	
	int updateReimbursement(Reimbursement r);
	
	int deleteReimbursement(Reimbursement r);
}
