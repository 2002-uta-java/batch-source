package com.revature.daos;

import java.sql.Connection;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbursementService;

public interface ReimbursementDao {
	
	List<Reimbursement> getAll();
	List<Reimbursement> getAllReimbursementByStatus(String status);	
	Reimbursement getById(int id);
	int createReimbursement(Reimbursement r);
	int updateReimbursement(Reimbursement r);
	List<Reimbursement> getAllReimbursementByUserId(int employeeId);
}
