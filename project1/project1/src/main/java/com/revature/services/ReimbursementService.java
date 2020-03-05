package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public class ReimbursementService {

	ReimbursementDao rd = new ReimbursementDaoImpl();

	public List<Reimbursement> getMyReimbursementsByStatus(Employee e, String status) {
		return rd.getAllReimbursements(e, status);
	}
	
	public List<Reimbursement> getMyReimbursementsByStatus(String status) {
		return rd.getAllReimbursements(status);
	}
	
	public List<Reimbursement> getEmployeeReimbursements(Employee e) {
		return rd.getAllReimbursements(e);
	}
	
	public int createReimbursement(Employee e, double expense, String rtype) {
		return rd.createReimbursement(e, expense, rtype);
	}
	
	public int updateReimbursement(int rid, String resolution, int eid) {
		return rd.updateReimbursement(rid, resolution, eid);
	}
}
