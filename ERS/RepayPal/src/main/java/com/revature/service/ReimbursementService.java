package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImplementation;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	ReimbursementDao reimbursementDao = new ReimbursementDaoImplementation();

	public List<Reimbursement> getReimbursement(String username) {
		return reimbursementDao.getReimbursementsByUsername(username);
	}

	public boolean createReimbursement(Reimbursement r) {
		int reimbursementCreated = reimbursementDao.createReimbursement(r);
		if(reimbursementCreated != 0) {
			System.out.println("Reimbursement creation successful");
			return true;
		}
		System.out.println("Unable to create, please try again\n");
		return false;
	}

	public boolean updateReimbursement(Reimbursement r, String managerUsername) {
		int reimbursementUpdated = reimbursementDao.updateReimbursement(r, managerUsername);
		System.out.println(managerUsername);
		if(reimbursementUpdated != 0) {
			System.out.println("Reimbursement updated successful");
			return true;
		}
		System.out.println("Unable to update, please try again\n");
		return false;
	}

	public boolean deleteReimbursement(Reimbursement r) {
		int reimbursementDeleted = reimbursementDao.deleteReimbursement(r);
		if(reimbursementDeleted != 0) {
			System.out.println("Reimbursement deletion successful");
			return true;
		}
		System.out.println("Unable to delete, please try again\n");
		return false;
	}

}
