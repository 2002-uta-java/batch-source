package com.project1.services;

import java.util.List;

import com.project1.daos.ReimbursementDao;
import com.project1.daos.ReimbursementDaoImpl;
import com.project1.models.Reimbursement;


public class ReimbursementService {
	
	private ReimbursementDao reimbDao = new ReimbursementDaoImpl();
	
	public List<Reimbursement> getReimbursementByEmpId(int id) {
		return reimbDao.getReimbursementByEmpId(id);
	}
	public boolean updateReimbursement(Reimbursement r) {
		int reimbUpdated = reimbDao.updateReimbursement(r);
		if (reimbUpdated != 0) {
			return true;
		}
		return false;
	}
	public Reimbursement createReimbWithFunction(Reimbursement r) {
		return reimbDao.createReimbWithFunction(r);
	}
	
	public List<Reimbursement> getEmpReimbByIdResolve(int id){
		return reimbDao.getEmpReimbByIdResolve(id);
	}
	
	public List<Reimbursement> getEmpReimbByIdPending(int id){
		return reimbDao.getEmpReimbByIdPending(id);
	}
	
	// for manager
	public List<Reimbursement> getAllReimbByStatus(String status){
		return reimbDao.getAllReimbByStatus(status);
		
	}
	
	public Reimbursement getReimbByID(int id){
		return reimbDao.getReimbById(id);
		
	}
}
