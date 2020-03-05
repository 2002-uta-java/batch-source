package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	private ReimbursementDao rDao = new ReimbursementDaoImpl();

	public boolean validAmount(Float amount) {
		if (amount == 0) {
			return false;
		}
		if (amount < 0) {
			return false;
		}
		if (amount > 9999.99) { // no greater than a SQL max (numeric 6, 2)
			return false;
		}
		return true;
	}
	
	public float roundTwoDecimal(float f) {
		return (float) Math.round(f * 100f) / 100f;
	}
	
	public int getNewId() {
		List<Reimbursement> reimbursements = rDao.getReimbursementsAll();
		int maxId = 1;
		
		for (Reimbursement r: reimbursements) {
			int id = r.getId();
			if (id >= maxId) {
				maxId = id + 1;
			}
		}
		
		return maxId;
	}
}
