package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface ReimbursementDao {

	public List<Reimbursement> getReimbursementsAll();
	public List<Reimbursement> getReimbursementsByEmployee(Employee e);
	public void createReimbursement(Reimbursement r);
	public void updateReimbursement(Reimbursement r, Employee e);
}
