package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursment;

public interface ReimbursementDAO {
	List<Reimbursment> getAllReimbursments();
	Reimbursment getReimbursment(int id);
	void addReimbursment(Reimbursment reimbursment) throws Exception;
	void deleteReimbursment(int id) throws Exception;
	void updateReimbursment(Reimbursment reimbursment) throws Exception;
}
