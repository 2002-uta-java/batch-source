package com.hylicmerit.service;

import java.util.List;

import com.hylicmerit.dao.ReimbursementDao;
import com.hylicmerit.dao.ReimbursementDaoImpl;
import com.hylicmerit.models.Reimbursement;

public class ReimbursementService {
	private ReimbursementDao rd = new ReimbursementDaoImpl();
	
	public List<Reimbursement> getAll(){
		return rd.getAll();
	}
	
	public List<Reimbursement> getAllByEmployee(String email){
		return rd.getAllByEmployee(email);
	}
	
	public List<Reimbursement> getAllByManager(String email){
		return rd.getAllByManager(email);
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
	
	public boolean deleteReimbursement(Reimbursement r) {
		int numRowsAffected = rd.deleteReimbursement(r);
		if(numRowsAffected != 0) {
			return true;
		} else {
			return false;
		}
	}

}
