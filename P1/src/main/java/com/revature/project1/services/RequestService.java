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
	
	public List<Request> getRequestByUserId(String userEmail) {
		return rd.getRequestByUserEmail(userEmail);
	}
	
	public boolean updateRequest(Request r) {
		int requestsUpdated = rd.updateRequest(r);
		if(requestsUpdated != 0) {
			return true;
		}
		return false;
	}
	
	public boolean createRequest(Request r) {
		int requestCreated = rd.createRequest(r);
		if(requestCreated != 0) {
			return true;
		}
		return false;
	}

}
