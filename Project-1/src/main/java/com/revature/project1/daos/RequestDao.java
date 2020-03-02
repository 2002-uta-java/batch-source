package com.revature.project1.daos;

import java.util.List;

import com.revature.project1.models.Request;

public interface RequestDao {
	
	public List<Request> getRequests();
	public Request getRequestByPending(boolean pending);
	public List<Request> getRequestByUserId(int userId);
	public int updateApproval(Request r);
	public int updatePending(Request r);

}
