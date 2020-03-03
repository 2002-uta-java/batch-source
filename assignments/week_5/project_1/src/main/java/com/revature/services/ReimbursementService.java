package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	private ReimbursementDao rd = new ReimbursementDaoImpl();
	
	// calls dao to create a new reimbursement. Returns a reimbursement object
	public Reimbursement createReimbursement(Reimbursement r) {
		return rd.createReimbursement(r);
	}
	
	// calls doa to find a reimbursement in the database that matches id. Returns reimbursement object
	public Reimbursement getReimbursementById(int id) {
		return rd.getReimbursementById(id);
	}
	
	// calls dao to update information regarding a reimbursement (where id matches). Returns int to check for success
	// if reimbursement.status is to be set to approved, it calls a different dao method
	public int updateReimbursement(Reimbursement r) {
		
		if (r.getStatusId() == 4) {
			return rd.approveReimbursement(r);
		} else {
			return rd.updateReimbursement(r);
		}
	}
	
	// calls dao to retrieve a list of all reimbursement
	public List<Reimbursement> getAllReimbursements(Employee e) {
		if (e != null) {
			return rd.getAllReimbursementsForEmployee(e);
		} else {
			return rd.getAllReimbursements();
		}
	}

}
