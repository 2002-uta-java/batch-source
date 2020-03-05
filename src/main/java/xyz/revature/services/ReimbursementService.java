package xyz.revature.services;

import java.util.List;

import xyz.revature.daos.ReimbursementDao;
import xyz.revature.daos.ReimbursementDaoImpl;
import xyz.revature.models.Reimbursement;

public class ReimbursementService {
	private ReimbursementDao reDao = new ReimbursementDaoImpl();
	
	public List<Reimbursement> getAllReimbursements(){
		return reDao.getAllReimbursements();
	}
	public List<Reimbursement> getReimbursementsOfEmployee(int id) {
		return reDao.getReimbursementsOfEmployee(id);
	}
	public int addReimbursement(Reimbursement r) {
		return reDao.addReimbursement(r);
	}
	public int update(int id, boolean t) {
		return reDao.update(id, t);
	}
}
