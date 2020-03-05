package com.project1.daos;

import java.util.List;

import com.project1.models.Employee;
import com.project1.models.Reimbursement;

public interface ReimbursementDao {
	public int updateReimbursement(Reimbursement r);
	public Reimbursement getReimbById(int id);
	public Reimbursement createReimbWithFunction(Reimbursement r);
	public List<Reimbursement> getEmpReimbByIdPending(int id);
	public List<Reimbursement> getEmpReimbByIdResolve(int id);
	
	// for manager
	public List<Reimbursement> getAllReimbByStatus(String status);
	public List<Reimbursement> getReimbursementByEmpId(int id);
	
}
