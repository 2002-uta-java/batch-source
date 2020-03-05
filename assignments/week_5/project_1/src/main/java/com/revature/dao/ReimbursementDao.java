package com.revature.dao;

import java.util.List;

import com.revature.models.Discussion;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	
	// create a new reimbursement in the database. Return reimbursement object
	public Reimbursement createReimbursement(Reimbursement r);
	
	// find a specific reimbursement by id in the database. Return found reimbursement object
	public Reimbursement getReimbursementById(int id);
	
	// update a reimbursement (like change status) in the database. Returns int for checking for success
	public int updateReimbursement(Reimbursement r);
	
	// approve a reimbursement (status) in the database and set approved by id. Returns int for checking for success
	public int approveReimbursement(Reimbursement r);
	
	public int denyReimbursement(Reimbursement r);
	
	// get a list of all reimbursement objects from the database
	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> getAllReimbursementsWithId(Employee e);
	
	// get a list of all reimbursement objects for a specified employee from the database
	public List<Reimbursement> getAllReimbursementsForEmployee(Employee e);
	
	// create a discussion post for a specific reimbursement in the database. Returns a discussion object
	public Discussion getDiscussionPost();
	
	// retrieve all discussion post(s) related to a specific reimbursement. Returns a List of discussion objects
	public List<Discussion> getDiscussion();
	
}
