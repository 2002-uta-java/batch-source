package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface ReimbursementDao {

	public List<Reimbursement> getAllReimbursements(String status);
	public List<Reimbursement> getAllReimbursements(Employee e);
	public List<Reimbursement> getAllReimbursements(Employee e, String status);
	public int createReimbursement(Employee e, double expense, String rtype);
	public Reimbursement getReimbursementById(int rid);
	public int updateReimbursement(int rid, String resolution, int eid);
	public int deleteReimbursement(Reimbursement r);
}
