package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	public boolean createReimbursement(Reimbursement newReimbursement);

	public Reimbursement viewReimbursementDetails(int reimbursementId);

	public boolean updateReimbursement(Reimbursement updateReimbursement, int reimbursementid);

	public String deleteReimbursement(int reimbursementId);

	public List<Reimbursement> getAllReimbursements();

}
