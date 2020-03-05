package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImp;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
private ReimbursementDao rd = new ReimbursementDaoImp();
	
	public List<Reimbursement> getAll(){
		return rd.getAll();
	}
	
	public List<Reimbursement> getAllReimbursementByStatus(String status){
		return rd.getAllReimbursementByStatus(status);
	}
	
	public Reimbursement getById(int id) {
		return rd.getById(id);
	}
	
	public boolean createReimbursement(Reimbursement r) {
		int numRowsAffected = rd.createReimbursement(r);
		if(numRowsAffected != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateReimbursement(Reimbursement r) {
		int numRowsAffected = rd.updateReimbursement(r);
		if(numRowsAffected != 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Reimbursement> getAllReimbursementByUserId(int employeeId){
		return rd.getAllReimbursementByUserId(employeeId);
	}
}