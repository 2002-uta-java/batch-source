package com.revature.project1.daos;

import java.util.List;

import com.revature.project1.models.Request;

public interface RequestDao {
	
	public List<Request> getRequests();
	public Request getRequestByStatus(int status);
	public List<Request> getRequestByUserId(int userId);
	public int updateRequest(Request r);
	public int createRequest(Request r);

}
