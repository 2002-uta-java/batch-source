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
	
	public int getReimbursementId(int id) {
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
	public Reimbursement generateReportReimbursementByEmployee(Reimbursement r1) {
		return reimbursementDao.generateReportReimbursementByEmployee(r1);
	}
	public Reimbursement generateReportReimbursementByManager(Reimbursement r1) {
		return reimbursementDao.generateReportReimbursementByManager(r1);
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

}
