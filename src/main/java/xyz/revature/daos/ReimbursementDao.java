package xyz.revature.daos;

import java.util.List;

import xyz.revature.models.Reimbursement;

public interface ReimbursementDao {
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getReimbursementsOfEmployee(int id);
	public int addReimbursement(Reimbursement r);
	public int update(int id, boolean t);
}
