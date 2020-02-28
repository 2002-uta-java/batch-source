package com.hylicmerit.dao;

import java.util.List;

import com.hylicmerit.models.Reimbursement;

public interface ReimbursementDao {
	List<Reimbursement> getAll();
	
	Reimbursement getById(int id);
	
	int createReimbursement(Reimbursement r);
	
	int updateReimbursement(Reimbursement r);
	
	int deleteReimbursement(Reimbursement r);
}
