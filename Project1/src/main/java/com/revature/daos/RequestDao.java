package com.revature.daos;

import java.util.List;

import com.revature.models.Request;

public interface RequestDao {
	
	public List<Request> getAllRequests();
	public List<Request> getAllRequests(int offset, int limit);
	public List<Request> getManagerRequests(int empl_id);
	public List<Request> getEmployeeRequests(int mng_id);
	public Request getRequestById(int id);
	public Request createRequest(Request r);
	public int updateRequest(Request r);
	public int deleteRequest(Request r);

}
