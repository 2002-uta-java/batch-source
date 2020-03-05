package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImplementation;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private static Logger log = Logger.getRootLogger();
	
	
	ReimbursementDao reimbursementDao = new ReimbursementDaoImplementation();

	public List<Reimbursement> getReimbursement(String username) {
		return reimbursementDao.getReimbursementsByUsername(username);
	}

	public boolean createReimbursement(Reimbursement r) {
		int reimbursementCreated = reimbursementDao.createReimbursement(r);
		if(reimbursementCreated != 0) {
			log.info("Reimbursement creation successful");
			return true;
		}
		log.error("Unable to create, please try again\n");
		return false;
	}

	public boolean updateReimbursement(Reimbursement r, String managerUsername) {
		int reimbursementUpdated = reimbursementDao.updateReimbursement(r, managerUsername);
		log.debug(managerUsername);
		if(reimbursementUpdated != 0) {
			log.info("Reimbursement updated successful");
			return true;
		}
		log.error("Unable to update, please try again\n");
		return false;
	}

	public List<Reimbursement> getReimbursements() {
		return reimbursementDao.getReimbursements();
	}
	
	public Reimbursement getReimbursementById(int id) {
		return reimbursementDao.getReimbursementById(id);
	}

}
