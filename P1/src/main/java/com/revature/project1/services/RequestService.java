package com.revature.project1.services;

import java.util.List;

import com.revature.project1.daos.RequestDao;
import com.revature.project1.daos.RequestDaoImpl;
import com.revature.project1.models.Request;

public class RequestService {
	
	private RequestDao rd = new RequestDaoImpl();
	
	public List<Request> getRequests() {
		return getRequests();
	}
	
	public Request getRequestByStatus(int status) {
		return rd.getRequestByStatus(status);
	}
	
	public List<Request> getRequestByUserId(int userId) {
		return rd.getRequestByUserId(userId);
	}
	
	public boolean updateRequest(Request r) {
		int requestsUpdated = rd.updateRequest(r);
		if(requestsUpdated != 0) {
			return true;
		}
		return false;
	}

}
