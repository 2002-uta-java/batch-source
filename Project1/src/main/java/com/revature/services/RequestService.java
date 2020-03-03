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
	
	public List<Request> getEmployeeRequests(int empl_id) {
		return rd.getEmployeeRequests(empl_id);
	}
	
	public List<Request> getManagerRequests(int mng_id) {
		return rd.getManagerRequests(mng_id);
	}
	
	public boolean createNewRequest(int emplId, LocalDate reimburseDate, double amount, String description, String imgUrl, String category) {
		Request r = new Request(LocalDateTime.now(), "PENDING", emplId, reimburseDate, amount, description, imgUrl, category);
		
		if (rd.createRequest(r) != null) {
			return true;
		}
		
		return false;
		
	}

	public boolean reviewRequest(Request req) {
		
		if(rd.updateRequest(req) > 0) {
			return true;
		}
		
		return false;
	}
}
