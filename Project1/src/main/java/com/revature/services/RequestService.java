package com.revature.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.revature.daos.RequestDao;
import com.revature.daos.RequestDaoImpl;
import com.revature.models.Request;

public class RequestService {
	
	private RequestDao rd = new RequestDaoImpl();
	
	public List<Request> getAllRequests() {
		return rd.getAllRequests();
	}
	
	public Request getRequestById(int id) {
		return rd.getRequestById(id);
	}
	
	public List<Request> getEmployeeRequests(int emplId) {
		return rd.getEmployeeRequests(emplId);
	}
	
	public List<Request> getManagerRequests(int mngId) {
		return rd.getManagerRequests(mngId);
	}
	
	public boolean createNewRequest(int emplId, LocalDate reimburseDate, double amount, String description, String imgUrl, String category) {
		Request r = new Request(LocalDateTime.now(), "PENDING", emplId, reimburseDate, amount, description, imgUrl, category);
		
		if (amount <= 0 || amount > 1000000) {
			return false;
		}
		
		return rd.createRequest(r) != null;
		
	}

	public boolean reviewRequest(Request req) {
		
		return rd.updateRequest(req) > 0;

	}
}
