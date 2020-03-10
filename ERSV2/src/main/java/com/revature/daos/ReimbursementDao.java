package com.revature.daos;

import java.util.List;

import org.json.JSONArray;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	public boolean createReimbursement(Reimbursement newReimbursement);

	public Reimbursement viewReimbursementDetails(int reimbursementId);

	public boolean updateReimbursement(Reimbursement updateReimbursement, int reimbursementid);
	public boolean updateReimbursementStatus(JSONArray arrReimbiIds, String status);
	public boolean updateReimbursementStatus(JSONArray arrReimbiIds, String status, int approverID);

	public String deleteReimbursement(int reimbursementId);

	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getAllReimbursementsByEmployee(int emplId);
}
