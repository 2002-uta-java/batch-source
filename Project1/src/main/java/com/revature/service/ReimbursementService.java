package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;

public class ReimbursementService {
	
	private ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
	
	public ReimbursementService() {
		super();
	}
	
	public List<Reimbursement> getReimbursement() {
		return reimbursementDao.getReimbursement();
	}
	
	public List<Reimbursement> getReimbursementId(int id) {
		return reimbursementDao.getReimbursementId(id);
	}
	
	public Reimbursement createReimbursementByFunction(Reimbursement r1) {
		return reimbursementDao.createReimbursementByFunction(r1);
	}
	
	public Reimbursement updateReimbursementByFunction(Reimbursement r1) {
		return reimbursementDao.updateReimbursementByFunction(r1);
	}
	
	public int deleteReimbursement(int id) {
		return reimbursementDao.deleteReimbursement(id);
	}
	public String getStatus(int id) {
		return reimbursementDao.getStatus(id);
	}
	
	public String getReciept(int id) {
		return reimbursementDao.getReciept(id);
	}
	
	public String updateReciept(String url, int id) {
		return reimbursementDao.updateReciept(url, id);	
	}

	public List<Reimbursement> managerStatus(int id) {
		return reimbursementDao.managerStatus(id);
	}
	
	public List<Reimbursement> employeeStatus(int id) {
		return reimbursementDao.employeeStatus(id);
	}

	public List<Reimbursement> remByType(String type1) {
		return reimbursementDao.reimbursementByType(type1);
	}
}
