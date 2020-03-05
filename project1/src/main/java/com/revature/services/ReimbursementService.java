package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	private ReimbursementDAO rDAO = new ReimbursementDAOImpl();

	public List<Reimbursement> getAll() {
		// TODO Auto-generated method stub
		return rDAO.getAll();
	}

}
