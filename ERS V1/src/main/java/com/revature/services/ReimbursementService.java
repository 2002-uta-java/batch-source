package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	private ReimbursementDao rd = new ReimbursementDaoImpl();

	private List<Reimbursement> reimbursements = new ArrayList<>();

	// returns all of the reimbursements in the database
	public List<Reimbursement> getAllReimbursements() {
		return (List<Reimbursement>) rd.getAllReimbursements();
	}
	// returns all of the reimbursements in the database
	public List<Reimbursement> getAllReimbursementsByEmployeeId(int emplid) {
		return (List<Reimbursement>) rd.getAllReimbursementsByEmployee(emplid);
			
	}

	// returns specified employee requested by the id
	public Reimbursement viewReimbursementDetails(int reimbursementId) {
		return rd.viewReimbursementDetails(reimbursementId);
	}
	
	//returns an object with updated reimbursement information 
	public boolean updateReimbursement(Reimbursement updateReimbursement,int reimbursementid) {
		return rd.updateReimbursement(updateReimbursement,reimbursementid);
	}
	
	public boolean updateReimbursementStatus(JSONArray arrReimbiIds, String status) {
		return rd.updateReimbursementStatus(arrReimbiIds, status);
	}
	
	public boolean addReimbursement(Reimbursement r) {
		return rd.createReimbursement(r);
	}
}
